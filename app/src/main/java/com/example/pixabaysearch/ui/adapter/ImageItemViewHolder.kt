package com.example.pixabaysearch.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.model.ImageModel
import com.example.pixabaysearch.ui.utils.loadImage

class ImageItemViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
    fun bind(viewModel: ImageModel) {
        item.findViewById<ImageView>(R.id.preview_image).loadImage(viewModel.url)
        item.findViewById<TextView>(R.id.likes_count_preview).text = viewModel.likes.toString()
        item.findViewById<TextView>(R.id.user_name_preview).text = viewModel.userName
        item.findViewById<TextView>(R.id.tags_preview).text = viewModel.tags
    }
}