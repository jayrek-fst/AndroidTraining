package com.fs.jayrek.trainingtask.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String){
    Glide.with(view).load(url).circleCrop().into(view)
}
