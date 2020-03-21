package com.example.pixabaysearch.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_image_item.view.*

class ImageItemViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
    fun bind(viewModel: ImageModel) {
        item.image.loadImage(viewModel.url)
    }
}