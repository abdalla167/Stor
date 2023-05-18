package com.example.stor.home.proudects.show_all_product.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.show_all_product.repositrory.GardRepostary
import kotlinx.coroutines.launch
import java.util.Date


class GardViewModel ( application: Application) :    AndroidViewModel(application)
{
    lateinit var allProduct: LiveData<List<Product>>

    lateinit var totalSell: LiveData<Double>
    lateinit var totalTaresha: LiveData<Double>
    lateinit var totalcash:LiveData<Double>
    lateinit var GardsProductDay: LiveData<List<Gards>>
    val repository: GardRepostary
    val dao = StorDataBase.getIntsance(application).storDao    // initialize dao, repository and all events
    init {

        repository = GardRepostary(dao)
        viewModelScope.launch {
            allProduct = repository.getAllProduct()

        }
    }

     fun insertNewGard(gards: Gards)
    {

        viewModelScope.launch {

            repository.InsertGArd(gards)
        }

    }
    fun updateProduct(product: Product)
    {
        viewModelScope.launch {

            repository.UpdateProduct(product)

        }

    }

    fun GetGardREsult(date: Date){


            totalSell=repository.GetgardDayTotalSell(date)
            GardsProductDay=repository.GetgardDay(date)
            totalTaresha=repository.GettareshaDay(date)
            totalcash=repository.GetTotalCah(date)


     }
   suspend fun getNameusingIdProduct(Id:Int)=repository.getProductUsingIdName(Id)


}