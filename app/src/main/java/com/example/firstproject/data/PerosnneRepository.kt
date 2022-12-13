package com.example.firstproject.data

import androidx.lifecycle.LiveData
import com.example.firstproject.model.Personne

class PerosnneRepository(private val personneDao : PersonneDAO) {

    val readAllData: LiveData<List<Personne>> = personneDao.getAllData()

    suspend fun addPersonne(personne: Personne){
        personneDao.insert(personne)
    }

    suspend fun updatePersonne(personne: Personne){
        personneDao.update(personne)
    }
}