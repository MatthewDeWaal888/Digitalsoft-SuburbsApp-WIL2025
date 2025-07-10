package com.dse.thesuburbsservices

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dse.thesuburbsservices.databinding.ActivityMainPhoneLightBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if(appTheme == APP_THEME_LIGHT) {

            val binding = ActivityMainPhoneLightBinding.inflate(this.layoutInflater)
            setContentView(binding.root)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.btnMenu.setOnClickListener {
                btnMenu_OnClick(binding.btnMenu)
            }
        }
    }

    private fun btnMenu_OnClick(view: View)
    {
        val popupMenu = PopupMenu(this, view)
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

        popupMenu.setOnMenuItemClickListener {
            when(it.title)
            {
                "Home" -> btnMenu_item1_onclick()
                "Listing Directory" -> btnMenu_item2_onclick()
                "What's Happening" -> btnMenu_item3_onclick()
                "Corporate Social Responsibility" -> btnMenu_item4_onclick()
                "40 Kids 40 Smiles" -> btnMenu_item5_onclick()
                "Zach Gives Back" -> btnMenu_item6_onclick()
                "Garth My Mate" -> btnMenu_item7_onclick()
                "About Us" -> btnMenu_item8_onclick()
                "About Us - Sports" -> btnMenu_item9_onclick()
                "About Us - Hospitality" -> btnMenu_item10_onclick()
                "About Us - Corporate" -> btnMenu_item11_onclick()
                "Staff Login" -> btnMenu_item12_onclick()
                "Advertise With Us" -> btnMenu_item13_onclick()
            }
            true
        }
    }

    // Occurs when 'Home' is clicked.
    private fun btnMenu_item1_onclick()
    {

    }

    // Occurs when 'Listing Directory' is clicked.
    private fun btnMenu_item2_onclick()
    {

    }

    // Occurs when 'What's Happening' is clicked.
    private fun btnMenu_item3_onclick()
    {

    }

    // Occurs when 'Corporate Social Responsibility' is clicked.
    private fun btnMenu_item4_onclick()
    {

    }

    // Occurs when '40 Kids 40 Smiles' is clicked.
    private fun btnMenu_item5_onclick()
    {

    }

    // Occurs when 'Zach Gives Back' is clicked.
    private fun btnMenu_item6_onclick()
    {

    }

    // Occurs when 'Garth My Mate' is clicked.
    private fun btnMenu_item7_onclick()
    {

    }

    // Occurs when 'About Us' is clicked.
    private fun btnMenu_item8_onclick()
    {

    }

    // Occurs when 'About Us - Sports' is clicked.
    private fun btnMenu_item9_onclick()
    {

    }

    // Occurs when 'About Us - Hospitality' is clicked.
    private fun btnMenu_item10_onclick()
    {

    }

    // Occurs when 'About Us - Corporate' is clicked.
    private fun btnMenu_item11_onclick()
    {

    }

    // Occurs when 'Staff Login' is clicked.
    private fun btnMenu_item12_onclick()
    {

    }

    // Occurs when 'Advertise With Us' is clicked.
    private fun btnMenu_item13_onclick()
    {

    }
}