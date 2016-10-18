package com.example.hp_pc.testtouchscreen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;


public class MainActivity extends Activity {
    private TextView  info=null;
    private TextView textView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info=(TextView)super.findViewById(R.id.info);
        textView=(TextView)super.findViewById(R.id.maintext);
        textView.setOnTouchListener(new PicOnTouchListener());
    }
    private class PicOnTouchListener implements OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){
                // TODO Auto-generated method stub
                Layout layout = ((TextView) v).getLayout();
                int x = (int)event.getX();
                int y = (int)event.getY();
                if (layout!=null){
                    int line = layout.getLineForVertical(y);
                    int location = layout.getOffsetForHorizontal(line, x);
                    //if(layout.getText().charAt(location)!=' ') {
                       // String word = getWord(layout.getText().toString(), location);



                        info.setText(location);
                   // }

                    //if (layout.getText().charAt(location)!=' ') {
                       // for (int m = location; m >= 0; m--) {
                          //  if (layout.getText().charAt(m) == ' ' || m == 0)
                              //  for (int n = location; n <= layout.getText().toString().length(); n++) {
                                 //   if (layout.getText().charAt(n) == ' ' || n == layout.getText().toString().length()) {
                                     //   String word = layout.getText().toString().substring(m+1, n );
                                    //    info.setText(word);
                                    //    m=-1;
                                     //   n=layout.getText().toString().length()+1;

                                   // }
                             //   }
                       // }
                   // }
                    //Log.i("index", ""+characterOffset);
                    //info.setText(Integer.toString(characterOffset));

                }
                return true;
        }
        private String getWord(String text,int location,SpannableStringBuilder style) {
            if(text.charAt(location)==' ')
                return String.valueOf(text.charAt(location));
            int startPosition = findStartPosition(text,location);
            int endPosition = findEndPosition(text,location);

            style=new SpannableStringBuilder(text);
            style.setSpan(new BackgroundColorSpan(Color.RED),startPosition+1,endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(style);
            //if(startPosition == -1 || endPosition == -1) return "NULL!";
            return text.substring(startPosition + 1,endPosition);
        }

        private int findStartPosition(String text, int location){
            if(location < 0 || location>= text.length()) return -1;
            for (int i = location;i>0;i--){
                if (text.charAt(i)==' ') return i;
            }
            return -1;
        }
        private int findEndPosition(String text,int location){
            if(location < 0 || location >= text.length()) return -1;
            for(int i = location;i < text.length();i++){
                if(text.charAt(i) == ' ') return i;
            }
            return text.length();
        }
        }
    }
