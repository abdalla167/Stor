package com.example.stor.home.gard.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.home.gard.model.GardModel

class ConferAdapter ( private val mList: List<GardModel>): RecyclerView.Adapter<ConferAdapter.GardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_conferm_gard, parent, false)

        return GardViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: GardViewHolder, position: Int) {
        holder.setIsRecyclable(false);

        val ItemsViewModel = mList.get(position)
        holder.name_Product.text = ItemsViewModel.name.toString()
        holder.countproduct.text=(ItemsViewModel.count.toString())

    }

    fun retrieveData(): List<GardModel?>? {
        return mList
    }

    // Holds the views for adding it to image and text
    class GardViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name_Product: TextView = itemView.findViewById(R.id.name_product_recycler_custom)
        val countproduct: TextView =itemView.findViewById(R.id.count_product_recycler_custom)

    }
}