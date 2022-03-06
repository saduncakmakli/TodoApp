package com.example.todoapp.room

import androidx.room.*
import com.example.todoapp.entity.Work

@Dao
interface WorksDao {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun allWorks() : List<Work>

    @Insert
    suspend fun addWorkDatabase(work:Work)

    @Update
    suspend fun updateWorkDatabase(work: Work)

    @Delete
    suspend fun removeWorkDatabase(work: Work)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%' || :aramaKelimesi || '%' ")
    suspend fun searchWork(aramaKelimesi : String) : List<Work>
}