package com.example.pixabaysearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.uiModel.ImageModel
import javax.inject.Inject
import kotlin.properties.Delegates

class ImageAdapter @Inject constructor() :
    RecyclerView.Adapter<ImageItemViewHolder>() {

    private var onClickListener: ((ImageModel) -> Unit)? = null

    fun setOnClickListener(onClickListener: (ImageModel) -> Unit) {
        this.onClickListener = onClickListener;
    }

    var data: List<ImageModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ImageItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener?.let { it1 -> it1(item) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.search_image_item
    }
}
