package com.example.pixabaysearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pixabaysearch.R
import com.example.pixabaysearch.ui.uiModel.ImageModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}