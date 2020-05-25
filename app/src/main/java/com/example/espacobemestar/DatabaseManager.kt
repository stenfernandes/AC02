package com.example.espacobemestar

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: EMSDatabase

    init{
        val appContext = EMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            EMSDatabase::class.java,
            "ems.sqlite"
        ).build()
    }

    fun getProfissionaisDAO(): ProfissionaisDAO{
        return dbInstance.profissionaisDAO()
    }

    fun getServicoDAO():ServicoDAO{
        return dbInstance.servicoDAO()
    }
}