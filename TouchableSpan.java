package com.example.hp_pc.testtouchscreen1;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by hp-pc on 2016/10/4.
 */



    /**
     * If an object of this type is attached to the text of a TextView
     * with a movement method of LinkTouchMovementMethod, the affected spans of
     * text can be selected.  If touched, the {@link #onTouch} method will
     * be called.
     */
    public abstract class TouchableSpan extends CharacterStyle implements UpdateAppearance     {

        /**
         * Performs the touch action associated with this span.
         * @return
         */
        public abstract boolean onTouch(View widget, MotionEvent m);

        /**
         * Could make the text underlined or change link color.
         */
        @Override
        public abstract void updateDrawState(TextPaint ds);

    }
