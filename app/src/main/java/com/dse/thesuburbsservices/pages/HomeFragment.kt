package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.R

// This class represents the home page (fragment).
class HomeFragment : Fragment() {

    // Occurs when the fragment is created.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_phone_light, container, false)

        // Obtain the buttons from the screen
        val btnExploreNow1 = view.findViewById<AppCompatButton>(R.id.btnExploreNow1)

        // Assign onclick listeners.
        btnExploreNow1.setOnClickListener {
            // Navigate to the Listing Directory fragment.
            ScreenNavigate(listingDirectory_fragment)
        }

        return view
    }
}