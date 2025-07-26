package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.ContactUs
import com.dse.thesuburbsservices.net.TSS_SendMessage

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact_phone_light, container, false)

        val etName = view.findViewById<EditText>(R.id.etName)
        val etSurname = view.findViewById<EditText>(R.id.etSurname)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPhone = view.findViewById<EditText>(R.id.etPhone)
        val etMessage = view.findViewById<EditText>(R.id.etMessage)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {
            val strName = etName.text.toString()
            val strSurname = etSurname.text.toString()
            val strEmail = etEmail.text.toString()
            val strPhone = etPhone.text.toString()
            val strMessage = etMessage.text.toString()

            val contactUs = ContactUs()
            contactUs.name = strName
            contactUs.surname = strSurname
            contactUs.email = strEmail
            contactUs.phone = strPhone
            contactUs.message = strMessage

            TSS_SendMessage(this.requireContext(), contactUs) { result ->
                Toast.makeText(this.requireContext(), result, Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}