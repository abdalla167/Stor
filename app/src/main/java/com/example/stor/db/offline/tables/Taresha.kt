package com.example.stor.db.offline.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.stor.helperFunction.helper.TimestampConverter
import java.util.*

@Entity
data class Taresha (

    @PrimaryKey(autoGenerate = true)
    val ID_Taresha:Int = 0,
    @ColumnInfo(name = "date")
    @field:TypeConverters(TimestampConverter::class)
    val date: Date ,
    val ID_Solidger:Int,
    val money:Double
        )