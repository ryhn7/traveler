package com.example.traveler

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val name: String,
    val description: String,
    val photo: String,
    val location: String,
    val rating: String,
    val price: String,
) : Parcelable
