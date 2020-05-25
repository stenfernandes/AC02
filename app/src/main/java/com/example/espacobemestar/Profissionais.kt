package com.example.espacobemestar

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="profissionais")
class Profissionais: Serializable  {

        @PrimaryKey
        var id: Long =0
        var nome: String = ""
        var especializacao: String =""
        var foto: String = ""

        override fun toString(): String {
            return "Profissionais(nome=' $nome')"
        }

}