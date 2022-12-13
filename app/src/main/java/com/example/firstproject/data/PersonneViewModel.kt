package com.example.firstproject.data

import android.app.Application
import androidx.lifecycle.*
import com.example.firstproject.model.Personne
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonneViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Personne>>
    private val repository: PerosnneRepository

    init {
        val personneDAO = AppDatabase.getInstance(application).personneDao()
        repository = PerosnneRepository(personneDAO)
        readAllData = repository.readAllData
    }


    fun addPersonne(personne: Personne){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPersonne(personne)
        }
    }

    fun updatePersonne(personne: Personne){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePersonne(personne)
        }
    }
}