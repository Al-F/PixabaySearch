package com.example.pixabaysearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private val viewModel: ImageListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ImageListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedImage.let {imageModel ->
            view.findViewById<ImageView>(R.id.full_size_image).loadImage(imageModel.fullSizeUrl)
            view.findViewById<TextView>(R.id.user_name_text_details_fragment).text =
                imageModel.userName
            view.findViewById<TextView>(R.id.likes_count_details_fragment).text =
                imageModel.likes.toString()
            view.findViewById<TextView>(R.id.favourites_count_details_fragment).text =
                imageModel.favourites.toString()
            view.findViewById<TextView>(R.id.comments_count_details_fragment).text =
                imageModel.comments.toString()
            view.findViewById<TextView>(R.id.tags_details_fragment).text = imageModel.tags
        }
    }
}
