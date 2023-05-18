package com.example.stor.home.taresha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.tables.Solidger

class SoldgerAdapterTaresha ( private var mList: List<Solidger>): RecyclerView.Adapter<SoldgerAdapterTaresha.SolgerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolgerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_soldger_taresha, parent, false)

        return SolgerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: SolgerViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.name_solger.text = ItemsViewModel.solidgerName.toString()
        holder.count_money.text = ItemsViewModel.currentmoneyTaresha.toString()



    }
    // Holds the views for adding it to image and text
    class SolgerViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name_solger: TextView = itemView.findViewById(R.id.name_soldger_taresha)
        val count_money: TextView = itemView.findViewById(R.id.money_soldger_taresha)


    }


    // method for filtering our recyclerview items.
 public   fun filterList(filterlist: List<Solidger>) {
        // below line is to add our filtered
        // list in our course array list.
        mList = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }




}

