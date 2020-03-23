package com.example.pixabaysearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.uiModel.ImageModel


class MainActivity : AppCompatActivity(), ImageListFragment.OnImageSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_layout, ImageListFragment.newInstance(), "imageList").commit()
        }
    }

    override fun onImageSelected(imageModel: ImageModel) {
        val imageDetailsFragment = ImageDetailsFragment.newInstance(imageModel)
        supportFragmentManager.beginTransaction().replace(R.id.root_layout, imageDetailsFragment, "imageDetails")
            .addToBackStack(null).commit()
    }


}