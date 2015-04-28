package com.mulou.play.domain;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Created by huamulou on 14-6-19.
 */
public class SimpleText {

    public String txt;
    public int left;
    public int top;
    public int defaultSize;


    public SimpleText(String txt, int left, int top, int defaultSize) {
        this.txt = txt;
        this.left = left;
        this.top = top;
        this.defaultSize = defaultSize;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }


    public String getTxt() {
        return txt;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getDefaultSize() {
        return defaultSize;
    }


}
