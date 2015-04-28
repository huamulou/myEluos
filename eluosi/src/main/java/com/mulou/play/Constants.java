package com.mulou.play;

import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by huamulou on 14-6-18.
 */
public class Constants {


    public final static int gamePanelWidth = 383;
    public final static int gamePanelHeight = 543;
    public final static int imageWidth = 480;
    public final static int imageHeight = 800;

    public final static int gameLeft = 30;
    public final static int gameTop = 16;


    public static float runtimeXRatio;
    public static float runtimeYRatio;


    public static int runtimeGameLeft;
    public static int runtimeGameTop;



    public static int deviceWidth;
    public static int deviceHeight;

    public static int layoutWidth;
    public static int layoutHeight;


    public final static int squareImgSize = 27;

    public  static int runtimeSquareImgWidth ;
    public  static int runtimeSquareImgHeight ;
    /**
     * 垂直size
     */
    public final static int eluosiVerticalSize = 20;
    /**
     * 水平size
     */
    public final static int eluosiHorizoHntal = 10;

    public static DisplayMetrics displayMetrics;


    public final static int t1Left = 306;
    public final static int t1Top = 79;

    public static int runtimeT1Left;
    public static int runtimeT1Top;



    public final static int t2Left = 304;
    public final static int t2Top = 153;


    public static int runtimeT2Left;
    public static int runtimeT2Top;

    public final static int t3Left = 304;
    public final static int t3Top = 217;

    public static int runtimeT3Left;
    public static int runtimeT3Top;

    public final static int t4Left = 372;
    public final static int t4Top = 391;

    public static int runtimeT4Left;
    public static int runtimeT4Top;


    public final static int fontSize = 24;
    public  static int runtimeFontSize ;



    public final  static  int defaultLeft = 3;
    public final  static  int defaultTop = -4;


    public final static int scoreSize = 8;
    public final static int levelSize = 2;



    public final static int infoLeft = 124;
    public final static int infoTOp = 209;




    public  final static int shapes[][][] = new int[][][] {
            // i
            { { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                    { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
            // s
            { { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
            // z
            { { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
            // J
            { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // o(田字)
            { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // l(竖右勾)
            { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // T
            { { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(Math.random()*3);
        System.out.println(Math.random()*3);
        System.out.println(Math.random()*3);
        System.out.println(Math.random()*3);
        int[][] i = shapes[0];
        int[] j = i[0];
    }
}
