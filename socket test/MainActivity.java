package com.example.hp_pc.sockettest;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends Activity {
    private EditText startPosition;
    private EditText endPosition;
    private String host="jd-thesis.ecs.csun.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startPosition=(EditText) findViewById(R.id.et_start);
        endPosition=(EditText) findViewById(R.id.et_end);
    }


    public void submit(View v) throws JSONException{
        if(TextUtils.isEmpty(startPosition.getText().toString().trim())||TextUtils.isEmpty(endPosition.getText().toString().trim())){
            Toast.makeText(MainActivity.this, "The test is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("start position", startPosition.getText().toString().trim());
        jsonObject.put("end position", endPosition.getText().toString().trim());
        final String  result=jsonObject.toString();
        Log.i("jSON", result);
        new Thread(new  Runnable() {
            @Override
            public void run() {

                try {
                    Socket socket=new Socket();
                    socket.connect(new InetSocketAddress(host, 8155),5000);

                    OutputStream os=socket.getOutputStream();
                    os.write(result.getBytes());
                    os.flush();
                    socket.shutdownOutput();
                    InputStream inputStream = socket.getInputStream();
                    DataInputStream ip = new DataInputStream(inputStream);

                    byte[] messageByte = new byte[1000];
                    boolean end = false;
                    String dataString = "";
                    while(!end)
                    {
                        int bytesRead = ip.read(messageByte);
                        dataString += new String(messageByte, 0, bytesRead);
                        if (dataString.length() == 100)
                        {
                            end = true;
                        }
                    }
                    Log.i("MESSAGE: " , dataString);

                    //byte[] b = new byte[10000];
                       // int length = ip.read(b);
                       // String Msg = new String(b, 0, length, "gb2312");
                       // Log.v("data",Msg);   //这里我想让server那边回复一个success，在这边控制台显示出来，结果看不到
                    socket.shutdownInput();
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }



}