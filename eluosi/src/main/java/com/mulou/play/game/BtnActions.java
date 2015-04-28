package com.mulou.play.game;

import android.view.MotionEvent;
import android.view.View;
import com.mulou.play.R;
import com.mulou.play.button.Btnn;
import com.mulou.play.button.Trigger;

/**
 * Created by huamulou on 14-6-18.
 */
public class BtnActions {

    public static class StartButtonTrigger extends Btnn {
        private GameView gameView;

        public StartButtonTrigger(GameView gameView) {
            this.gameView = gameView;
        }

        @Override
        public boolean sencondHandTouch(View view, MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void change(View view, int state) {
            switch (state) {
                case state_off:
                    gameView.start();
                    view.setBackgroundResource(R.drawable.start_down);
                    break;
                case state_on:
                    view.setBackgroundResource(R.drawable.start);
                    break;
                default:
                    break;

            }

        }
    }

    public static class SoundButtonTrigger extends Trigger {
        private GameView gameView;

        public SoundButtonTrigger(int resourceOn, int resourceOff, GameView gameView) {
            super(resourceOn, resourceOff);
            this.gameView = gameView;
        }

        @Override
        public void on(View view) {
        }

        @Override
        public void off(View view) {
        }
    }

    public static class PauseButtonTrigger extends Trigger {
        private GameView gameView;

        public PauseButtonTrigger(int resourceOn, int resourceOff, GameView gameView) {
            super(resourceOn, resourceOff);
            this.gameView = gameView;
        }

        @Override
        public void on(View view) {
            gameView.pause();
        }

        @Override
        public void off(View view) {
            gameView.resume();
        }
    }

}
