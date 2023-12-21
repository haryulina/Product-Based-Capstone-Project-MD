package com.user.phisheye.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Infographic(
    val id : Int,
    val name: String,
    val source: String,
    val photo: Int,
    val details: String
) : Parcelable

