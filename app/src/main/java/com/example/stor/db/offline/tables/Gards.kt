package com.example.stor.db.offline.tables

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.stor.helperFunction.helper.TimestampConverter
import java.util.*

@Entity
data class Gards (

    @PrimaryKey(autoGenerate = true)
    val ID_Gard:Int? = null ,
    val ID_Product:Int,
    val Start_Day:Int,
    val End_day:Int,
    val Total_Sell:Double,
    val Total_mony:Double,
    @ColumnInfo(name = "Date_Sell")
    @field:TypeConverters(TimestampConverter::class)
    val Date_Sell: Date
        )