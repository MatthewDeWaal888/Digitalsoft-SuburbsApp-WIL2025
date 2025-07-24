package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData

class HospitalityServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hospitality_service_phone_light, container, false)

        val tvHeading = view.findViewById<TextView>(R.id.tvHeading)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)

        tvHeading.text = AppData.hospitalityService?.heading
        tvContent.text = AppData.hospitalityService?.content

        return view
    }
}