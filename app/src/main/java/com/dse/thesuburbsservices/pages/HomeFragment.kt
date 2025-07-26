package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.aboutUs_fragment
import com.dse.thesuburbsservices.advertiseWithUs_fragment
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.csr_fragment
import com.dse.thesuburbsservices.listingDirectory_fragment
import com.dse.thesuburbsservices.whatshappening_fragment

// This class represents the home page (fragment).
class HomeFragment : Fragment() {

    // Occurs when the fragment is created.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_home_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_home_phone_dark, container, false)

        // Obtain the buttons from the screen
        val btnExploreNow1 = view.findViewById<AppCompatButton>(R.id.btnExploreNow1)
        val btnExploreNow2 = view.findViewById<AppCompatButton>(R.id.btnExploreNow2)
        val btnExploreNow3 = view.findViewById<AppCompatButton>(R.id.btnExploreNow3)
        val btnExploreNow4 = view.findViewById<AppCompatButton>(R.id.btnExploreNow4)
        val btnExploreNow5 = view.findViewById<AppCompatButton>(R.id.btnExploreNow5)

        // Assign onclick listeners.
        btnExploreNow1.setOnClickListener {
            // Navigate to the Listing Directory fragment.
            ScreenNavigate(listingDirectory_fragment)
        }

        btnExploreNow2.setOnClickListener {
            // Navigate to the What's Happening fragment.
            ScreenNavigate(whatshappening_fragment)
        }

        btnExploreNow3.setOnClickListener {
            // Navigate to the Corporate Social Responsibility fragment.
            ScreenNavigate(csr_fragment)
        }

        btnExploreNow4.setOnClickListener {
            // Navigate to the About Us fragment.
            ScreenNavigate(aboutUs_fragment)
        }

        btnExploreNow5.setOnClickListener {
            // Navigate to the Advertise With Us fragment.
            ScreenNavigate(advertiseWithUs_fragment)
        }

        return view
    }
}