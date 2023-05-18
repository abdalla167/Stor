package com.example.stor.home.proudects.show_all_product.repositrory

import androidx.lifecycle.LiveData
import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.gard.model.FinalResult
import java.util.Date

class GardRepostary(private val storDao: StorDao)
{

    lateinit var gardDay: LiveData<FinalResult>


    fun getAllProduct():LiveData<List<Product>> = storDao.getAllProudects()
    suspend fun InsertGArd(gards: Gards)=storDao.insertGard(gards)
    suspend fun UpdateProduct(product: Product)=storDao.updateProduct(product)
    /////
     fun GetgardDayTotalSell(date:Date)=storDao.getgardDayTotalSell(date)
     fun GettareshaDay(date:Date)=storDao.gettareshaDay(date)
     fun GetgardDay(date:Date)= storDao.getgardDay(date)
     fun GetTotalCah(date:Date)=storDao.getgardDayTotalCash(date)
   suspend  fun getProductUsingIdName(Id:Int)=storDao.getNameById(Id)



}