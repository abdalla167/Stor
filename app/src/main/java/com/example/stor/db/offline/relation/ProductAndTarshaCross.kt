package com.example.stor.db.offline.relation

import androidx.room.Entity

@Entity(primaryKeys = ["ID_Taresha", "ID_Product"])
data class ProductAndTarshaCross (
    val ID_Taresha: Int,
    val ID_Product: Int,
    val countOneProduct:Int
        )