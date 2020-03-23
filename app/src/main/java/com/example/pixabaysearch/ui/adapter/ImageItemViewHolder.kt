package com.example.pixabaysearch.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.ui.uiModel.ImageModel
import com.example.pixabaysearch.ui.utils.loadImage
import kotlinx.android.synthetic.main.search_image_item.view.*

class ImageItemViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
    fun bind(viewModel: ImageModel) {
        item.preview_image.loadImage(viewModel.url)
        item.likes_count_preview.text = viewModel.likes.toString()
        item.user_name_preview.text = viewModel.userName
        item.tags_preview.text = viewModel.tags
    }
}