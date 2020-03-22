package com.example.pixabaysearch.ui

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.ui.MainActivity.Utility.calculateNoOfColumns
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

        setupRecyclerView(applicationContext)
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

    private fun setupRecyclerView(context: Context){
        recyclerView.layoutManager = GridLayoutManager(this, calculateNoOfColumns(context = context, columnWidthDp = 180F))
        recyclerView.adapter = adapter
    }

    object Utility {
        fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int { // For example columnWidthdp=180
            val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            return (screenWidthDp / columnWidthDp - 0.2).toInt()
        }
    }
}