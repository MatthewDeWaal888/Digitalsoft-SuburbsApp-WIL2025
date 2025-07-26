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
import com.dse.thesuburbsservices._40kids40smiles_fragment
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.garthMyMate_fragment
import com.dse.thesuburbsservices.zachGivesBack_fragment

class CSRFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_csr_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_csr_phone_dark, container, false)

        val tvHeading = view.findViewById<TextView>(R.id.tvHeading)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)

        val btnCSR1 = view.findViewById<AppCompatButton>(R.id.btnCSR1)
        val btnCSR2 = view.findViewById<AppCompatButton>(R.id.btnCSR2)
        val btnCSR3 = view.findViewById<AppCompatButton>(R.id.btnCSR3)

        tvHeading.text = AppData.csr?.heading
        tvContent.text = AppData.csr?.content

        btnCSR1.setOnClickListener {
            ScreenNavigate(_40kids40smiles_fragment)
        }

        btnCSR2.setOnClickListener {
            ScreenNavigate(zachGivesBack_fragment)
        }

        btnCSR3.setOnClickListener {
            ScreenNavigate(garthMyMate_fragment)
        }

        return view
    }
}