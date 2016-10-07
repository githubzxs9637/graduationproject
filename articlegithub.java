package com.example.hp_pc.graduation;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by hp-pc on 2016/9/2.
 */
public class articlegithub {
    public final String API_URL = "http://jd-thesis.ecs.csun.edu/~stone";

    public class articlelist {
        public int articleid;
        public String articlename;

        public articlelist(int articleid,String articlename) {
            this.articleid = articleid;
            this.articlename = articlename;
        }
    }

    public interface GitHub {
        @GET("/articlelist.php")
        Call<List<articlelist>> getlist();
    }



    public static void changelist(ArrayList finallist) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHub github = retrofit.create(GitHub.class);
        Call<List<articlelist>> call = github.getlist();
        List<articlelist> lists = call.execute().body();
        for (articlelist list : lists){
             finallist.add(list.articlename);
        }
    }
}