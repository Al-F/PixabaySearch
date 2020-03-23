package com.example.pixabaysearch.ui

import android.content.Context
import android.util.DisplayMetrics

internal fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
    val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp - 0.2).toInt()
}