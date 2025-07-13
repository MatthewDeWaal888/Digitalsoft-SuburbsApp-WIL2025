package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_phone_light, container, false)

        val btnExploreNow1 = view.findViewById<AppCompatButton>(R.id.btnExploreNow1)

        btnExploreNow1.setOnClickListener {
            ScreenNavigate(listingDirectory_fragment)
        }

        return view
    }
}