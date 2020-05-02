package com.example.espacobemestar

import java.io.Serializable

class Profissionais: Serializable  {
        var id: Long =0
        var nome: String = ""
        var especializacao: String =""
        var foto: String = ""

        override fun toString(): String {
            return "Profissionais(nome=' $nome')"
        }

}