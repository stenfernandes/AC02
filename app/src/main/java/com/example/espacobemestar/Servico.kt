package com.example.espacobemestar

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "servico")
class Servico : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long =0


    @ColumnInfo(name = "nome")
    var nome: String = ""

    @ColumnInfo(name = "duracao")
    var duracao: Int =0

    @ColumnInfo(name = "valor")
    var valor: Float = 0.00f

    @ColumnInfo(name = "foto")
    var foto: String = ""

    override fun toString(): String {
        return nome
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}

