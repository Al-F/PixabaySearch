package com.example.pixabaysearch.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.PixabayService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pixabay.com/"

class MainActivity : AppCompatActivity() {

    private val adapter: ImageAdapter = ImageAdapter()
    private val viewModel: ImageViewModel = ImageViewModel()
    private lateinit var service: PixabayService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)

        setupRecyclerView(applicationContext)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PixabayService::class.java)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)
        val menuSearchItem = menu?.findItem(R.id.action_search)
        val searchView = menuSearchItem?.actionView as SearchView
        searchView.queryHint = applicationContext.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null) {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.search_empty_warning),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    viewModel.getImages(query, service)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupRecyclerView(context: Context) {
        recyclerView.layoutManager =
            GridLayoutManager(this, calculateNoOfColumns(context = context, columnWidthDp = 180F))
        recyclerView.adapter = adapter
    }
}