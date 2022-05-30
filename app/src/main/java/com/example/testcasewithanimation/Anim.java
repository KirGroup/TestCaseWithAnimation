package com.example.testcasewithanimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Anim {
    private final int TIME_DELAY = 5000;
    private final int MOVE_STEP = 10;
    private final int PERIOD_FOR_TIMER_ANIMATION = 50;

    private TextView activityMainText;
    private Timer timerAnimation;
    private Context animContext;

    private boolean downMove = true;
    private boolean timerActivationFirst = false;

    public Anim(Context mainActivity) {
        this.animContext = mainActivity;
    }

    public void initHelloTextView(TextView textView) {
        activityMainText = textView;
        activityMainText.setOnClickListener(view -> {
            if (timerActivationFirst) timerAnimation.cancel();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initMainBox(ConstraintLayout mainBox) {
        mainBox.setOnTouchListener((view, motionEvent) -> {

            if (timerActivationFirst) timerAnimation.cancel();

            String country = Locale.getDefault().getLanguage();
            if(country.equals("US")){
                activityMainText.setTextColor(Color.BLUE);
            } else activityMainText.setTextColor(Color.RED);

            startMoveText(mainBox.getHeight());

            move(motionEvent.getX(), motionEvent.getY());
            return false;
        });
    }

    private void move(float x, float y) {
        activityMainText.setX(x - activityMainText.getWidth() / 2);
        activityMainText.setY(y - activityMainText.getHeight() / 2);
    }

    private void startMoveText(float sectorAnimation) {
        TranslateAnimation animationPause = new TranslateAnimation(
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0);
        animationPause.setDuration(TIME_DELAY);
        animationPause.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {
            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                directionAnimation(sectorAnimation);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {
            }
        });
        activityMainText.startAnimation(animationPause);
    }

    private void directionAnimation(float sectorAnimation) {
        timerActivationFirst = true;

        timerAnimation = new Timer(true);
        timerAnimation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (activityMainText.getY() + activityMainText.getHeight() <= sectorAnimation && downMove) {
                    activityMainText.setY(activityMainText.getY() + MOVE_STEP);
                } else {
                    downMove = false;
                }

                if (activityMainText.getY() >= 0 && downMove == false) {
                    activityMainText.setY(activityMainText.getY() - MOVE_STEP);
                } else {
                    downMove = true;
                }
            }
        }, 0, PERIOD_FOR_TIMER_ANIMATION);
    }
}