package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.amazingAppsTestTask.model.database.Character
import com.example.amazingAppsTestTask.model.database.CharacterDao
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterDao: CharacterDao
) : ViewModel() {
    val films: LiveData<List<Character>> = characterDao.getAll()

    fun retrieveCharacter(id: Int): LiveData<Character> {
        return characterDao.get(id)
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.delete(character)
        }
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class CharacterDetailsViewModelFactory(
    private val characterDao: CharacterDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterDetailsViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
