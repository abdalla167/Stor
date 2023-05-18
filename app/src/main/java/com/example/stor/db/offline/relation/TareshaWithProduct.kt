package com.example.stor.db.offline.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.stor.db.offline.tables.Taresha

data class TareshaWithProduct
    (
    @Embedded
val taresha: Taresha,
    @Relation
    (
    parentColumn ="ID_Taresha",
    entityColumn = "ID_Product",
    associateBy = Junction( ProductAndTarshaCross::class)
)
val products:List<Taresha>


)