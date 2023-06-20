package com.example.liquidpaytest.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.liquidpaytest.R
import com.example.liquidpaytest.presentation.core.UiState

@BindingAdapter(value = ["glideUrl", "glideFallBackDrawable", "glideCircular"], requireAll = false)
fun loadImageUrl(view: ImageView, source: String?, drawable: Drawable?, isCircular: Boolean?) {
    val loadingProgress = CircularProgressDrawable(view.context).apply {
        strokeWidth = 2f
        centerRadius = 10f
        start()
    }
    Glide.with(view)
        .load(source)
        .placeholder(loadingProgress)
        .fallback(drawable ?: ContextCompat.getDrawable(view.context, R.drawable.baseline_image_24))
        .let {
            if (isCircular == true) {
                it.circleCrop()
            } else {
                it
            }
        }.into(view)
}

@BindingAdapter("isRefreshing")
fun checkIsRefreshing(view: SwipeRefreshLayout, uiState: UiState?) {
    view.isRefreshing = uiState is UiState.Loading
}

@BindingAdapter("swipeRefreshListener")
fun setSwipeRefreshListener(view: SwipeRefreshLayout, onRefresh: () -> Unit) {
    view.setOnRefreshListener { onRefresh() }
}

@BindingAdapter("isVisible")
fun setIsVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
