package com.example.espacobemestar

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UsuarioService {

    val host = "https://emsystem.azurewebsites.net/api/"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun Login (context: Context, user: String, password: String): Boolean {

        var retorno = false

        if (AndroidUtils.isInternetDisponivel()) {

            val url = "$host/Usuario"
            val json = HttpHelper.get("$host/Usuario/$user/$password")
            retorno = parserJson<Boolean>(json)
            //return parserJson<Bool>(json)

            //Para Salvar Offline
            //for (s in servico){
            //    ServicoService.saveOffline(s)
            //}


        } else {
            //val dao = DatabaseManager.getServicoDAO()
            //return dao.findAll()
        }
        return retorno
    }
    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}