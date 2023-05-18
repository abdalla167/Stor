package com.example.stor.home.taresha.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.home.proudects.show_all_product.viewmodel.TareshaViewModel
import com.example.stor.home.taresha.adapter.SoldgerAdapterTaresha


class FragementTareshaListSoldger : Fragment() {

    private val tareshaViewModel: TareshaViewModel by viewModels()

    private lateinit var soldgerAdapterTaresha:SoldgerAdapterTaresha
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_fragement_taresha_list_soldger, container, false)

        var searchView=view.findViewById<SearchView>(R.id.searchView)
        var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView_soldger_taresha)
        tareshaViewModel.getAllSoldger()
        tareshaViewModel.allSoldger.observe(viewLifecycleOwner, Observer {
            soldgerAdapterTaresha= it.let { it1 -> SoldgerAdapterTaresha(it1) }!!
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = soldgerAdapterTaresha
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(msg: String): Boolean {
                    // inside on query text change method we are
                    // calling a method to filter our recycler view.
                    filter(msg,it )
                    return false
                }
            })
        })







    return view
    }
    private fun filter(text: String,soldgerlist:List<Solidger>) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Solidger> = ArrayList()

        // running a for loop to compare elements.
        for (item in soldgerlist) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.solidgerName.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            soldgerAdapterTaresha.filterList(filteredlist)
        }
    }

}