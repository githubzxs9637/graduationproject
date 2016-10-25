package com.example.hp_pc.sockettest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
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
        if(TextUtils.isEmpty(et_start.getText().toString().trim())||TextUtils.isEmpty(et_end.getText().toString().trim())){
            Toast.makeText(MainActivity.this, "The test is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("start position", et_start.getText().toString().trim());
        jsonObject.put("end position", et_end.getText().toString().trim());
        final String  result=jsonObject.toString();
        Log.i("jSON", result);
        new Thread(new  Runnable() {
            @Override
            public void run() {

                try {
                    Socket socket=new Socket(InetAddress.getByName(host), 8155);
                    OutputStream os=socket.getOutputStream();
                    os.write(result.getBytes());
                    os.flush();
                    socket.shutdownOutput();
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