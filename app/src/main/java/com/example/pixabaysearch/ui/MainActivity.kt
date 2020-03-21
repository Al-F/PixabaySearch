package com.example.pixabaysearch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.PixabayService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pixabay.com/"

class MainActivity : AppCompatActivity() {

    private val adapter: ImageAdapter  = ImageAdapter()
    private val viewModel: ImageViewModel = ImageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PixabayService::class.java)

        viewModel.observeImages().observe(this, Observer {
            it?.let {
                adapter.renderables = it
            }
        })
        viewModel.observeFailure().observe(this, Observer {
            Toast.makeText(this, "Error occurred, try again later", Toast.LENGTH_LONG).show()
        })
        viewModel.getImages("fruits", service)
    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
    }
}