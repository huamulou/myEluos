package com.mulou.play.button;

import android.view.View;

/**
 * Created by huamulou on 14-6-18.
 */
public abstract class Trigger implements View.OnClickListener {

    protected final static int state_on = 1;
    protected final static int state_off = 2;
    protected int state = state_on;
    private int resourceOn;
    private int resourceOff;

    @Override
    public void onClick(View view) {

        switch (this.state) {
            case state_on:
                this.state = state_off;
                view.setBackgroundResource(resourceOff);
                on(view);
                break;
            case state_off:
                this.state = state_on;
                view.setBackgroundResource(resourceOn);
                off(view);
                break;
            default:
                on(view);

        }

    }


    public abstract void on(View view);

    public abstract void off(View view);

    public Trigger(int resourceOn, int resourceOff) {
        this.resourceOn = resourceOn;
        this.resourceOff = resourceOff;
    }
}
