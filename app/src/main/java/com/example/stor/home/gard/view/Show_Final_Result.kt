package com.example.stor.home.gard.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stor.R
import com.example.stor.home.gard.adapter.ConferAdapter
import com.example.stor.home.gard.model.GardModel
import com.example.stor.home.proudects.show_all_product.adapter.GardAdapters
import com.example.stor.home.proudects.show_all_product.viewmodel.GardViewModel
import com.example.stor.home.proudects.show_all_product.viewmodel.TareshaViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class Show_Final_Result : Fragment() {

    lateinit var radioButton: RadioButton
    private val gardViewModel: GardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_show__final__result, container, false)
        var buttonShowResult=view.findViewById<Button>(R.id.button_show_result_final)
        var textDate=view.findViewById<TextView>(R.id.textDate_show_final_result)
        var sell_day_text=view.findViewById<TextView>(R.id.sell_id_show_finale_result)
        var cash_day_text=view.findViewById<TextView>(R.id.cash_id_show_finale_result)
        var taresh_text=view.findViewById<TextView>(R.id.taresha_id_show_finale_result)
        var recler_day_gard=view.findViewById<RecyclerView>(R.id.recyler_result_final_result)
        var radioGroup=view.findViewById<RadioGroup>(R.id.radioGroupshowResult)
        var calander=view.findViewById<CalendarView>(R.id.calendarView)
        //first time get gard for same time or last night
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd ")
        val current = formatter.format(time)
        var date =Date()
        textDate.text=current

        textDate.setOnClickListener{

           if (  calander.isVisible)
            {
                calander.isVisible = false
            }
            else{
               calander.isVisible = true
            }



        }
        calander.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { views, year, month, dayOfMonth ->
                val Date1 = (year.toString() + "-" + (month + 1) + "-" + dayOfMonth.toString())
                date= Date(year,(month),dayOfMonth)
                // set this date in TextView for Display
                Log.d("TAG", "onCreateView: "+Date1)
                textDate.setText(Date1)


            })

        buttonShowResult.setOnClickListener {
            //    Log.d("TAG", "onCreateView: "+formatter.parse(textDate.text.toString())+"    ggg " + textDate.text.toString() )
            // Getting the checked radio button id
            // from the radio group
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId

            // Assigning id of the checked radio button
            radioButton = view.findViewById(selectedOption)
            if (radioButton.text.toString().equals("اظهار النتائج اليوميه"))
            {
                //show result for this day
                if (textDate.text == "") {
                    Toast.makeText(requireContext(), "برجاء اختيار اليوم", Toast.LENGTH_LONG).show()
                }
                else {
                    if(!current.equals( textDate.text.toString()))
                    {
                        gardViewModel.GetGardREsult(date)
                        Log.d("TAG", "onCreateView: "+date)
                    }
                    else
                    {
                        gardViewModel.GetGardREsult(formatter.parse(textDate.text.toString())!!)
                        Log.d("TAG 2", "onCreateView: "+formatter.parse(textDate.text.toString()))

                    }
                    gardViewModel.totalSell.observe(viewLifecycleOwner, androidx.lifecycle.Observer
                    {
                        if (it == null) {
                            sell_day_text.text = "0"
                        } else {
                            sell_day_text.text = it.toString()

                        }
                    })
                    gardViewModel.totalTaresha.observe(viewLifecycleOwner, androidx.lifecycle.Observer
                     {
                         taresh_text.text=it.toString()
                     })
                    gardViewModel.totalcash.observe(viewLifecycleOwner, androidx.lifecycle.Observer
                    {
                        if (it == null) {
                            cash_day_text.text = "0"
                        } else {
                            cash_day_text.text=  Math.abs(it).toString()

                        }
                    })

                    gardViewModel.GardsProductDay.observe(viewLifecycleOwner, androidx.lifecycle.Observer {



                        lifecycleScope.launch {
                            var mList= ArrayList<GardModel>()
                            it.forEach {
                            //get name of product by id
                                mList.add(GardModel(gardViewModel.getNameusingIdProduct(it.ID_Product),it.End_day))
                            }
                            lateinit var gardAdapters: GardAdapters
                            gardAdapters= GardAdapters(mList,"الكميه الحاليه")
                            recler_day_gard.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(requireContext())
                                adapter = gardAdapters
                            }
                        }
                    })
                }

            }
        }
        return view
    }


}