package com.example.pixabaysearch.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_image_item.view.*

class ImageItemViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
    fun bind(viewModel: ImageModel) {
        item.preview_image.loadImage(viewModel.url)
        item.likes_count_preview.text = viewModel.likes.toString()
        item.user_name_preview.text = viewModel.userName
        item.tags_preview.text = viewModel.tags
    }
}