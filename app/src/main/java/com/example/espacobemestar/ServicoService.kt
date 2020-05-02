package com.example.espacobemestar

import android.content.Context
import java.security.AccessControlContext

object ServicoService {
    fun getServico (context: Context): List<Servico> {
        val servico = mutableListOf<Servico>()

        for (i in 1..5) {
            val serv = Servico()
            serv.nome = "Massoterapia $i"
            serv.duracao = i
            serv.valor =  i + 100.99f
            serv.foto = "http://www.acupunturapopular.com.br/wp-content/uploads/2019/12/1-1140x641.jpg"
            servico.add(serv)
        }
        return servico
    }
}