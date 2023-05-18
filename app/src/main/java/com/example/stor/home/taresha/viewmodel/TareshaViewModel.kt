package com.example.stor.home.proudects.show_all_product.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha
import com.example.stor.home.proudects.show_all_product.repositrory.TareshaRepostary
import kotlinx.coroutines.launch


class TareshaViewModel (application: Application) :    AndroidViewModel(application)
{
    lateinit var allProduct: LiveData<List<Product>>
    lateinit var allSoldger:LiveData<List<Solidger>>

    val repository: TareshaRepostary
    val dao = StorDataBase.getIntsance(application).storDao    // initialize dao, repository and all events
    init {

        repository = TareshaRepostary(dao)

    }
    fun getAllProduct(){

        viewModelScope.launch {
            allProduct = repository.getAllProduct()

        }
    }

    suspend  fun insertTaresha(taresha: Taresha):Long
    {



          return  repository.insertTaresha(taresha)


    }
    fun insertCross(cross: ProductAndTarshaCross)
    {


        viewModelScope.launch {

            repository.insertCrossTareshaProduct(cross)
        }

    }

     fun updateMonySoldger(id:Int, money:Double)
    {
    viewModelScope.launch {

        repository.updateMonyTareshaForSoldger(id,money)
    }

    }

    fun  getAllSoldger()
    {

        viewModelScope.launch {

            allSoldger=repository.getAllSoldgerThatHaveTaresha()
        }

    }





}