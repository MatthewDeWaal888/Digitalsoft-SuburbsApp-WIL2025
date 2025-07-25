package com.dse.thesuburbsservices.screens

import android.os.Bundle
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.databinding.ActivityMainPhoneLightBinding
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.csr_fragment
import com.dse.thesuburbsservices.home_fragment
import com.dse.thesuburbsservices.listingDirectory_fragment
import com.dse.thesuburbsservices.tss_clDisplay
import com.dse.thesuburbsservices.tss_fragmentManager
import com.dse.thesuburbsservices.*

// This class represents the MainActivity class.
class MainActivity : AppCompatActivity() {

    lateinit var popupMenu: PopupMenu
    lateinit var tvTitle: TextView

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

            tvTitle = binding.tvTitle

            // Initialization
            popupMenu = PopupMenu(this, binding.btnMenu)

            // Add the menu items to the PopupMenu.
            popupMenu.menu.add("Home")
            popupMenu.menu.add("Listing Directory")
            popupMenu.menu.add("What’s Happening")
            popupMenu.menu.add("Corporate Social Responsibility")
            popupMenu.menu.add("40 Kids 40 Smiles")
            popupMenu.menu.add("Zach Gives Back")
            popupMenu.menu.add("Garth My Mate")
            popupMenu.menu.add("About Us")
            popupMenu.menu.add("Services - Sports")
            popupMenu.menu.add("Services - Hospitality")
            popupMenu.menu.add("Services - Corporate")
            popupMenu.menu.add("Contact Us")
            popupMenu.menu.add("Staff Login")
            popupMenu.menu.add("Advertise With Us")

            // Assign an onclick listener to the PopupMenu.
            popupMenu.setOnMenuItemClickListener {
                // Case statement.
                when(it.title)
                {
                    "Home" -> btnMenu_item1_onclick()
                    "Listing Directory" -> btnMenu_item2_onclick()
                    "What’s Happening" -> btnMenu_item3_onclick()
                    "Corporate Social Responsibility" -> btnMenu_item4_onclick()
                    "40 Kids 40 Smiles" -> btnMenu_item5_onclick()
                    "Zach Gives Back" -> btnMenu_item6_onclick()
                    "Garth My Mate" -> btnMenu_item7_onclick()
                    "About Us" -> btnMenu_item8_onclick()
                    "Services - Sports" -> btnMenu_item9_onclick()
                    "Services - Hospitality" -> btnMenu_item10_onclick()
                    "Services - Corporate" -> btnMenu_item11_onclick()
                    "Contact Us" -> btnMenu_item12_onclick()
                    "Staff Login" -> btnMenu_item13_onclick()
                    "Advertise With Us" -> btnMenu_item14_onclick()
                }
                true
            }

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
                popupMenu.show()
            }
        }
    }

    // Occurs when 'Home' is clicked.
    private fun btnMenu_item1_onclick()
    {
        tvTitle.text = "The Suburbs Services - Home"
        ScreenNavigate(home_fragment)
    }

    // Occurs when 'Listing Directory' is clicked.
    private fun btnMenu_item2_onclick()
    {
        tvTitle.text = "The Suburbs Services - Listing Directory"
        ScreenNavigate(listingDirectory_fragment)
    }

    // Occurs when 'What's Happening' is clicked.
    private fun btnMenu_item3_onclick()
    {
        tvTitle.text = "The Suburbs Services - What's Happening"
        ScreenNavigate(whatshappening_fragment)
    }

    // Occurs when 'Corporate Social Responsibility' is clicked.
    private fun btnMenu_item4_onclick()
    {
        tvTitle.text = "The Suburbs Services - Corporate Social Responsibility"
        ScreenNavigate(csr_fragment)
    }

    // Occurs when '40 Kids 40 Smiles' is clicked.
    private fun btnMenu_item5_onclick()
    {
        tvTitle.text = "The Suburbs Services - 40 Kids 40 Smiles"
        ScreenNavigate(_40kids40smiles_fragment)
    }

    // Occurs when 'Zach Gives Back' is clicked.
    private fun btnMenu_item6_onclick()
    {
        tvTitle.text = "The Suburbs Services - Zach Gives Back"
        ScreenNavigate(zachGivesBack_fragment)
    }

    // Occurs when 'Garth My Mate' is clicked.
    private fun btnMenu_item7_onclick()
    {
        tvTitle.text = "The Suburbs Services - Garth My Mate"
        ScreenNavigate(garthMyMate_fragment)
    }

    // Occurs when 'About Us' is clicked.
    private fun btnMenu_item8_onclick()
    {
        tvTitle.text = "The Suburbs Services - Anout Us"
        ScreenNavigate(aboutUs_fragment)
    }

    // Occurs when 'Services - Sports' is clicked.
    private fun btnMenu_item9_onclick()
    {
        tvTitle.text = "The Suburbs Services - Services - Sports"
        ScreenNavigate(sportsService_fragment)
    }

    // Occurs when 'Services - Hospitality' is clicked.
    private fun btnMenu_item10_onclick()
    {
        tvTitle.text = "The Suburbs Services - Services - Hospitality"
        ScreenNavigate(hospitalityService_fragment)
    }

    // Occurs when 'Services - Corporate' is clicked.
    private fun btnMenu_item11_onclick()
    {
        tvTitle.text = "The Suburbs Services - Services - Corporate"
        ScreenNavigate(corporateService_fragment)
    }

    // Occurs when 'Contact Us' is clicked.
    private fun btnMenu_item12_onclick()
    {
        tvTitle.text = "The Suburbs Services - Contact Us"
        ScreenNavigate(contact_fragment)
    }

    // Occurs when 'Staff Login' is clicked.
    private fun btnMenu_item13_onclick()
    {
        tvTitle.text = "The Suburbs Services - Staff Login"
        ScreenNavigate(staffLogin_fragment)
    }

    // Occurs when 'Advertise With Us' is clicked.
    private fun btnMenu_item14_onclick()
    {
        tvTitle.text = "The Suburbs Services - Advertise With Us"
        ScreenNavigate(advertiseWithUs_fragment)
    }
}