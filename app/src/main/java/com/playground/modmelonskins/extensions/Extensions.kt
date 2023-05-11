package com.playground.modmelonskins.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.playground.modmelonskins.R

fun ImageView.loadImage(context: Context, pathImage: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.image_placeholder)
        .priority(Priority.HIGH)
    Glide.with(context)
        .load(pathImage)
        .apply(options)
        .into(this)
}

fun ConstraintLayout.loadImage(context: Context, pathImage: String) {
    Glide.with(context)
        .load(pathImage)
        .placeholder(R.drawable.image_placeholder)
        .into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable?>?,
            ) {
                Log.d("TAGING", "onResourceReady")
                this@loadImage.setBackground(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}

fun Long.convertDpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

fun List<Any>.addNativeItems(): MutableList<Any> {
    val listNatives: MutableList<Any> = mutableListOf()
    var positionNative = 1
    for (position in 0 until this.size) {
        when (position) {
            0 -> {
                listNatives.add(this[position])
            }
            1 -> {
                positionNative = position + 3
                listNatives.add(Any())
                listNatives.add(this[position])
            }
            positionNative -> {
                listNatives.add(Any())
                listNatives.add(this[position])
            }
            else -> {
                listNatives.add(this[position])
            }
        }
    }
    return listNatives
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.rateApp(){
    try {
        startActivity(
            Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=${this.packageName}")
        )
        )
    } catch (e : ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")
            )
        )
    }
}

fun Context.openUrl(url: String){
    val customTabsIntent = CustomTabsIntent.Builder().build()
    customTabsIntent.launchUrl(this, Uri.parse(url))
}