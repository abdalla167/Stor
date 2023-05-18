package com.example.stor.home.taresha.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.stor.R
import com.example.stor.home.gard.view.Gard_FRagment
import com.example.stor.home.gard.view.Show_Final_Result
import com.example.stor.home.proudects.product_deatails.adapters.MyPagerAdapter_details
import com.example.stor.home.proudects.show_all_product.viewmodel.TareshaViewModel
import com.google.android.material.tabs.TabLayout

class MainFragmentHaveTaresha : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view= inflater.inflate(R.layout.fragment_main_have_taresha, container, false)
        var tab_viewpager =view. findViewById<ViewPager>(R.id.tab_viewpager_taresha)
        var tab_tablayout =view.  findViewById<TabLayout>(R.id.tab_tablayout_taresha)


        var adapter: MyPagerAdapter_details = MyPagerAdapter_details(childFragmentManager)
        adapter.addFragment(Taresha_FRagemt(),"اضافه تاريشا")
        adapter.addFragment(FragementTareshaListSoldger(), "اظهار تاريشا")
        tab_viewpager.setAdapter(adapter)
        tab_tablayout.setupWithViewPager(tab_viewpager)

        return view
    }


}