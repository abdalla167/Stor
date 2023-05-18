package com.example.stor.db.offline.sharedperffrence

import android.content.Context
import android.content.SharedPreferences
import com.example.stor.db.offline.tables.Product
import com.google.gson.Gson

class ShardPreffrence (val context: Context){

    private val PREFS_NAME = "sharedpref"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveProduct(product: Product) {
        sharedPref.edit().putString("product", Gson().toJson(product)).apply()
    }

    fun getProduct(): Product? {
        val data = sharedPref.getString("product", null)
        if (data == null) {
            return null
        }
        return Gson().fromJson(data, Product::class.java)
    }
    fun deletProduct()
    {
        sharedPref.edit().remove("product")
    }
}