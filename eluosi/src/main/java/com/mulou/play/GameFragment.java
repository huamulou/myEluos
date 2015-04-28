package com.mulou.play;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.actionbarsherlock.app.SherlockFragment;
import com.mulou.play.button.ActionButton;
import com.mulou.play.game.BtnActions;
import com.mulou.play.game.GameView;

/**
 * Created by huamulou on 14-6-18.
 */
public class GameFragment extends SherlockFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.gamezone, container, false);
        return v;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton startButton = (ImageButton) getActivity().findViewById(R.id.start);
       GameView game =  (GameView)getActivity().findViewById(R.id.snake);
        startButton.setOnTouchListener(new BtnActions.StartButtonTrigger(game));

//
        ImageButton soundButton = (ImageButton) getActivity().findViewById(R.id.sound);

        soundButton.setOnClickListener(new BtnActions.SoundButtonTrigger(R.drawable.sound, R.drawable.sound_down, game));


        ImageButton pauseButton = (ImageButton) getActivity().findViewById(R.id.pause);

        pauseButton.setOnClickListener(new BtnActions.PauseButtonTrigger(R.drawable.pause2, R.drawable.pause_down, game));

        ImageButton aButton = (ImageButton) getActivity().findViewById(R.id.a);

        aButton.setOnTouchListener(new ActionButton(ActionButton.TYPE.A, R.drawable.round, R.drawable.round_down, (GameView)getActivity().findViewById(R.id.snake)) );
        ImageButton wButton = (ImageButton) getActivity().findViewById(R.id.w);

        wButton.setOnTouchListener(new ActionButton(ActionButton.TYPE.W, R.drawable.round, R.drawable.round_down, (GameView)getActivity().findViewById(R.id.snake)));
        ImageButton sButton = (ImageButton) getActivity().findViewById(R.id.s);

        sButton.setOnTouchListener(new ActionButton(ActionButton.TYPE.S, R.drawable.round, R.drawable.round_down, (GameView)getActivity().findViewById(R.id.snake)));
        ImageButton dButton = (ImageButton) getActivity().findViewById(R.id.d);

        dButton.setOnTouchListener(new ActionButton(ActionButton.TYPE.D, R.drawable.round, R.drawable.round_down, (GameView)getActivity().findViewById(R.id.snake)));
        ImageButton enterButton = (ImageButton) getActivity().findViewById(R.id.enter);

        enterButton.setOnTouchListener(new ActionButton(ActionButton.TYPE.ACTION, R.drawable.enter, R.drawable.enter_down, (GameView)getActivity().findViewById(R.id.snake)));



    }


}
