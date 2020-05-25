package com.example.espacobemestar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfissionaisDAO {

    @Query("SELECT * FROM profissionais where id = :id")
    fun gatById(id:Long):Profissionais?

    @Query("SELECT * FROM profissionais")
    fun findAll(): List<Profissionais>

    @Insert
    fun insert (profissionais: Profissionais)

    @Delete
    fun delete(profissionais: Profissionais)

}