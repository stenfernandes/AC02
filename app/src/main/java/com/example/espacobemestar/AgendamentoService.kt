package com.example.espacobemestar

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AgendamentoService {


    val host = "https://emsystem.azurewebsites.net/api/"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAgendamento (context: Context,servico:String): List<Agendamento> {
        var agendamentos = ArrayList<Agendamento>()

        val url = "$host/agendamentos/aluno/$servico"
        val json = HttpHelper.get(url)
        agendamentos = parserJson(json)
        return agendamentos
    }

    // listar todos
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAgendamento ():  List<Agendamento> {

        val url = "$host/agendamentos/"
        val json = HttpHelper.get(url)
        val listAgendamento = parserJson<List<Agendamento>>(json)
        return listAgendamento
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAgendamento (context: Context, id: Long): Agendamento? {

        val url = "$host/agendamentos/${id}"
        val json = HttpHelper.get(url)
        val agendamento = parserJson<Agendamento>(json)
        return agendamento
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun save(agendamento: Agendamento): Response {

        val json = HttpHelper.post("$host/agendamentos", agendamento.toJson())
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}