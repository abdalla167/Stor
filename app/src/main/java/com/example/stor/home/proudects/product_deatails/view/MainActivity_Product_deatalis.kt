package com.example.stor.home.proudects.product_deatails.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.stor.R
import com.example.stor.db.offline.sharedperffrence.ShardPreffrence
import com.example.stor.home.proudects.ShardViewModel
import com.example.stor.home.proudects.product_deatails.adapters.MyPagerAdapter_details
import com.example.stor.home.proudects.product_deatails.show_all_statistic.Statistic_All_Fragment
import com.example.stor.home.proudects.product_deatails.show_details.Show_Fragment_deatalis
import com.example.stor.home.proudects.product_deatails.show_statistc_one.StatistecFragment
import com.google.android.material.tabs.TabLayout

class MainActivity_Product_deatalis : AppCompatActivity() {

    private val shardViewModel: ShardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_product_deatalis)


        var tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)
        setSupportActionBar(tab_toolbar)



            var adapter: MyPagerAdapter_details = MyPagerAdapter_details(supportFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        var sh= ShardPreffrence(this)

            adapter.addFragment(Show_Fragment_deatalis(sh.getProduct()!!),"تفاصيل المنتج")
            adapter.addFragment(StatistecFragment(), "احصائيات المنتج")
            adapter.addFragment(Statistic_All_Fragment(), "احصائيات المنتجات")
            tab_viewpager.setAdapter(adapter)

        tab_tablayout.setupWithViewPager(tab_viewpager)


    }


}