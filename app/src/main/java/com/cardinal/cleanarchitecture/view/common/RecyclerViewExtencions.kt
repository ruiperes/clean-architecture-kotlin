package com.cardinal.cleanarchitecture.view.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cardinal.cleanarchitecture.R

fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {

    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })
    diff.dispatchUpdatesTo(this)
}

private val DEFAULT_PLACEHOLDER = R.color.colorPrimary
private val DEFAULT_ERROR = R.color.colorPrimaryDark

class Views {}

fun ImageView.loadFromUrl(url: String,
                          placeholder: Int = DEFAULT_PLACEHOLDER,
                          error: Int = DEFAULT_ERROR) =
    loadImage(
        imageView = this,
        url = url,
        placeholder = placeholder,
        error = error
    )

fun ImageView.loadFromUrl(url: String,
                          placeholder: Drawable,
                          error: Drawable) =
    loadImage(
        imageView = this,
        url = url,
        placeholder = placeholder,
        error = error
    )
private fun loadImage(imageView: ImageView,
                      url: String,
                      placeholder: Drawable,
                      error: Drawable) {

    Glide.with(imageView.context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(imageView)
}

private fun loadImage(imageView: ImageView,
                      url: String,
                      placeholder: Int,
                      error: Int) {

    Glide.with(imageView.context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(imageView)
}