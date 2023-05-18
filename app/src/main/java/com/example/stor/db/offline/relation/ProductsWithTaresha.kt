package com.example.stor.db.offline.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.stor.db.offline.tables.Product

data class ProductsWithTaresha (
    @Embedded val product: Product,
    @Relation
  (
     parentColumn = "ID_Product",
     entityColumn = "ID_Taresha",
     associateBy = Junction( ProductAndTarshaCross::class)
          )
val products:List<Product>


        )