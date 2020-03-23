package com.example.pixabaysearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.uiModel.ImageModel
import kotlin.properties.Delegates

class ImageAdapter : RecyclerView.Adapter<ImageItemViewHolder>() {

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
        holder.item.setOnClickListener {
            val alertDialog = AlertDialog.Builder(it.context).also { builder ->
                builder.setTitle("Open image")
                builder.setMessage("Do you want to see more details?")

                builder.setPositiveButton("Yes") { dialog, which ->
                    this.imageSelectedForExpantion.value = renderables[position]
                    dialog.dismiss()}
                builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            }.create()
            alertDialog.show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.search_image_item
    }
}
