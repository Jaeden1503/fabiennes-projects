package com.example.safehome.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Review(
    @StringRes val name: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val description: Int,
)
