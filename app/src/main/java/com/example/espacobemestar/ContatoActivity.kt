package com.example.espacobemestar

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat





class ContatoActivity : AppCompatActivity() {
    private val REQUEST_CALL = 1
    private var textTell: TextView? = null
    private var callButton: Button? = null
    private val PHONE_CALL_REQUEST = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)
        this.textTell = findViewById(R.id.textTell)
        this.callButton = findViewById(R.id.callButton)
        this.callButton?.setOnClickListener {
            TesteCallPhone();
        }
    }


    fun TesteCallPhone()
    {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                PHONE_CALL_REQUEST
            )
        } else {
            val intent =
                Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+551125678850"))
            startActivity(intent)
        }

    }



}
