package com.playground.modmelonskins.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


fun ImageView.loadImage(context: Context, pathImage: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()
    Glide.with(context)
        .load(pathImage)
        .apply(options)
        .into(this)
}

fun ConstraintLayout.loadImage(context: Context, pathImage: String){
    Glide.with(context)
        .load(pathImage)
        .into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                @Nullable transition: Transition<in Drawable?>?,
            ) {
                Log.d("TAGING", "onResourceReady")
                this@loadImage.setBackground(resource)
            }

            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
        })
}

fun View.showSnackBar(message: String){

}