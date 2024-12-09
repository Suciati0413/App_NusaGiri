package com.example.girinusa.activity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FacilityItem(
    val nameFacility: String,
    val imgFacility: Int
) : Parcelable
