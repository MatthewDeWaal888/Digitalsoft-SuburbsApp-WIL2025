package com.dse.thesuburbsservices.screens

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.webkit.ValueCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dse.thesuburbsservices.APP_THEME_DARK
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.databinding.ActivityStartupPhoneLightBinding
import com.dse.thesuburbsservices.tools.ListingDirectoryHelper
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;


// This class represents the StartupActivity screen.
class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Configuration.getInstance().load(getApplicationContext(), getSharedPreferences("osmdroid", MODE_PRIVATE));

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
                // Declare and instantiate a ListingDirectoryHelper object.
                val helper = ListingDirectoryHelper(this)
                // Assign  the ValueCallback listener.
                helper.onDataReceived = object : ValueCallback<Array<ListingDirectory>> {
                    override fun onReceiveValue(value: Array<ListingDirectory>?) {
                        AppData.listings.addAll(value!!)
                    }
                }

                val dlg = showLoadingScreen()

                CoroutineScope(Dispatchers.IO).launch {
                    helper.getTask().await()
                }.invokeOnCompletion {
                    dlg.dismiss()
                    startApp()
                }

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

    private fun startApp()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoadingScreen(): Dialog
    {
        val dlgLoadingScreen = Dialog(this)
        dlgLoadingScreen.setContentView(R.layout.layout_loading_phone_light)
        dlgLoadingScreen.show()

        return dlgLoadingScreen
    }
}