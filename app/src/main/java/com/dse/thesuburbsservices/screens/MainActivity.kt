package com.dse.thesuburbsservices.screens

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.databinding.ActivityMainPhoneLightBinding
import com.dse.thesuburbsservices.pages.ScreenNavigate
import com.dse.thesuburbsservices.pages.home_fragment
import com.dse.thesuburbsservices.pages.*

// This class represents the MainActivity class.
class MainActivity : AppCompatActivity() {

    // Occurs when the activity (screen) is created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if the app is in Light Theme mode.
        if(appTheme == APP_THEME_LIGHT) {

            // Create a binding object for this activity.
            val binding = ActivityMainPhoneLightBinding.inflate(this.layoutInflater)
            // Set the content view to the binding.
            setContentView(binding.root)

            // Initialization
            tss_fragmentManager = this.supportFragmentManager
            tss_clDisplay = binding.clDisplay
            ScreenNavigate(home_fragment)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Occurs when the user clicked the drop-down menu.
            binding.btnMenu.setOnClickListener {
                btnMenu_OnClick(binding.btnMenu, binding.clDisplay)
            }
        }
    }

    // Occurs when the drop-down menu is clicked.
    private fun btnMenu_OnClick(view1: View, view2: View)
    {
        // Create a PopupMenu object.
        val popupMenu = PopupMenu(this, view1)

        // Add the menu items to the PopupMenu.
        popupMenu.menu.add("Home")
        popupMenu.menu.add("Listing Directory")
        popupMenu.menu.add("What’s Happening")
        popupMenu.menu.add("Corporate Social Responsibility")
        popupMenu.menu.add("40 Kids 40 Smiles")
        popupMenu.menu.add("Zach Gives Back")
        popupMenu.menu.add("Garth My Mate")
        popupMenu.menu.add("About Us")
        popupMenu.menu.add("About Us - Sports")
        popupMenu.menu.add("About Us - Hospitality")
        popupMenu.menu.add("About Us - Corporate")
        popupMenu.menu.add("Staff Login")
        popupMenu.menu.add("Advertise With Us")

        // Assign an onclick listener to the PopupMenu.
        popupMenu.setOnMenuItemClickListener {
            // Case statement.
            when(it.title)
            {
                "Home" -> btnMenu_item1_onclick(view2)
                "Listing Directory" -> btnMenu_item2_onclick(view2)
                "What's Happening" -> btnMenu_item3_onclick(view2)
                "Corporate Social Responsibility" -> btnMenu_item4_onclick(view2)
                "40 Kids 40 Smiles" -> btnMenu_item5_onclick(view2)
                "Zach Gives Back" -> btnMenu_item6_onclick(view2)
                "Garth My Mate" -> btnMenu_item7_onclick(view2)
                "About Us" -> btnMenu_item8_onclick(view2)
                "About Us - Sports" -> btnMenu_item9_onclick(view2)
                "About Us - Hospitality" -> btnMenu_item10_onclick(view2)
                "About Us - Corporate" -> btnMenu_item11_onclick(view2)
                "Staff Login" -> btnMenu_item12_onclick(view2)
                "Advertise With Us" -> btnMenu_item13_onclick(view2)
            }
            true
        }
        // Show the Popup Menu to the user.
        popupMenu.show()
    }

    // Occurs when 'Home' is clicked.
    private fun btnMenu_item1_onclick(view: View)
    {
        ScreenNavigate(home_fragment)
    }

    // Occurs when 'Listing Directory' is clicked.
    private fun btnMenu_item2_onclick(view: View)
    {
        ScreenNavigate(listingDirectory_fragment)
    }

    // Occurs when 'What's Happening' is clicked.
    private fun btnMenu_item3_onclick(view: View)
    {

    }

    // Occurs when 'Corporate Social Responsibility' is clicked.
    private fun btnMenu_item4_onclick(view: View)
    {

    }

    // Occurs when '40 Kids 40 Smiles' is clicked.
    private fun btnMenu_item5_onclick(view: View)
    {

    }

    // Occurs when 'Zach Gives Back' is clicked.
    private fun btnMenu_item6_onclick(view: View)
    {

    }

    // Occurs when 'Garth My Mate' is clicked.
    private fun btnMenu_item7_onclick(view: View)
    {

    }

    // Occurs when 'About Us' is clicked.
    private fun btnMenu_item8_onclick(view: View)
    {

    }

    // Occurs when 'About Us - Sports' is clicked.
    private fun btnMenu_item9_onclick(view: View)
    {

    }

    // Occurs when 'About Us - Hospitality' is clicked.
    private fun btnMenu_item10_onclick(view: View)
    {

    }

    // Occurs when 'About Us - Corporate' is clicked.
    private fun btnMenu_item11_onclick(view: View)
    {

    }

    // Occurs when 'Staff Login' is clicked.
    private fun btnMenu_item12_onclick(view: View)
    {

    }

    // Occurs when 'Advertise With Us' is clicked.
    private fun btnMenu_item13_onclick(view: View)
    {

    }
}