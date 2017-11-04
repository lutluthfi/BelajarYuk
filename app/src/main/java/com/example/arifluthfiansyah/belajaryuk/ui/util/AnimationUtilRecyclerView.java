package com.example.arifluthfiansyah.belajaryuk.ui.util;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Arif Luthfiansyah on 21/09/2017.
 */

public class AnimationUtilRecyclerView {

    public static void setAnimation(RecyclerView.ViewHolder viewHolder) {
        ObjectAnimator.ofFloat(viewHolder.itemView, "translationY", 500, 0)
                .setDuration(500)
                .start();
    }
}
