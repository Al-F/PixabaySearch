package com.example.pixabaysearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import kotlinx.android.synthetic.main.search_image_item.view.*
import kotlin.properties.Delegates

class ImageAdapter : RecyclerView.Adapter<ImageItemViewHolder>() {

    var renderables: List<ImageModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ImageItemViewHolder(view)
    }

    override fun getItemCount(): Int = renderables.size

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.bind(renderables[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.search_image_item
    }
}
