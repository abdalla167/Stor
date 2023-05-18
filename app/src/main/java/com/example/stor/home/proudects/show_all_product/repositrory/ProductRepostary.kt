package com.example.stor.home.proudects.show_all_product.repositrory

import androidx.lifecycle.LiveData
import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.tables.Product

class ProductRepostary(private val storDao: StorDao)
{

    fun getAllProduct():LiveData<List<Product>> = storDao.getAllProudects()

}