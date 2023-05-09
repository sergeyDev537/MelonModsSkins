package com.playground.modmelonskins.extensions

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
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
import com.google.android.material.snackbar.Snackbar
import com.playground.modmelonskins.adapters.base.BaseAdapter
import com.playground.modmelonskins.domain.entities.ModEntity
import com.playground.modmelonskins.domain.entities.SkinEntity

fun ImageView.loadImage(context: Context, pathImage: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
    Glide.with(context)
        .load(pathImage)
        .apply(options)
        .into(this)
}

fun ConstraintLayout.loadImage(context: Context, pathImage: String) {
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

fun Long.convertDpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

fun Context.isOnline(): Boolean {
    val connManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
        networkCapabilities != null
    } else {
        // below Marshmallow
        val activeNetwork = connManager.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting == true && activeNetwork.isAvailable
    }
}

fun List<Any>.addNativeItems(): MutableList<Any> {
    var listNatives: MutableList<Any> = mutableListOf()
    var positionNative = 1
    for (position in 0 until this.size) {
        //when(this[position]) {
//            is ModEntity -> {
        if (position == 0) {
            listNatives.add(this[position])
        } else if (position == 1) {
            positionNative = position + 3
            listNatives.add(Any())
            listNatives.add(this[position])
        }
        else if (position == positionNative){
            listNatives.add(Any())
            listNatives.add(this[position])
        }
        else {
            listNatives.add(this[position])
        }






//        else if (position % 3 == 0) {
//            listNatives.add(Any())
//            listNatives.add(this[position])
//        } else {
//            listNatives.add(this[position])
//        }
//            }
//            is SkinEntity -> {
//                if (position in 2..2) {
//                    listNatives.add(Any())
//                } else if (position % 3 == 0) {
//                    listNatives.add(Any())
//                } else {
//                    listNatives.add(this[position])
//                }
//            }
//        }
    }
    return listNatives
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}