package com.bluelinelabs.conductor.demo.changehandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler;

public class FadeInChangeHandler extends AnimatorChangeHandler {

    public FadeInChangeHandler() {
        super(false);
    }

    @NonNull
    @Override
    protected Animator getAnimator(@NonNull ViewGroup container, @Nullable View from, @Nullable View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animator = new AnimatorSet();
        if (to != null && toAddedToContainer) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(to, View.ALPHA, 0, 1);
            anim.setDuration(250);
            animator.play(anim);
        }

        return animator;
    }

    @Override
    protected void resetFromView(@NonNull View from) {

    }
}
