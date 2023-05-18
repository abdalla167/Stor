package com.example.stor.home.proudects.show_all_product.repositrory

import androidx.lifecycle.LiveData
import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha

class TareshaRepostary(private val storDao: StorDao)
{



    fun getAllProduct():LiveData<List<Product>> = storDao.getAllProudects()
    suspend  fun insertTaresha(taresha: Taresha):Long=storDao.insertTaresha(taresha)
    suspend fun insertCrossTareshaProduct(cross: ProductAndTarshaCross)=storDao.insertproductTarshaCross(cross)
    suspend fun updateMonyTareshaForSoldger(id:Int,money:Double)=storDao.updateSoldgerMony(id,money)
     fun getAllSoldgerThatHaveTaresha():LiveData<List<Solidger>> =storDao.getAllSolidgerHaveTaresha()




}