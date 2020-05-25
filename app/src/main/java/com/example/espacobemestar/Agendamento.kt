package com.example.espacobemestar


import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable
import java.util.*

@Entity(tableName = "agendamento")
class Agendamento : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long =0

    @ColumnInfo(name = "username")
    var username: String = ""

    @ColumnInfo(name = "servico")
    var servico: String = ""

    @ColumnInfo(name = "data")
    var data: String = ""

    override fun toString(): String {
        return data
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}