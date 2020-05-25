package com.example.espacobemestar

import java.io.Serializable

class Usuario  : Serializable {
    var user: Long = 0
    var password: String = ""
    var name: Int = 0


    override fun toString(): String {
        return "Usuario(nome=' $name')"
    }
}