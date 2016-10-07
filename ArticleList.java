package com.example.hp_pc.graduation;

import android.os.Bundle;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.example.hp_pc.graduation.articlegithub;

/**
 * Created by hp-pc on 2016/8/29.
 */
public class ArticleList extends Activity {
    private ListView listview;
    public MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlelist);
        listview = (ListView) findViewById(R.id.listView);
        ArrayList arraylist = new ArrayList();
        articlegithub.changelist(arraylist);
        adapter = new MyAdapter(this,arraylist);
        listview.setAdapter(adapter);

    }

}
