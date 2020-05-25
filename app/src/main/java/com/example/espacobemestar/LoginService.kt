package com.example.espacobemestar

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LoginService {

    val host = "https://urldoservico"
    val TAG = "WS_LMSApp"

    fun getServicos (context: Context): List<Servico> {

        val url = "$host/Servicos"
        val json = HttpHelper.get(url)
        return parserJson(json)

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}