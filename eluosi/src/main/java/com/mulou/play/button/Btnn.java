package com.mulou.play.button;

import android.view.MotionEvent;
import android.view.View;
import com.mulou.play.R;
import com.mulou.play.game.GameView;

/**
 * Created by huamulou on 14-6-18.
 */
public abstract class Btnn  implements View.OnTouchListener  {

    protected final static int state_on = 1;
    protected final static int state_off = 2;
    protected int state = state_on;

    protected GameView game;



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            this.state = state_off;
            change(view, this.state);
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            this.state = state_on;
            change(view, this.state);
        }
        return sencondHandTouch(view, motionEvent);
    }


    public abstract boolean sencondHandTouch(View view, MotionEvent motionEvent);

    public abstract void change(View view, int state) ;


}
