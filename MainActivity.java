
package com.example.hp_pc.graduation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected static final int ERROR = 2;
    protected static final int SUCCESS = 1;
    private EditText account;
    private EditText password;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    String obj = (String)msg.obj;
                    Toast.makeText(MainActivity.this,obj,Toast.LENGTH_SHORT).show();
                    System.out.println(obj);
                    if(obj.equals("Login success!") == true){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,ArticleList.class);
                    MainActivity.this.startActivity(intent);
                    }
                    break;

                case ERROR:
                    Toast.makeText(MainActivity.this,"Login failed!",Toast.LENGTH_SHORT).show();
                    break;

            }
        };
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.passward);


    }
    public void login(View view){
        final String qq = account.getText().toString();
        final String psd = password.getText().toString();

        if(TextUtils.isEmpty(qq)||TextUtils.isEmpty(psd)){
            Toast.makeText(this, "Please check your input!",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(){
            public void run(){
                try {

                    String path = "http://jd-thesis.ecs.csun.edu/~stone/login.php";
                    URL url = new  URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)");

                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    String data = "username="+qq+"&password="+psd+"&button=";
                    ;
                    conn.setRequestProperty("Content-Length", data.length()+"");

                    conn.setDoOutput(true);
                    byte[] bytes = data.getBytes();
                    conn.getOutputStream().write(bytes);
                    int code = conn.getResponseCode();
                    System.out.println(code);
                    if(code == 200){
                        InputStream is = conn.getInputStream();
                        String  result = StreamTools.readStream(is);
                        Message mas= Message.obtain();
                        mas.what = SUCCESS;
                        mas.obj = result;
                        handler.sendMessage(mas);

                    }else{
                        Message mas = Message.obtain();
                        mas.what = ERROR;
                        handler.sendMessage(mas);
                    }
                }catch (IOException e) {
                    // TODO Auto-generated catch block
                    Message mas = Message.obtain();
                    mas.what = ERROR;
                    handler.sendMessage(mas);
                }
            }
        }.start();

    }
}
