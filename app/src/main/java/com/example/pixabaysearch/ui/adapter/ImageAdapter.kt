package com.example.pixabaysearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.uiModel.ImageModel
import javax.inject.Inject
import kotlin.properties.Delegates

class ImageAdapter @Inject constructor() : RecyclerView.Adapter<ImageItemViewHolder>() {

    private var imageSelectedForExpantion = MutableLiveData<ImageModel>()
    fun observeSelectedForExpantion(): LiveData<ImageModel> = imageSelectedForExpantion

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
