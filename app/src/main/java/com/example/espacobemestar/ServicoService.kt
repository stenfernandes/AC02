@file:Suppress("UNREACHABLE_CODE")

package com.example.espacobemestar

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ServicoService {

    val host = "https://emsystem.azurewebsites.net/api/"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getServico (context: Context): List<Servico> {
        var servico = ArrayList<Servico>()
        if (AndroidUtils.isInternetDisponivel()) {

            val url = "$host/servicos"
            val json = HttpHelper.get(url)
            servico = parserJson(json)
            //return parserJson<List<Servico>>(json)

            //Para Salvar Offline
            for (s in servico){
                saveOffline(s)
            }
            return servico

        } else {
            val dao = DatabaseManager.getServicoDAO()
            return dao.findAll()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getServico (context: Context, id: Long): Servico? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/servicos/${id}"
            val json = HttpHelper.get(url)
            val servico = parserJson<Servico>(json)

            return servico
        } else {
            val dao = DatabaseManager.getServicoDAO()
            val servico = dao.getById(id)
            return servico
        }

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun save(servico: Servico): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/servicos", servico.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(servico)
            return Response("OK", "Serviço salvo no dispositivo")
        }
    }

    fun saveOffline(servico: Servico) : Boolean {
        val dao = DatabaseManager.getServicoDAO()

        if (! existeServico(servico)) {
            dao.insert(servico)
        }

        return true

    }

    fun existeServico(servico: Servico): Boolean {
        val dao = DatabaseManager.getServicoDAO()
        return dao.getById(servico.id) != null
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun delete(servico: Servico): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/servicos/${servico.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getServicoDAO()
            dao.delete(servico)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }


}