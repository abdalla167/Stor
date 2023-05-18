package com.example.stor.home.proudects.show_all_product.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.show_all_product.repositrory.ProductRepostary
import kotlinx.coroutines.launch

class ProductsViewModel ( application: Application) :    AndroidViewModel(application)
{
    lateinit var allProduct: LiveData<List<Product>>


    val repository: ProductRepostary
    // initialize dao, repository and all events
    init {
        val dao = StorDataBase.getIntsance(application).storDao
        repository = ProductRepostary(dao)
        viewModelScope.launch {
            allProduct = repository.getAllProduct()

        }
    }
}