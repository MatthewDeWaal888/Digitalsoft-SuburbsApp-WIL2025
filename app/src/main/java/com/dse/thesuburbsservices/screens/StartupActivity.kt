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
import com.dse.thesuburbsservices.data.*
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.data.Article
import com.dse.thesuburbsservices.data.GarthMyMate
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.data.ZachGivesBack
import com.dse.thesuburbsservices.data._40Kids40Smiles
import com.dse.thesuburbsservices.databinding.ActivityStartupPhoneLightBinding
import com.dse.thesuburbsservices.showLoadingScreen
import com.dse.thesuburbsservices.tools.AboutUsHelper
import com.dse.thesuburbsservices.tools.GarthMyMateHelper
import com.dse.thesuburbsservices.tools.ListingDirectoryHelper
import com.dse.thesuburbsservices.tools.WhatsHappeningHelper
import com.dse.thesuburbsservices.tools.ZachGivesBackHelper
import com.dse.thesuburbsservices.tools.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import java.util.concurrent.atomic.AtomicInteger


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
                val jobsCompleted = AtomicInteger(0)
                val numberOfJobs = 9
                val deferred = CompletableDeferred<Int>()
                getContent(deferred, jobsCompleted, numberOfJobs)

                val dlg = showLoadingScreen(this)

                CoroutineScope(Dispatchers.Main).launch {
                    deferred.await()
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

    private fun getContent(deferred: CompletableDeferred<Int>, jobsCompleted: AtomicInteger, numberOfJobs: Int)
    {
        // Declare and instantiate a ListingDirectoryHelper object.
        val listingHelper = ListingDirectoryHelper()
        // Assign  the ValueCallback listener.
        listingHelper.onDataReceived = object : ValueCallback<Array<ListingDirectory>> {
            override fun onReceiveValue(value: Array<ListingDirectory>?) {
                AppData.listings.addAll(value!!)
                jobsCompleted.incrementAndGet()

                if(jobsCompleted.get() == numberOfJobs)
                {
                    if(!deferred.isCompleted)
                    {
                        deferred.complete(jobsCompleted.get())
                    }
                }
            }
        }

        // Declare and instantiate an ArticleHelper object.
        val articleHelper = WhatsHappeningHelper()
        articleHelper.onDataReceived = object : ValueCallback<Array<Article>> {
            override fun onReceiveValue(value: Array<Article>?) {
                AppData.articles.addAll(value!!)
                jobsCompleted.incrementAndGet()

                if(jobsCompleted.get() == numberOfJobs)
                {
                    if(!deferred.isCompleted)
                    {
                        deferred.complete(jobsCompleted.get())
                    }
                }
            }
        }

        // Declare and instantiate a _40Kids40SmilesHelper object
        val _40kids40smilesHelper = _40Kids40SmilesHelper()
        _40kids40smilesHelper.onDataReceived = object : ValueCallback<_40Kids40Smiles> {
            override fun onReceiveValue(value: _40Kids40Smiles?) {
                AppData._40kids40smiles = value
                jobsCompleted.incrementAndGet()

                if(jobsCompleted.get() == numberOfJobs)
                {
                    if(!deferred.isCompleted)
                    {
                        deferred.complete(jobsCompleted.get())
                    }
                }
            }
        }

        // Declare and instantiate a ZachGivesBackHelper object
        val zachGivesBackHelper = ZachGivesBackHelper()
        zachGivesBackHelper.onDataReceived = object : ValueCallback<ZachGivesBack> {
            override fun onReceiveValue(value: ZachGivesBack?) {
                AppData.zachGivesBack = value
                jobsCompleted.incrementAndGet()

                if(jobsCompleted.get() == numberOfJobs)
                {
                    if(!deferred.isCompleted)
                    {
                        deferred.complete(jobsCompleted.get())
                    }
                }
            }
        }

        // Declare and instantiate a GarthMyMateHelper object
        val garthMyMateHelper = GarthMyMateHelper()
        garthMyMateHelper.onDataReceived = object : ValueCallback<GarthMyMate> {
            override fun onReceiveValue(value: GarthMyMate?) {
                AppData.garthMyMate = value
                jobsCompleted.incrementAndGet()

                if(jobsCompleted.get() == numberOfJobs)
                {
                    if(!deferred.isCompleted)
                    {
                        deferred.complete(jobsCompleted.get())
                    }
                }
            }
        }

        // Declare and instantiate an AboutUsHelper object
        val aboutUsHelper = AboutUsHelper()
        aboutUsHelper.onDataReceived = object : ValueCallback<AboutUs> {
            override fun onReceiveValue(value: AboutUs?) {
                AppData.aboutUs = value
                val i = jobsCompleted.incrementAndGet()

                if(i == numberOfJobs && !deferred.isCompleted)
                {
                    deferred.complete(i)
                }
            }
        }

        // Declare and instantiate an SportsServiceHelper object.
        val sportsServiceHelper = SportsServiceHelper()
        sportsServiceHelper.onDataReceived = object : ValueCallback<SportsService> {
            override fun onReceiveValue(value: SportsService?) {
                AppData.sportsService = value
                val i = jobsCompleted.incrementAndGet()

                if(i == numberOfJobs && !deferred.isCompleted)
                {
                    deferred.complete(i)
                }
            }
        }

        // Declare and instantiate a HospitalityServiceHelper object.
        val hospitalityServiceHelper = HospitalityServiceHelper()
        hospitalityServiceHelper.onDataReceived = object : ValueCallback<HospitalityService> {
            override fun onReceiveValue(value: HospitalityService?) {
                AppData.hospitalityService = value
                val i = jobsCompleted.incrementAndGet()

                if(i == numberOfJobs && !deferred.isCompleted)
                {
                    deferred.complete(i)
                }
            }
        }

        // Declare and instantiate a CorporateServiceHelper object.
        val corporateServiceHelper = CorporateServiceHelper()
        corporateServiceHelper.onDataReceived = object : ValueCallback<CorporateService> {
            override fun onReceiveValue(value: CorporateService?) {
                AppData.corporateService = value
                val i = jobsCompleted.incrementAndGet()

                if(i == numberOfJobs && !deferred.isCompleted)
                {
                    deferred.complete(i)
                }
            }
        }

        // Declare and instantiate an AdvertiseWithUsHelper object.
        val advertiseWithUsHelper = AdvertiseWithUsHelper()
        advertiseWithUsHelper.onDataReceived = object : ValueCallback<AdvertiseWithUs> {
            override fun onReceiveValue(value: AdvertiseWithUs?) {
                AppData.advertiseWithUs = value
                val i = jobsCompleted.incrementAndGet()

                if(i == numberOfJobs && !deferred.isCompleted)
                {
                    deferred.complete(i)
                }
            }
        }
    }

    private fun startApp()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}