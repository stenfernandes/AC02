package com.example.espacobemestar

import android.content.Context
import java.security.AccessControlContext

object ProfissionaisService {
    fun getProfissionais (context: Context): List<Profissionais> {
        val profissionais = mutableListOf<Profissionais>()

        for (i in 1..5) {
            val prof = Profissionais()
            prof.nome = "Alana Martins $i"
            prof.especializacao = "Massoterapia"
            prof.foto = "https://www.estudeseg.com.br/uploads/noticias/89/conheca-o-trabalho-do-profissional-tecnico-em-mass_1.jpg"
            profissionais.add(prof)
        }
        return profissionais
    }


}