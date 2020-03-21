package com.example.pixabaysearch.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pixabaysearch.R
import com.example.pixabaysearch.data.api.PixabayService
import com.example.pixabaysearch.model.PixabayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://pixabay.com/"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(PixabayService::class.java)
//        val call = service.listImages("15612596-c20b2282ebade3a0e69a30c78", "fruits")
//
//        val button = findViewById<Button>(R.id.search_images)
//        val imageView = findViewById<ImageView>(R.id.imageView)
//
//        button.setOnClickListener {
//            call.clone().enqueue(object : Callback<PixabayResponse> {
//                override fun onResponse(call: Call<PixabayResponse>, response: Response<PixabayResponse>) {
//                    if (response.code() == 200) {
//                        val pixabayResponse = response.body()!!
//
//                        imageView.loadImage(pixabayResponse.hits.first().previewURL)
//                    }
//                }
//
//                override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
//
//                }
//            })
//        }
//    }

    }
}