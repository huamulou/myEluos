package com.mulou.play.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import com.mulou.play.Constants;
import com.mulou.play.R;
import com.mulou.play.domain.Tile;

import static com.mulou.play.Constants.*;

/**
 * Created by huamulou on 14-6-18.
 */
public class GameView extends GameInfo {


    /* 声明Paint对象 */
    private Paint mPaint = null;

    private boolean isPositionInited = false;


    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    public void init() {
        Typeface fontFace = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/lcd.ttf");
        mPaint = new Paint();
        mPaint.setTypeface(fontFace);
        mRedrawHandler = new RefreshHandler();
        if (this.getViewTreeObserver() != null) {
            this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    getViewTreeObserver().removeOnPreDrawListener(this);
                    Constants.layoutWidth = getMeasuredWidth();
                    Constants.layoutHeight = getMeasuredHeight();

                    Log.e("tag", "###############" + Constants.deviceHeight + "-" + Constants.deviceWidth);

                    View v = findViewById(R.id.snake);

                    Constants.runtimeXRatio = ((float) Constants.layoutWidth / Constants.imageWidth);
                    Constants.runtimeYRatio = ((float) Constants.layoutHeight / Constants.imageHeight);

                    initPosition();
                    return true;
                }
            });
        }
        update();
    }


    private void initPosition() {

        runtimeGameLeft = (int) (gameLeft * runtimeXRatio);
        runtimeGameTop = (int) (gameTop * runtimeYRatio);

        runtimeT1Left = (int) (t1Left * runtimeXRatio);
        runtimeT1Top = (int) (t1Top * runtimeYRatio);

        runtimeT2Left = (int) (t2Left * runtimeXRatio);
        runtimeT2Top = (int) (t2Top * runtimeYRatio);


        runtimeT3Left = (int) (t3Left * runtimeXRatio);
        runtimeT3Top = (int) (t3Top * runtimeYRatio);


        runtimeT4Left = (int) (t4Left * runtimeXRatio);
        runtimeT4Top = (int) (t4Top * runtimeYRatio);


        runtimeSquareImgWidth = (int) (squareImgSize * runtimeXRatio);
        runtimeSquareImgHeight = (int) (squareImgSize * runtimeYRatio);

        runtimeFontSize = (int) (fontSize * runtimeYRatio);

        isPositionInited = true;

    }


    public void pause() {
        this.status = STATE.PAUSE;
        invalidate();
    }

    public void resume() {
        this.status = STATE.RUNNING;
        invalidate();
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isPositionInited && inited) {

            mPaint.setColor(Color.BLACK);
            Resources r = this.getContext().getResources();
            mPaint.setTextSize(runtimeFontSize);
            canvas.drawText(getString(getScore(), 8), runtimeT1Left, runtimeT1Top, mPaint);
            canvas.drawText(getString(getHighscore(), 8), runtimeT2Left, runtimeT2Top, mPaint);
            canvas.drawText(getString(getLevel(), 2), runtimeT4Left, runtimeT4Top, mPaint);

            for (int i = 0; i < Constants.eluosiHorizoHntal; i++) {
                for (int j = 0; j < Constants.eluosiVerticalSize; j++) {
                    int k = getSquares()[i][j];

                    Drawable drawable = getDrawable(k);
                    if (drawable != null) {
                        int left = runtimeGameLeft + i * runtimeSquareImgWidth;
                        int top = runtimeGameTop + j * runtimeSquareImgHeight;
                        drawable.setBounds(left, top, runtimeSquareImgWidth + left, runtimeSquareImgHeight + top);
                        drawable.draw(canvas);
                    }
                }
            }

            if (this.status == STATE.PAUSE) {
                Drawable d = r.getDrawable(R.drawable.pause);
                d.setBounds((int) (infoLeft * runtimeXRatio), (int) (infoTOp * runtimeYRatio)
                        , (int) ((82 + infoLeft) * runtimeXRatio), (int) ((126 + infoTOp) * runtimeYRatio));
                d.draw(canvas);
            }


            drawTile(canvas, current, runtimeGameLeft, runtimeGameTop);
            Tile tmp = new Tile(next);
            tmp.setLeft((4 - tmp.getWidth()) / 2);
            tmp.setTop((4 - tmp.getHeight()) / 2);
            drawTile(canvas, tmp, runtimeT3Left, runtimeT3Top);

//            canvas.drawText("hellow world", t1Left, t1Top, mPaint);

//            canvas.drawRect(0, 0 ,Constants.displayMetrics.widthPixels-10, Constants.layoutHeight-10, mPaint);
//            canvas.drawRect(0, 0 ,Constants.displayMetrics.widthPixels* 160 /Constants.displayMetrics.ydpi, Constants.displayMetrics.heightPixels* 160 /Constants.displayMetrics.ydpi, mPaint);
        }
    }


    public void drawTile(Canvas canvas, Tile tile, int left, int top) {
        int[] cur = shapes[tile.getX()][tile.getY()];
        for (int i = 0; i < cur.length; i++) {
            if (cur[i] == 1) {
                Drawable drawable = this.getContext().getResources().getDrawable(R.drawable.block);
                int topCount = i / 4 + tile.getTop();
                int leftCout = i % 4 + tile.getLeft();

                if (topCount >= 0 && leftCout >= 0) {
                    int lefttmp = leftCout * runtimeSquareImgWidth + left;
                    int toptmp = topCount * runtimeSquareImgHeight + top;
                    drawable.setBounds(lefttmp, toptmp, runtimeSquareImgWidth + lefttmp, runtimeSquareImgHeight + toptmp);
                    drawable.draw(canvas);
                }
            }
        }

    }


    public static String getString(int num, int size) {
        StringBuffer sb = new StringBuffer("" + num);
        for (int i = sb.length(); i < size; i++) {
            sb.insert(0, " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getString(2, 4));
    }


}
