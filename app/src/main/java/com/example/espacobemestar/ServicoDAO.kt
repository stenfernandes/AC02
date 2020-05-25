package com.example.espacobemestar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ServicoDAO  {

    @Query("SELECT * FROM servico where id = :id")
    fun getById(id: Long) : Servico?

    @Query("SELECT * FROM servico")
    fun findAll(): List<Servico>

    @Insert
    fun insert (servico: Servico)

    @Delete
    fun delete(servico: Servico)

}
