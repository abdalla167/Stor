package com.example.stor.db.offline.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha

data class TareshaWithSolidger
    (
    @Embedded
val taresha: Taresha,
    @Relation(
    parentColumn = "ID_Solidger",
    entityColumn = "ID_Solidger"
)
val solidger: List <Solidger>
)