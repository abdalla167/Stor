package com.example.stor.home.proudects.add_new_product.repositary

import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.tables.Product


class ReposatryAddProduct(private val storDao: StorDao) {
    suspend fun insertNewProduct(product: Product)=storDao.insertProduct(product)
      suspend fun checkProductName(product: String): Int {

        return storDao.checkProduct(product)

    }
}