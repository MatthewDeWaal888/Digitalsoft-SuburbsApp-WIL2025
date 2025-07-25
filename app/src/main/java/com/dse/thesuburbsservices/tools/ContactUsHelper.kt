package com.dse.thesuburbsservices.tools

import android.content.Context
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.ContactUs
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.security.auth.callback.Callback

class ContactUsHelper(context: Context) {

    private var contactUsObj: ContactUs? = null
    private val webView = WebView(context)
    private var pageReady: Boolean = false
    var _context: Context
    var onReady: ValueCallback<Boolean>? = null
    var onSubmissionCompleted: ValueCallback<String>? = null

    init {
        _context = context

        val url = combinePath(TSS_ADDRESS, "contact")

        // Assign the settings for the webView object.
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = false
        webView.settings.blockNetworkImage = true

        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pageReady = true
                onReady?.onReceiveValue(pageReady)
            }

        }

        webView.loadUrl(url)
    }

    fun getContactUsInfo(): ContactUs?
    {
        return contactUsObj
    }

    fun setContactUsInfo(contactUs: ContactUs?)
    {
        this.contactUsObj = contactUs
    }

    fun sendMessage()
    {
        if(pageReady)
        {
            var js = _context.resources.getString(R.string.js_submit_message)
            js = js.replace("#name", contactUsObj?.name!!)
            js = js.replace("#surname", contactUsObj?.surname!!)
            js = js.replace("#email", contactUsObj?.email!!)
            js = js.replace("#phone", contactUsObj?.phone!!)
            js = js.replace("#message", contactUsObj?.message!!)

            this.webView.evaluateJavascript(js) {

                Thread.sleep(3000)
                val js_result = _context.resources.getString(R.string.js_get_message_submission_status)

                this.webView.evaluateJavascript(js_result) { result ->
                    onSubmissionCompleted?.onReceiveValue(Json.decodeFromString(result))
                }

            }
        }
    }
}