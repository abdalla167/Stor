package com.example.stor.home.proudects.add_new_product.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.add_new_product.repositary.ReposatryAddProduct
import kotlinx.coroutines.launch

     class ViewModelAddProduct:ViewModel()
{


    var checknumber=MutableLiveData<Int>()






      fun checkArticle(product_name: String?, storDao: StorDao, product: Product) {

          viewModelScope.launch {
              var repository= ReposatryAddProduct(storDao)
          //    checknumber.postValue(repository.checkProductName(product_name!!))
              if(repository.checkProductName(product_name!!)==0)
              {
                  repository.insertNewProduct(product)
                  checknumber.postValue(1)

              }
              else
              {
                  checknumber.postValue(0)

              }
          }

    }
}