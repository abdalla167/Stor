package com.example.stor.home.gard.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.stor.R
import com.example.stor.home.proudects.product_deatails.adapters.MyPagerAdapter_details
import com.example.stor.home.proudects.product_deatails.show_all_statistic.Statistic_All_Fragment
import com.example.stor.home.proudects.product_deatails.show_details.Show_Fragment_deatalis
import com.example.stor.home.proudects.product_deatails.show_statistc_one.StatistecFragment
import com.google.android.material.tabs.TabLayout


class MainFragmentHaveViewPgaer : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_main_have_view_pgaer, container, false)
        var tab_viewpager =view. findViewById<ViewPager>(R.id.tab_viewpager_gard)
        var tab_tablayout =view.  findViewById<TabLayout>(R.id.tab_tablayout_gard)

        var adapter: MyPagerAdapter_details = MyPagerAdapter_details(childFragmentManager)
        adapter.addFragment(Gard_FRagment(),"اضافه جرد")
        adapter.addFragment(Show_Final_Result(), "اظهار النتائج")
        tab_viewpager.setAdapter(adapter)
        tab_tablayout.setupWithViewPager(tab_viewpager)

        return view

    }



}