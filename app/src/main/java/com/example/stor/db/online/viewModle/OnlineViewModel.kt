package com.example.stor.db.online.viewModle

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.online.repsatroy.OnlineRepo
import com.example.stor.home.proudects.show_all_product.repositrory.TareshaRepostary
import com.google.gson.Gson
import kotlinx.coroutines.launch

class OnlineViewModel (application: Application) :    AndroidViewModel(application)
{




    lateinit var allProduct: LiveData<List<Product>>
    lateinit var allSoldger: LiveData<List<Solidger>>
    lateinit var GardsProductDay: LiveData<List<Gards>>

    val repository: OnlineRepo
    val dao = StorDataBase.getIntsance(application).storDao    // initialize dao, repository and all events
    init {
        repository = OnlineRepo(dao)
    }


//    fun setDataOnline()
//    {
//        GardsProductDay=repository.getDataGard()
//
//        val gson = Gson()
//        val json = gson.toJson(GardsProductDay)
//        repository.uploadDataGard(json)
//
//    }

    fun getAllGardOnline()
    {

    }

    //get data offline

    //uploadData
    val dataGardOnline: LiveData<List<Gards>> = repository.getDataGard()
    private val _uploadStatus = MutableLiveData<Boolean>()
    val uploadStatus: LiveData<Boolean>
        get() = _uploadStatus

    fun uploadData() {

        viewModelScope.launch {

          repository.uploadDataGard( repository.getAllGared())


        }
    }



}