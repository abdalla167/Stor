package com.example.stor.db.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stor.helperFunction.helper.TimestampConverter
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.PlaceSellProduct
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha

@Database(

    entities = [PlaceSellProduct::class, Product::class, Solidger::class, Taresha::class, ProductAndTarshaCross::class, Gards::class],
    version = 1,exportSchema = false
)
@TypeConverters(TimestampConverter::class)
abstract class StorDataBase : RoomDatabase() {
    abstract val storDao: StorDao

    companion object {
        @Volatile
        private var INSTANCE: StorDataBase? = null
        fun getIntsance(context: Context): StorDataBase {
            synchronized(this)
            {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StorDataBase::class.java,
                    "stordatabase"
                    ).build().also {
                        INSTANCE =it
                }
            }
        }

    }

}
