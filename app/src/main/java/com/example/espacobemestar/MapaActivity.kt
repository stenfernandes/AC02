package com.example.espacobemestar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
    }

    override fun onResume() {
        super.onResume()

        val mapaFragment = MapaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.layoutMapas, mapaFragment).commit()
    }
}
