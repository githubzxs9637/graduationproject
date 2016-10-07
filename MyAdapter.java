package com.example.hp_pc.graduation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.hp_pc.graduation.articlegithub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp-pc on 2016/9/3.
 */
public class MyAdapter extends BaseAdapter {

        private Context context;
        private ArrayList list;
        public MyAdapter(Context context,ArrayList list) {
            this.context = context;
             this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.adapter_line, null);
            }
            return convertView;
        }

    }


