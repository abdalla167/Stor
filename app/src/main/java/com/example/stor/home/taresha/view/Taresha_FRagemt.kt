package com.example.stor.home.taresha.view


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Taresha
import com.example.stor.home.gard.adapter.ConferAdapter
import com.example.stor.home.gard.model.GardModel
import com.example.stor.home.proudects.show_all_product.adapter.TareshaAdapter
import com.example.stor.home.proudects.show_all_product.viewmodel.TareshaViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class Taresha_FRagemt : Fragment()  {
    private val tareshaViewModel: TareshaViewModel by viewModels()
    private lateinit var qrScanIntegrator: IntentIntegrator
    private lateinit var tareshaAdapter: TareshaAdapter
    var mList_Product= ArrayList<Product>()
    var mList= ArrayList<GardModel>()
    var pListID=ArrayList<Int>()
    var pListCoun=ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScanner()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view= inflater.inflate(R.layout.fragment_taresha__f_ragemt, container, false)
        var buttonScanne=view.findViewById<Button>(R.id.btnScan)
        var recyclerView: RecyclerView
        tareshaViewModel.getAllProduct()
        tareshaViewModel.allProduct.observe(viewLifecycleOwner, Observer {
            it.forEach {
                mList.add(GardModel(it.name,0))
                mList_Product.add(it)
            }
            tareshaAdapter= mList.let { it1 -> TareshaAdapter(it1,"الكميه المطلوبه") }!!
            recyclerView=view.findViewById(R.id.recyclerView_taresha)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tareshaAdapter
            }
        }
        )



        buttonScanne.setOnClickListener {
           performAction()
        }


        return view
    }

    private fun setupScanner() {
        qrScanIntegrator = IntentIntegrator.forSupportFragment(this)
        qrScanIntegrator.setOrientationLocked(true)
    }

    private fun performAction() {
        // Code to perform action when button is clicked.
        qrScanIntegrator.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(activity, "result_not_found", Toast.LENGTH_LONG).show()
            } else {
                // If QRCode contains data.
                try {
                    // Converting the data to json format
                    val obj = JSONObject(result.contents)

                    // Show values in UI.
//                    Log.d("TAG", "onActivityResult: "+ obj.getInt("id"))
//                    Log.d("TAG", "onActivityResult: "+ obj.getString("name"))
                    //Taresha(1, Date(2023, 4, 20) ,1,30.0)
                    //insert taresha



                    var gardList=  tareshaAdapter.retrieveData()
                    if (gardList != null) {
                        var money=0.0
                        gardList.forEachIndexed { index, element ->

                            if (element != null) {

                                if(element.count>0)
                                {
                                    //get total mony of product that he buy
                                    //get id soldger
                                    money+=element.count*mList_Product.get(index).PriceSell
                                    mList_Product.get(index).ID_Product?.let { pListID.add(it) }
                                    pListCoun.add(element.count)
                                }


                            }

                        }
                        mList_Product.clear()
                        //add in taresha tabel
                        val  builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog).create()
                        val  view = layoutInflater.inflate(R.layout.custom_dailog_conferm_gard,null)
                        val  conferm = view.findViewById<Button>(R.id.button_custom_conferm)
                        val  titel = view.findViewById<TextView>(R.id.name_of_process_custom)
                        val  recyclerView_gard = view.findViewById<RecyclerView>(R.id.recyclerView_custom)
                        val  close=view.findViewById<Button>(R.id.close_custom)
                        val  calender=view.findViewById<CalendarView>(R.id.calenderinsertGard)
                        val  textDate=view.findViewById<TextView>(R.id.showdate_insertGard)
                        calender.isVisible = false
                        var date = Date()
                        textDate.setOnClickListener{

                            if (  calender.isVisible)
                            {
                                calender.isVisible = false
                            }
                            else{
                                calender.isVisible = true
                            }



                        }
                        calender.setOnDateChangeListener(CalendarView.OnDateChangeListener { views, year, month, dayOfMonth ->
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


                                lifecycleScope.launch {
                                   var id= tareshaViewModel.insertTaresha(Taresha(date=date, ID_Solidger= obj.getInt("id"),money=money))
                                    //update mony for soldger
                                    tareshaViewModel.updateMonySoldger(obj.getInt("id"),money)
                                    pListID.forEachIndexed { index, i ->

                                        tareshaViewModel.insertCross(ProductAndTarshaCross(ID_Taresha = id.toInt(),ID_Product=i,countOneProduct=pListCoun.get(index)))
                                        Log.d("TAG", "onActivityResult: add "+id+"   "+i+"   " +pListCoun.get(index))

                                    }

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

                } catch (e: JSONException) {
                    e.printStackTrace()

                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(activity, result.contents, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}