package com.example.safehome.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Home(
    @StringRes val address: Int,
    @DrawableRes val imageRes: Int,
    val price: Int,
    @StringRes val location: Int
)
