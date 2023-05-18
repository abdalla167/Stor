package com.example.stor.db.offline.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Solidger
    (
    @PrimaryKey(autoGenerate = true)
        val ID_Solidger:Int,
    val solidgerName : String,
    val haveAccess:Boolean,
    var currentmoneyTaresha:Double=0.0
)