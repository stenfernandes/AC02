package com.example.espacobemestar

import java.io.Serializable


class Servico : Serializable {
    var id: Long =0
    var nome: String = ""
    var duracao: Int =0
    var valor: Float = 0.00f
    var foto: String = ""

    override fun toString(): String {
        return "Servico(nome=' $nome')"
    }

}