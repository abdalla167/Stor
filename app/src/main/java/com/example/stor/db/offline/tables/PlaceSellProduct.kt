package com.example.stor.db.offline.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaceSellProduct (

    @PrimaryKey (autoGenerate = true)
    val ID_Place:Int,
    val name:String,
    val worker:String
    )