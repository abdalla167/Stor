package com.example.stor.db.online.repsatroy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stor.db.offline.StorDao
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.util.Date

class OnlineRepo(private var storDao: StorDao) {


    private val firestore = FirebaseFirestore.getInstance()


    fun getAllProduct(): LiveData<List<Product>> = storDao.getAllProudects()
    fun getAllSoldgerThatHaveTaresha():LiveData<List<Solidger>> =storDao.getAllSolidgerHaveTaresha()
    fun getAllCrossTareshaProduct():LiveData<List<ProductAndTarshaCross>> = storDao.getAllCross()
    fun getAllTaresha():LiveData<List<Taresha>> = storDao.getAllTaresha()
     suspend  fun getAllGared():List<Gards> = storDao.getAllgardDay()





    //uploadData json on firebase
    /////


    fun uploadDataGard(gardList:List<Gards>) {
        val gson = Gson()
        val json = gson.toJson(gardList)
        firestore.collection("Gard").add(gardList).addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    //getdata json to firebase
    /////

    fun getDataGard(): LiveData<List<Gards>> {
        val data = MutableLiveData<List<Gards>>()
        firestore.collection("Gard").get()
            .addOnSuccessListener { querySnapshot ->
                val dataList = mutableListOf<Gards>()
                for (document in querySnapshot.documents) {
                    val ID_Gard = document.get("ID_Gard")
                    val ID_Product = document.get("ID_Product")
                    val Start_Day = document.get("Start_Day")
                    val End_day = document.get("End_day")
                    val Total_Sell = document.get("Total_Sell")
                    val Total_mony = document.get("Total_mony")
                    val Date_Sell = document.get("Date_Sell")
                    dataList.add(Gards(ID_Gard as Int?, ID_Product as Int, Start_Day as Int, End_day as Int, Total_Sell as Double, Total_mony as Double, Date_Sell as Date
                    ))
                }
                data.value = dataList
            }
        return data
    }


}