package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.csr_fragment
import com.dse.thesuburbsservices.data.AppData

class GarthMyMateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_garth_my_mate_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_garth_my_mate_phone_dark, container, false)

        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val strContent = "${AppData.garthMyMate?.heading1}\n\n${AppData.garthMyMate?.content1}\n\n${AppData.garthMyMate?.heading2}\n\n${AppData.garthMyMate?.content2}"
        tvContent.text = strContent


        val btnBack = view.findViewById<AppCompatButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            ScreenNavigate(csr_fragment)
        }

        return view
    }
}