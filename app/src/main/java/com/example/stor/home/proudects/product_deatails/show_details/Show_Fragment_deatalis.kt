package com.example.stor.home.proudects.product_deatails.show_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.stor.R
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.show_all_product.viewmodel.GardViewModel


class Show_Fragment_deatalis(var product: Product) : Fragment() {

    private val gardViewModel: GardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=inflater.inflate(R.layout.fragment_show__deatalis, container, false)

        var   name_product_detalies_id=view.findViewById<TextView>(R.id.name_product_detalies_id)
        var   detalis_price_buy_card_id  =  view.findViewById<TextView>(R.id.detalis_price_buy_card_id)
        var   detalis_price_sell_card_id  = view.findViewById<TextView>(R.id.detalis_price_sell_card_id)
        var   detalis_All_benfit_card_id = view.findViewById<TextView>(R.id.detalis_All_benfit_card_id)
        var   detalis_count_card_id = view.findViewById<TextView>(R.id.detalis_count_card_id)
        var   detalis_add_more_count=view.findViewById<Button>(R.id.conferm_button_add_more_count)
        var   detalis_edit_get_more=view.findViewById<EditText>(R.id.edit_add_count_product_add_more)

        name_product_detalies_id.setText(product.name)
        detalis_price_buy_card_id.setText(product.priceBuy.toString())
        detalis_price_sell_card_id.setText(product.PriceSell.toString())
        detalis_All_benfit_card_id.setText((product.StartCount*(product.PriceSell-product.priceBuy)).toString())
        detalis_count_card_id.setText(product.StartCount.toString())

        detalis_add_more_count.setOnClickListener {

            if(detalis_edit_get_more.text.length!=0)
            {
                //update product curent count and startcount
                product.StartCount+=detalis_edit_get_more.text.toString().toInt()
                product.currentCount+=detalis_edit_get_more.text.toString().toInt()
                gardViewModel.updateProduct(product)



            }
        }

        return view
    }

}