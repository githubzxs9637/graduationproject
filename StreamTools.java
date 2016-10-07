package com.example.hp_pc.graduation;

/**
 * Created by hp-pc on 2016/8/18.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
    public static String readStream(InputStream is){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer))!=-1) {
                baos.write(buffer,0,len);
            }
            baos.close();
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
