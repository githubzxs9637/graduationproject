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

                    try {
                        if(location >= layout.getText().toString().length())  location = layout.getText().toString().length()-1;
                            if (layout.getText().toString().charAt(location) != ' ') {
                                 String word = getWord(layout.getText().toString(), location);
                                //info.setText(Integer.toString(layout.getText().toString().length()));
                                info.setText(word);
                                getColor(textView,location);
                                //info.setText(Integer.toString(location));
                            }

                    }catch(StringIndexOutOfBoundsException e2){
                         info.setText("Out of range!");
                    }
                    //}
                    //else info.setText("Out of range!");

                    //Log.i("index", ""+characterOffset);
                    //info.setText(Integer.toString(characterOffset));

                }
                return true;
        }
        private void getColor(TextView textView,int location) {
            int startPosition = findStartPosition(textView.getText().toString(),location);
            int endPosition = findEndPosition(textView.getText().toString(),location);
            SpannableStringBuilder style=new SpannableStringBuilder(textView.getText().toString());
            style.setSpan(new BackgroundColorSpan(Color.RED),startPosition+1,endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.getText();
        }
        private String getWord(String text,int location) {
            if(text.charAt(location)==' ')
                return String.valueOf(text.charAt(location));
            int startPosition = findStartPosition(text,location);
            int endPosition = findEndPosition(text,location);

            if(endPosition == -1) return "NULL!";
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
