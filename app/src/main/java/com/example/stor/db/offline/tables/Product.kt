package com.example.stor.db.offline.tables

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product(

    @PrimaryKey(autoGenerate = true)
    val ID_Product:Int? = null,
    val name:String,
    val priceBuy: Double,
    val PriceSell: Double,
    var StartCount:Int,
    var currentCount:Int
        ) : Parcelable