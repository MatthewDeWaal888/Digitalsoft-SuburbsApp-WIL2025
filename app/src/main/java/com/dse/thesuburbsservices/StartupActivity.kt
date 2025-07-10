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

val APP_THEME_LIGHT = 1
val APP_THEME_DARK = 2
var appTheme = APP_THEME_LIGHT

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if(resources.configuration.isNightModeActive)
            appTheme = APP_THEME_DARK

        if(appTheme == APP_THEME_LIGHT)
        {
            val binding = ActivityStartupPhoneLightBinding.inflate(this.layoutInflater)
            setContentView(binding.root)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.btnStartNow.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            binding.btnExit.setOnClickListener {
                this.finish()
            }
        }
        else if(appTheme == APP_THEME_DARK)
        {

        }
    }
}