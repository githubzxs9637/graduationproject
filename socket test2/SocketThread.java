package com.example.hp_pc.sockettest;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Created by hp-pc on 2016/10/18.
 */


    public class SocketThread extends Thread {
        private String ip = "jd-thesis.ecs.csun.edu";
        private int port = 13000;
        private String TAG = "socket thread";
        private int timeout = 10000;

        public Socket client = null;
        PrintWriter out;
        BufferedReader in;
        public boolean isRun = true;
        Handler inHandler;
        Handler outHandler;
        Context ctx;
        private String TAG1 = "===Send===";
        SharedPreferences sp;

        public SocketThread(Handler handlerin, Handler handlerout, Context context) {
            inHandler = handlerin;
            outHandler = handlerout;
            ctx = context;
            Log.i(TAG, "creating socket");
        }


        public void conn() {

            try {
                initdate();
                Log.i(TAG, "connecting");
                client = new Socket(ip, port);
                client.setSoTimeout(timeout);
                Log.i(TAG, "success");
                in = new BufferedReader(new InputStreamReader(
                        client.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        client.getOutputStream())), true);
            } catch (UnknownHostException e) {
                Log.i(TAG, "connection error,UnknownHostException");
                e.printStackTrace();
                conn();
            } catch (IOException e) {
                Log.i(TAG, "error");
                e.printStackTrace();
            } catch (Exception e) {
                Log.i(TAG, "error" + e.getMessage());
                e.printStackTrace();
            }
        }

        public void initdate() {
            sp = ctx.getSharedPreferences("SP", ctx.MODE_PRIVATE);
            ip = sp.getString("ipstr", ip);
            port = Integer.parseInt(sp.getString("port", String.valueOf(port)));
            Log.i(TAG, "ip:" + ip + ";" + port);
        }


        @Override
        public void run() {
            Log.i(TAG, "socket start");
            conn();
            String line = "";
            while (isRun) {
                try {
                    if (client != null) {
                        while ((line = in.readLine()) != null) {
                            Log.i(TAG, "3.getdata" + line + " len=" + line.length());
                            Log.i(TAG, "4.start set Message");
                            Message msg = inHandler.obtainMessage();
                            msg.obj = line;
                            inHandler.sendMessage(msg);
                            Log.i(TAG1, "5.send to handler");
                        }

                    } else {
                        Log.i(TAG, "connection unavailable");
                        conn();
                    }
                } catch (Exception e) {
                    Log.i(TAG, "error" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }


        public void Send(String mess) {
            try {
                if (client != null) {
                    Log.i(TAG1, "sending" + mess + "to"
                            + client.getInetAddress().getHostAddress() + ":"
                            + String.valueOf(client.getPort()));
                    out.println(mess);
                    out.flush();
                    Log.i(TAG1, "success");
                    Message msg = outHandler.obtainMessage();
                    msg.obj = mess;
                    msg.what = 1;
                    outHandler.sendMessage(msg);
                } else {
                    Log.i(TAG, "client doesn't exist");
                    Message msg = outHandler.obtainMessage();
                    msg.obj = mess;
                    msg.what = 0;
                    outHandler.sendMessage(msg);
                    Log.i(TAG, "connection error");
                    conn();
                }

            } catch (Exception e) {
                Log.i(TAG1, "send error");
                e.printStackTrace();
            } finally {
                Log.i(TAG1, "message sent");

            }
        }

        public void close() {
            try {
                if (client != null) {
                    Log.i(TAG, "close in");
                    in.close();
                    Log.i(TAG, "close out");
                    out.close();
                    Log.i(TAG, "close client");
                    client.close();
                }
            } catch (Exception e) {
                Log.i(TAG, "close error");
                e.printStackTrace();
            }

        }
    }

