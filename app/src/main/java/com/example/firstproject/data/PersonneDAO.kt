package com.example.firstproject.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firstproject.model.Personne

@Dao
interface PersonneDAO {
    @Insert
    suspend fun insert(personne: Personne) : Long

    @Query("SELECT * FROM Personne ORDER BY anniversaire ASC")
    fun getAllData() : LiveData<List<Personne>>

    @Update
    suspend fun update(personne: Personne)

    @Delete
    fun delete(personne: Personne)
}