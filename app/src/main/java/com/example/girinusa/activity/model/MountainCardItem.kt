package com.example.girinusa.activity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MountainCardItem(
    val name: String,
    val region: String,
    val price: String,
    val photo: Int,
    val description: String,
    val status: String,
    val elevationHome: String,
    val elevation: String,
    val temperature: String,
    val facility: List<FacilityItem> = emptyList()
) : Parcelable
