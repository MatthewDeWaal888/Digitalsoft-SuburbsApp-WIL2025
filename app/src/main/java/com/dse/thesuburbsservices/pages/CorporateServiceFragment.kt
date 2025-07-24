package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.home_fragment

class CorporateServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_corporate_service_phone_light, container, false)

        val tvHeading = view.findViewById<TextView>(R.id.tvHeading)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val btnBack = view.findViewById<AppCompatButton>(R.id.btnBack)

        tvHeading.text = AppData.corporateService?.heading
        tvContent.text = AppData.corporateService?.content

        btnBack.setOnClickListener {
            ScreenNavigate(home_fragment)
        }

        return view
    }
}