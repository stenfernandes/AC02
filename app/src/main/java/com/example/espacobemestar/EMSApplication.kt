package com.example.espacobemestar

import android.app.Application
import android.content.Context

class EMSApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance= this
    }

    companion object{
        private var appInstance: EMSApplication? = null

        fun getInstance(): EMSApplication{
            if (appInstance == null){
                throw IllegalStateException("Configurar application do Android Manifest")
            }
            return appInstance!!

        }
    }

}