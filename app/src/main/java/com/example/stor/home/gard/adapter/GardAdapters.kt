package com.example.stor.home.proudects.show_all_product.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.home.gard.model.GardModel

class GardAdapters ( private val mList: List<GardModel>,val nameOutLin:String):
    RecyclerView.Adapter<GardAdapters.GardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gard, parent, false)

        return GardViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    override fun onBindViewHolder(holder: GardViewHolder, position: Int) {
        holder.setIsRecyclable(false);

        val ItemsViewModel = mList.get(position)
        holder.name_Product.text = ItemsViewModel.name.toString()
        holder.countproduct.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?)
            {
                if( p0.toString().equals("")||p0.toString()==null )
                {
                    ItemsViewModel.count =0
                }
                else
                {
                    ItemsViewModel.count = p0.toString().toInt()
                }

            }
        })
        holder.countproduct.setText(ItemsViewModel.count.toString())
        holder.nameoutline.setText(nameOutLin+"")
        mList.get(position).count=ItemsViewModel.count
    }
    fun retrieveData(): List<GardModel?>? {
        return mList
    }
    // Holds the views for adding it to image and text
    class GardViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
         val name_Product: TextView = itemView.findViewById(R.id.name_Product_id_text_gard)
         val countproduct:EditText=itemView.findViewById(R.id.edittext_gard_number)
        val nameoutline:TextView =itemView.findViewById(R.id.nameOutLine)

    }

}



