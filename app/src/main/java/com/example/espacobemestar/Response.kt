package com.example.espacobemestar

data class Response(val status:String, val msg:String) {
    fun isOK() = "OK" .equals(status,ignoreCase = true)


}