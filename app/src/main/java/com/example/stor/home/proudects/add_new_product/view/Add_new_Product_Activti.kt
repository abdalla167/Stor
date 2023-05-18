package com.example.stor.home.proudects.add_new_product.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.stor.R
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.add_new_product.viewmodel.ViewModelAddProduct


class Add_new_Product_Activti : AppCompatActivity() {
    val dao = StorDataBase.getIntsance(this).storDao

    val viewModelAddProduct: ViewModelAddProduct by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_product_activti)

        var   name_product_detalies_id=findViewById<TextView>(R.id.edit_add_name_product)
        var   detalis_price_buy_card_id  =  findViewById<TextView>(R.id.edit_add_price_buy_product)
        var   detalis_price_sell_card_id  = findViewById<TextView>(R.id.edit_add_price_sell_product)
        var   detalis_count_card_id = findViewById<TextView>(R.id.edit_add_count_product)
        var   buttonAddProduct = findViewById<TextView>(R.id.button_add_product_id)


        buttonAddProduct.setOnClickListener{

            if(name_product_detalies_id.text.length==0||detalis_price_buy_card_id.text.length==0
                ||detalis_price_sell_card_id.text.length==0||detalis_count_card_id.text.length==0)
            {

                Toast.makeText(this,"برجاء اكمال البيانات",Toast.LENGTH_LONG).show()

            }
            else
            {

                    viewModelAddProduct.checkArticle(name_product_detalies_id.text.toString(),dao,
                        Product(name=name_product_detalies_id.text.toString(),
                        priceBuy= ( detalis_price_buy_card_id.text.toString()).toDouble() ,
                        PriceSell=(detalis_price_sell_card_id.text.toString()).toDouble(),
                        StartCount=(detalis_count_card_id.text.toString()).toInt(),
                        currentCount =(detalis_count_card_id.text.toString()).toInt()
                    )
                    )
                    viewModelAddProduct.checknumber?.observe(this@Add_new_Product_Activti, Observer {

                                if (it != null) {
                                    if(it==0) {
                                        Toast.makeText(this@Add_new_Product_Activti, "هذا المنتج موجود حاليا", Toast.LENGTH_SHORT).show()
                                    }
                                    else
                                    {

                                        Toast.makeText(this@Add_new_Product_Activti, "تم اضافه المنتج بنجاح", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            })



            }



        }


    }
}