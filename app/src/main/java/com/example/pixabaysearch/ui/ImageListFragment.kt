package com.example.pixabaysearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.ui.adapter.ImageAdapter
import com.example.pixabaysearch.ui.uiModel.ImageModel
import com.example.pixabaysearch.ui.viewModel.ImageListViewModel
import kotlinx.android.synthetic.main.image_list_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageListFragment : Fragment() {

    private val adapter: ImageAdapter = ImageAdapter()
    private val viewModel: ImageListViewModel by lazy { ViewModelProvider(this).get(
        ImageListViewModel::class.java) }

    private lateinit var service: PixabayService
    private lateinit var imageSelectedListener: OnImageSelected
    private lateinit var observer : Observer<ImageModel>

    companion object {
        fun newInstance() = ImageListFragment()
        private const val BASE_URL = "https://pixabay.com/"

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnImageSelected){
            imageSelectedListener = context
        } else{
            throw ClassCastException("$context must implement OnImageSelected.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_list_fragment, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PixabayService::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        search_image.setOnEditorActionListener { v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                viewModel.getImages(v.text.toString(), service)
                v.text = ""
                v.clearFocus()
                false
            } else {
                false
            }
        }
        viewModel.observeImages().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.renderables = it
            }
        })
        viewModel.observeFailure().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Error occurred, try again later", Toast.LENGTH_LONG).show()
        })
        if (viewModel.images.value.isNullOrEmpty()) {
            viewModel.getImages("fruits", service)
        }

        adapter.observeSelectedForExpantion().observe(viewLifecycleOwner, Observer {
            imageSelectedListener.onImageSelected(it)
        })

    }

    interface OnImageSelected{
        fun onImageSelected(imageModel: ImageModel)
    }
}
