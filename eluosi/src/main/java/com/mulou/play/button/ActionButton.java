package com.mulou.play.button;

import android.view.MotionEvent;
import android.view.View;
import com.mulou.play.game.GameView;

/**
 * Created by huamulou on 14-6-18.
 */
public class ActionButton extends Btnn {
    public enum TYPE {
        W, A, S, D, ACTION
    }

    private TYPE type;

    private int resourceOn;
    private int resourceOff;
    private GameView game;

    public ActionButton(TYPE type, int resourceOn, int resourceOff, GameView view) {
        this.type = type;
        this.resourceOn = resourceOn;
        this.resourceOff = resourceOff;
        this.game = view;
    }

    @Override
    public boolean sencondHandTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            switch (type) {
                case W:
                    game.up();
                    break;
                case A:
                    game.left();
                    break;
                case S:
                    game.down();
                    break;
                case D:
                    game.right();
                    break;
                case ACTION:
                    game.change();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    public void change(View view, int state) {
        switch (state) {
            case state_off:
                view.setBackgroundResource(resourceOff);
                break;
            case state_on:
                view.setBackgroundResource(resourceOn);
                break;
            default:
                break;

        }

    }


}
