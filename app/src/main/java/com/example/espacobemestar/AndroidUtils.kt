@file:Suppress("DEPRECATION")

package com.example.espacobemestar

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

object AndroidUtils {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isInternetDisponivel(): Boolean  {

        val conexao = EMSApplication.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map{conexao.getNetworkInfo(it)}.any {it.state == NetworkInfo.State.CONNECTED}

    }

}