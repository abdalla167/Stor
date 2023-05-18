package com.example.stor.db.online.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stor.R
import com.example.stor.db.offline.StorDataBase
import com.example.stor.db.online.viewModle.OnlineViewModel


class OnlineFragementData : Fragment() {
    private val viewModel: OnlineViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_online_fragement_data, container, false)


        viewModel.uploadData()
        return view
    }


    }



