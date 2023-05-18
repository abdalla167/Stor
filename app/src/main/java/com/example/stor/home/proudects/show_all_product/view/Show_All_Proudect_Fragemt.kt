package com.example.stor.home.proudects.show_all_product.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.sharedperffrence.ShardPreffrence
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.proudects.ShardViewModel
import com.example.stor.home.proudects.add_new_product.view.Add_new_Product_Activti
import com.example.stor.home.proudects.product_deatails.view.MainActivity_Product_deatalis
import com.example.stor.home.proudects.show_all_product.adapter.EventClickInterface
import com.example.stor.home.proudects.show_all_product.adapter.ProductsAdapters
import com.example.stor.home.proudects.show_all_product.viewmodel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Show_All_Proudect_Fragemt : Fragment() , EventClickInterface  {

    private val viewModel: ProductsViewModel by navGraphViewModels(R.id.nav_graph)
    private lateinit var productsAdapters: ProductsAdapters
    private val shardViewModel:ShardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_show__all__proudect__fragemt, container, false)
        var recyclerView:RecyclerView
        viewModel.allProduct.observe(viewLifecycleOwner, Observer {
            productsAdapters=ProductsAdapters(this,it)
            recyclerView=view.findViewById(R.id.recyler_Product)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = productsAdapters
            }
        })
        var button_add_product=view.findViewById<FloatingActionButton>(R.id.add_new_Proudect_id)
        button_add_product.setOnClickListener {

            var intent=Intent(requireContext(),Add_new_Product_Activti::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onEventClick(product: Product) {

            activity?.let {
                shardViewModel.sendCurrItems(product)
                shardViewModel.getCurrItem().observe(viewLifecycleOwner, Observer {

                    it.name
                })
                val intent = Intent(it, MainActivity_Product_deatalis::class.java)

                // is a title of tab
                var sh= ShardPreffrence(requireContext())
                sh.deletProduct()
                sh.saveProduct(product)
                sh.getProduct()
                it.startActivity(intent)
            }

        }

}

