package com.dse.thesuburbsservices

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dse.thesuburbsservices.databinding.ActivityStartupPhoneLightBinding
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// This class represents the StartupActivity screen.
class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if the system theme is in Dark Theme mode.
        if(resources.configuration.isNightModeActive)
            // Assign the appTheme to Dark mode.
            appTheme = APP_THEME_DARK

        // Check if the appTheme is Light Theme mode.
        if(appTheme == APP_THEME_LIGHT)
        {
            // Create a binding for this activity.
            val binding = ActivityStartupPhoneLightBinding.inflate(this.layoutInflater)
            // Set the content view to this activity.
            setContentView(binding.root)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Occurs when btnStartNow is clicked.
            binding.btnStartNow.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            // Occurs when btnExit is clicked.
            binding.btnExit.setOnClickListener {
                this.finish()
            }
        }
        // Check if the appTheme is in Dark Theme mode.
        else if(appTheme == APP_THEME_DARK)
        {

        }
    }
}