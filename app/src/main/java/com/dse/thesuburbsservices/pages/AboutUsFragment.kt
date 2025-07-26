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
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.home_fragment

class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_about_us_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_about_us_phone_dark, container, false)

        val tvHeading1 = view.findViewById<TextView>(R.id.tvHeading1)
        val tvContent1 = view.findViewById<TextView>(R.id.tvContent1)
        val tvHeading2 = view.findViewById<TextView>(R.id.tvHeading2)
        val tvContent2 = view.findViewById<TextView>(R.id.tvContent2)
        val btnBack = view.findViewById<AppCompatButton>(R.id.btnBack)

        tvHeading1.text = AppData.aboutUs?.ourStoryHeading
        tvContent1.text = AppData.aboutUs?.ourStory
        tvHeading2.text = AppData.aboutUs?.ourVisionHeading
        tvContent2.text = AppData.aboutUs?.ourVision

        btnBack.setOnClickListener {
            ScreenNavigate(home_fragment)
        }

        return view
    }
}