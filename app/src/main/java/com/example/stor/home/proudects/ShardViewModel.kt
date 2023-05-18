package com.example.stor.home.proudects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stor.db.offline.tables.Product

class ShardViewModel : ViewModel()
{
     val currItems: MutableLiveData<Product> =
         MutableLiveData<Product>()

    fun getCurrItem(): LiveData<Product> {
        return currItems
    }

    fun sendCurrItems(items: Product) {
        currItems.postValue(items)
    }

}