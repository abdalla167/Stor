package com.example.stor.home.proudects.show_all_product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.tables.Product

class ProductsAdapters (val eventClickInterface: EventClickInterface,private val mList: List<Product>): RecyclerView.Adapter<ProductsAdapters.ProductViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.name_Product.text = ItemsViewModel.name.toString()
        holder.count_Avalbel.text = ItemsViewModel.currentCount.toString()
        holder.price_buy.text = ItemsViewModel.priceBuy.toString()
        holder.price_sill.text = ItemsViewModel.PriceSell.toString()
        holder.benfit.text = (ItemsViewModel.PriceSell-ItemsViewModel.priceBuy).toString()

        holder.itemView.setOnClickListener {
            // call eventClickInterface.onEventClick() and pass position to it.
            eventClickInterface.onEventClick(mList.get(position))


        }

    }
    // Holds the views for adding it to image and text
    class ProductViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name_Product: TextView = itemView.findViewById(R.id.name_Product_id_text)
        val count_Avalbel: TextView = itemView.findViewById(R.id.proudect_count_id_item)
        val price_buy: TextView = itemView.findViewById(R.id.proudect_price_buy_id_item)
        val price_sill: TextView = itemView.findViewById(R.id.proudect_price_sell_id_item)
        val benfit: TextView = itemView.findViewById(R.id.proudect_price_benfit_id_item)

    }
}



interface EventClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onEventClick(product: Product)
}
