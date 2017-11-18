package com.example.arifluthfiansyah.belajaryuk.ui.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.arifluthfiansyah.belajaryuk.R;

/**
 * Created by Arif Luthfiansyah on 19/10/2017.
 */

public class AnimUtilFab {

    public static Animation getFabOpen(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fab_open);
    }

    public static Animation getFabClose(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fab_close);
    }

    public static Animation getFabRotateForward(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fab_rotate_forward);
    }

    public static Animation getFabRotateBackward(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fab_rotate_backward);
    }
}
