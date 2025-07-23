package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData

class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_us_phone_light, container, false)

        val tvHeading1 = view.findViewById<TextView>(R.id.tvHeading1)
        val tvContent1 = view.findViewById<TextView>(R.id.tvContent1)
        val tvHeading2 = view.findViewById<TextView>(R.id.tvHeading2)
        val tvContent2 = view.findViewById<TextView>(R.id.tvContent2)

        tvHeading1.text = AppData.aboutUs?.ourStoryHeading
        tvContent1.text = AppData.aboutUs?.ourStory
        tvHeading2.text = AppData.aboutUs?.ourVisionHeading
        tvContent2.text = AppData.aboutUs?.ourVision

        return view
    }
}