package com.example.pixabaysearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.Status
import com.example.pixabaysearch.ui.adapter.ImageAdapter
import com.example.pixabaysearch.ui.uiModel.ImageModel
import com.example.pixabaysearch.ui.viewModel.ImageListViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    @Inject
    lateinit var adapter: ImageAdapter

    private val viewModel: ImageListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ImageListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_list_fragment, container, false)
        val activity = activity as Context

        adapter.setOnClickListener { imageModel -> onImageSelected(imageModel) }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver(view)

        view.findViewById<TextInputEditText>(R.id.search_image)
            .setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.fetchImages(v.text.toString())
                    v.text = ""
                    v.clearFocus()
                    false
                } else {
                    false
                }
            }
    }

    private fun onImageSelected(imageModel: ImageModel) {
        viewModel.selectedImage = imageModel
        findNavController().navigate(R.id.action_imageListFragment_to_imageDetailsFragment)
    }

    private fun setupObserver(view: View) {
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val emptyLayout = view.findViewById<LinearLayout>(R.id.empty_layout)
        viewModel.response.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    adapter.data = resource.data
                    progressBar.visibility = View.GONE
                    if (resource.data.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                    } else emptyLayout.visibility = View.GONE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                    view.findViewById<TextView>(R.id.empty_layout_message).text = resource.message
                    Toast.makeText(requireContext(), resource.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}
