package com.example.traveler

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
//    val description: String,
    val rating: String,
    val photo: Int,
    val name: String,
    val location: String,
//    val price: String,
) : Parcelable
