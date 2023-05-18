package com.example.stor.db.offline.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product

data class GardsWithProduct
        (
    @Embedded
    val product: Product,
    @Relation
            (
            parentColumn = "ID_Product",
            entityColumn = "ID_Product"
    )
    val gardes: List <Gards>

)