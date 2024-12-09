package com.example.girinusa.activity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteItem(
    val image: Int,
    val nameRoute: String
): Parcelable
