package com.example.espacobemestar

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=arrayOf(Profissionais::class, Servico::class), version = 1)
abstract class EMSDatabase : RoomDatabase() {
    abstract fun profissionaisDAO(): ProfissionaisDAO
    abstract fun servicoDAO():ServicoDAO
}