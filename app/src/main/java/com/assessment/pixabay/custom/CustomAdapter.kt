package com.assessment.pixabay.custom

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class CustomAdapter {

    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context)
                    .load(url)
                    .into(view)
        }
    }
}