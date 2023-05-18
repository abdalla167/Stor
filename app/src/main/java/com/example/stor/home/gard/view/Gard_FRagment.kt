package com.example.stor.home.gard.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.home.gard.adapter.ConferAdapter
import com.example.stor.home.gard.model.GardModel
import com.example.stor.home.proudects.show_all_product.adapter.GardAdapters
import com.example.stor.home.proudects.show_all_product.viewmodel.GardViewModel
import java.text.SimpleDateFormat
import java.util.*

class Gard_FRagment : Fragment() {
    private val gardViewModel: GardViewModel by viewModels()
    private lateinit var gardAdapters: GardAdapters
    var mList_Product= ArrayList<Product>()
    var mList= ArrayList<GardModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_gard__f_ragment, container, false)
        var recyclerView: RecyclerView
        var buttonAddGard:Button
        buttonAddGard=view.findViewById(R.id.button_add_gard_database)
        gardViewModel.allProduct.observe(viewLifecycleOwner, Observer {
            it.forEach {
                mList.add(GardModel(it.name,it.currentCount))
                mList_Product.add(it)
            }
            gardAdapters= mList.let { it1 -> GardAdapters(it1,"الكميه الحاليه") }!!
            recyclerView=view.findViewById(R.id.gard_recycler_id)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = gardAdapters
            }
        })

        buttonAddGard.setOnClickListener {


            ///show dailog to hint about data updated and conferm it


        var gardList = gardAdapters.retrieveData()
            if (gardList != null) {

                val  builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog).create()
                val  view = layoutInflater.inflate(R.layout.custom_dailog_conferm_gard,null)
                val  conferm = view.findViewById<Button>(R.id.button_custom_conferm)
                val  titel = view.findViewById<TextView>(R.id.name_of_process_custom)
                val  recyclerView_gard = view.findViewById<RecyclerView>(R.id.recyclerView_custom)
                val  close=view.findViewById<Button>(R.id.close_custom)
                val  calender=view.findViewById<CalendarView>(R.id.calenderinsertGard)
                val  textDate=view.findViewById<TextView>(R.id.showdate_insertGard)
                calender.isVisible = false
                var date =Date()

                textDate.setOnClickListener{

                    if (  calender.isVisible)
                    {
                        calender.isVisible = false
                    }
                    else{
                        calender.isVisible = true
                    }



                }
                calender.setOnDateChangeListener(
                    CalendarView.OnDateChangeListener { views, year, month, dayOfMonth ->
                        val Date1 = (year.toString() + "-" + (month + 1) + "-" + dayOfMonth.toString())
                        date= Date(year,(month),dayOfMonth)

                        // set this date in TextView for Display
                        Log.d("TAG", "onCreateView: "+Date1)
                        textDate.setText(Date1)

                    })
                lateinit var conferAdapter: ConferAdapter
                conferAdapter= ConferAdapter(mList)
                recyclerView_gard.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = conferAdapter
                }
                builder.setView(view)
                close.setOnClickListener {
                    builder.dismiss()

                }

                conferm.setOnClickListener {
                    val formatter = SimpleDateFormat("yyyy-MM-dd ")
                    if(textDate.text.equals(""))
                     {
                       Toast.makeText(requireContext(),"برجاء ادخال تاريخ الجرد",Toast.LENGTH_LONG).show()
                     }
                    else {

                        gardList.forEachIndexed { index, element ->
                            gardViewModel.insertNewGard(
                                Gards(
                                    ID_Product = mList_Product.get(index).ID_Product!!,
                                    Start_Day = mList_Product.get(index).currentCount,
                                    End_day = element!!.count,
                                    Total_Sell = (mList_Product.get(index).currentCount - element!!.count).toDouble(),
                                    Date_Sell = date,
                                    Total_mony = ((mList_Product.get(index).currentCount - element!!.count).toDouble())*mList_Product.get(index).PriceSell
                                )
                            )
                            mList_Product.get(index).currentCount = element!!.count
                            gardViewModel.updateProduct(mList_Product.get(index))
                        }

                        builder.dismiss()
                        Toast.makeText(requireContext(), " تم التعديل بنجاح ", Toast.LENGTH_LONG)
                            .show()
                    }
                    builder.dismiss()
                }
                builder.setCanceledOnTouchOutside(false)
                builder.show()

            }

        }

        return view
    }

}