package com.example.stor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       val dao = StorDataBase.getIntsance(this).storDao
//        val products= listOf(
//            Product(1,"شيبسي",2.5,3.0,90,80),
//            Product(2,"كانز",7.25,8.0,80,70),
//            Product(3,"كولا",7.25,8.0,100,90),
//            Product(4,"مولتو",2.75,3.5,50,40),
//            Product(5,"لبان",0.5,1.0,100,90),
//            Product(6,"بيبسي",8.0,10.5,100,90),
//            Product(7,"روتانا",2.5,3.0,60,50),
//            Product(8,"معمول",3.5,4.0,100,90),
//            Product(9,"شاي",1.0,2.5,70,60)
//            )
//
//        val solidgers= listOf(
//            Solidger(1,"مصطفي عرفان",true,0.0),
//            Solidger(2,"احمد حجازي",true,0.0),
//            Solidger(3,"ابراهيم علي",true,0.0),
//            Solidger(4,"مصعب احمد",true,0.0),
//            Solidger(5,"علي علي",true,0.0),
//            Solidger(6,"ابراهيم احمد",true,0.0),
//            Solidger(7,"سلامه علي ",true,0.0),
//            Solidger(8,"نور ابراهيم",true,0.0)
//        )
//        val gard= listOf(
//            Gards(ID_Product = 1, Start_Day =90, End_day = 80, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 30.0 ),
//            Gards(ID_Product = 2, Start_Day =80, End_day = 70, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 80.0 ),
//            Gards(ID_Product = 3, Start_Day =100, End_day = 90, Total_Sell = 10.0, Date_Sell =  Date(2023,5, 13), Total_mony = 80.0  ),
//            Gards(ID_Product = 4, Start_Day =50, End_day = 40, Total_Sell = 10.0, Date_Sell = Date(2023, 5, 13), Total_mony = 35.0  ),
//            Gards(ID_Product = 5, Start_Day =100, End_day = 90, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 10.0 ),
//            Gards(ID_Product = 6, Start_Day =100, End_day = 90, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13), Total_mony = 105.0  ),
//            Gards(ID_Product = 7, Start_Day =60, End_day = 50, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 30.0 ),
//            Gards(ID_Product = 8, Start_Day =100, End_day = 90, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 40.0 ),
//            Gards(ID_Product = 9, Start_Day =70, End_day = 60, Total_Sell = 10.0, Date_Sell =  Date(2023, 5, 13) , Total_mony = 25.0 )
//        )
//
//        /*
//         val taresha=listOf(
//            Taresha(1, Date(2023, 4, 20) ,1,30.0),
//            Taresha(2, Date(2023, 4, 20),1,4.5),
//            Taresha(3, Date(2023, 4, 20) ,3,21.0),
//            )
//        val productstorreffrenc=listOf(
//            ProductAndTarshaCross(1,1,2),
//            ProductAndTarshaCross(1,2,2),
//            ProductAndTarshaCross(1,3,1),
//            ProductAndTarshaCross(2,4,1),
//            ProductAndTarshaCross(2,5,3),
//            ProductAndTarshaCross(3,6,2),
//            )
//
//
//*/
//        lifecycleScope.launch {
//            products.forEach { dao.insertProduct(it) }
//            solidgers.forEach { dao.insertSoildger(it) }
//            gard.forEach { dao.insertGard(it) }
//           // taresha.forEach { dao.insertTaresha(it) }
//           // productstorreffrenc.forEach{dao.insertproductTarshaCross(it)}
//       //     insertproductTarshaCross.forEach { dao.insertproductTarsha(it) }
//
//        }
        val navController :NavController
        val bottomNavigationView:BottomNavigationView
        navController=Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)




    }
}