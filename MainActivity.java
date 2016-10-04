package com.example.hp_pc.testtouchscreen1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView maintext = (TextView) findViewById(R.id.maintext);
        final TextView info = (TextView) findViewById(R.id.info);
        maintext.setMovementMethod(new LinkTouchMovementMethod());
        String rv = "This is a long passage where you can tap words on \n" +
                "This is the second paragraph";
        final SpannableString text = new SpannableString(rv);
        String regex = "\\w+";
        Pattern p = Pattern.compile(regex);

        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            final int begin = matcher.start();
            final int end = matcher.end();
            TouchableSpan touchableSpan = new TouchableSpan() {

                public boolean onTouch(View widget, MotionEvent m) {
                    String lword = (String) text.subSequence(begin, end).toString();
                    info.setText(lword);
                    return true;
                }

                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setAntiAlias(true);
                }

            };

            text.setSpan(touchableSpan, begin, end, 0);
            maintext.setText(text, TextView.BufferType.SPANNABLE);
        }
    }
}
