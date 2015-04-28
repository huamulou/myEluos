package com.mulou.play.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.mulou.play.Constants;
import com.mulou.play.R;
import com.mulou.play.domain.Tile;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huamulou on 14-6-19.
 */
public class GameInfo extends View {

    private int[][] squares = new int[10][20];
    /**
     * Create a simple handler that we can use to cause animation to happen.  We
     * set ourselves as a target and we can use the sleep()
     * function to cause an update/invalidate to occur at a later date.
     */
    protected RefreshHandler mRedrawHandler;


    private int score = 0;
    private Lock lock = new ReentrantLock();

    private int highscore = 0;
    private int level = 1;

    public Tile current;
    public Tile next;


    public final static int blank = 0;
    public final static int square1 = 1;
    public final static int square2 = 2;


    public final static int square1_r = R.drawable.block;
    public final static int square2_r = R.drawable.block2;


    public STATE status;
    public boolean inited = false;

    public enum STATE {
        RUNNING, PAUSE, NEW, GAMEOVER
    }


    public GameInfo(Context context) {
        super(context);
        this.initInfo();
    }

    public GameInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initInfo();
    }


    public GameInfo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initInfo();
    }

    private void initInfo() {
        current = getNextTile();
        next = getNextTile();
        status = STATE.NEW;
        inited = true;

    }

    public void start() {
        if (status == STATE.NEW || status == STATE.GAMEOVER) {
            lock.lock();
            try {
                if (status == STATE.GAMEOVER) {
                    for (int i = 0; i < squares.length; i++) {
                        for (int j = 0; j < squares[i].length; j++) {
                            squares[i][j] = 0;
                        }
                    }
                }
                if (status == STATE.NEW || status == STATE.GAMEOVER) {
                    status = STATE.RUNNING;
                }


            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * 检查是否可以执行下一步
     *
     * @param tile
     * @param squares
     * @return
     */
    public static boolean check(Tile tile, int[][] squares) {
//        if (tile.getLeft() < 0 || tile.getTop() < 0) {
//            return false;
//        }
        int[] ii = Constants.shapes[tile.getX()][tile.getY()];
        for (int i = 0; i < 16; i++) {
            if (ii[i] == 1) {
                int top = i / 4 + tile.getTop();
                int left = i % 4 + tile.getLeft();
                /**
                 * 由于图案是从上面逐渐出来的，所以是-4
                 */
                if (top < -4 || top > 19 || left < 0 || left > 9) {
                    return false;
                }
                if (top >= -4 && top < 0) {
                    continue;
                }
                try {
                    Log.i("mulou", "top:" + top + "-" + "left:" + left);
                    if (left >= squares.length || top >= squares[left].length) {
                        return false;
                    }
                    if (squares[left][top] == 1) {
                        return false;
                    }
                } catch (Exception e) {
                    Log.e("mulou", "top:" + top + "-" + "left:" + left, e);
                }
            }
        }
        return true;
    }


    /**
     * 发现连成一行的就杀死
     */
    public void kill() {
        int lines = 0;
        for (int i = 0; i < Constants.eluosiVerticalSize; i++) {
            boolean flag = true;
            for (int j = 0; j < Constants.eluosiHorizoHntal; j++) {
                if (squares[j][i] == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                lines++;
                for (int j = i; j > 0; j--) {
                    for (int o = 0; o < Constants.eluosiHorizoHntal; o++) {
                        if (j - 1 > 0) {
                            squares[o][j] = squares[o][j - 1];
                        } else {
                            squares[o][j] = 0;
                        }
                    }
                }
            }
        }
        int tmpScore = 0;
        /**
         * 行数越多，分数越高
         */
        for (int i = 0; i < lines; i++) {
            tmpScore += (i + 1) * 100;
        }
        this.score += tmpScore;
    }

    public boolean up() {
        return true;
    }

    public boolean left() {
        if (!inited) {
            return false;
        }
        if (status != STATE.RUNNING) {
            return true;
        }
        lock.lock();
        try {
            Tile tmp = new Tile(current);
            tmp.setLeft(tmp.getLeft() - 1);
            if (check(tmp, squares)) {
                current.setLeft(tmp.getLeft());
                this.invalidate();
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean right() {
        if (!inited) {
            return false;
        }
        if (status != STATE.RUNNING) {
            return true;
        }
        lock.lock();
        try {
            Tile tmp = new Tile(current);
            tmp.setLeft(tmp.getLeft() + 1);
            if (check(tmp, squares)) {
                current.setLeft(tmp.getLeft());
                this.invalidate();
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean down() {
        if (!inited) {
            return false;
        }
        if (status != STATE.RUNNING) {
            return true;
        }
        lock.lock();
        try {
            Tile tmp = new Tile(current);
            tmp.setTop(tmp.getTop() + 1);
            if (check(tmp, squares)) {
                current.setTop(tmp.getTop());
                this.invalidate();
                return true;
            } else {
                if (tmp.getTop() < 0) {
                    this.status = STATE.GAMEOVER;
                    return false;
                } else {
                    addTileToSquares();
                    kill();
                }
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void addTileToSquares() {
        int[] ii = Constants.shapes[current.getX()][current.getY()];
        for (int i = 0; i < 16; i++) {
            if (ii[i] == 1) {
                int top = i / 4 + current.getTop();
                int left = i % 4 + current.getLeft();
                if (top < 0) {
                    continue;
                }
                squares[left][top] = 1;

            }
        }
        current = new Tile(next);
        next = getNextTile();
    }

    public boolean change() {
        if (status != STATE.RUNNING) {
            return true;
        }
        lock.lock();
        try {
            Tile tmp = new Tile(current);
            int max = Constants.shapes[tmp.getX()].length;
            int y = tmp.getY();

            if (tmp.getY() == max - 1) {
                y = 0;
            } else {
                y += 1;
            }
            tmp.setY(y);
            if (check(tmp, squares)) {
                current.setY(y);
                this.invalidate();
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }


    public Tile getNextTile() {
        int x = getRandom(Constants.shapes.length);
        int y = getRandom(Constants.shapes[x].length);
        int left = Constants.defaultLeft;
        Tile tile = new Tile(left, Constants.defaultTop, x, y);
        getTileInfo(tile);
        tile.setTop(-tile.getHeight());
        tile.setLeft((10 - tile.getWidth()) / 2);
        return tile;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getLevel() {
        return level;
    }

    public void setSquares(int[][] squares) {
        this.squares = squares;
    }

    public void setCurrent(Tile current) {
        this.current = current;
    }

    public void setNext(Tile next) {
        this.next = next;
    }


    public int[][] getSquares() {
        return squares;
    }

    public Tile getCurrent() {
        return current;
    }

    public Tile getNext() {
        return next;
    }

    public static int getRandom(int arg0) {
        return (int) (Math.random() * arg0);
    }


    public Drawable getDrawable(int seq) {
        Drawable drawable = null;
        Resources r = getContext().getResources();
        switch (seq) {
            case blank:
                drawable = null;
                break;
            case square1:
                drawable = r.getDrawable(square1_r);
                break;
            case square2:
                drawable = r.getDrawable(square2_r);
                break;
            default:
                drawable = null;
                break;
        }
        return drawable;
    }


    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            GameInfo.this.update();
            GameInfo.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }

    int num = 0;

    protected void update() {
        down();
        mRedrawHandler.sleep(1000);
    }


    protected void getTileInfo(Tile tile) {
        int[] ii = Constants.shapes[tile.getX()][tile.getY()];
        Set<Integer> tops = new HashSet<Integer>();
        Set<Integer> lefts = new HashSet<Integer>();
        for (int i = 0; i < 16; i++) {
            if (ii[i] == 1) {
                int top = i / 4;
                int left = i % 4;
                tops.add(top);
                lefts.add(left);
            }
        }
        tile.setHeight(tops.size());
        tile.setWidth(lefts.size());

    }
}
