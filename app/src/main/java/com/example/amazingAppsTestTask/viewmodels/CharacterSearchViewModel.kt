package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.*
import com.example.amazingAppsTestTask.model.database.Character
import com.example.amazingAppsTestTask.model.database.CharacterDao
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val characterDao: CharacterDao
) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    init {
//        habitDao.getHabitsByType(habitType).observeForever(habitsObserver)
//        _characters.value = characterDao.getAll().value
    }

    fun demoAddCharacter(charactersFromView: List<Character>) {
        _characters.value = charactersFromView
    }

    fun saveCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.insert(character)
        }
    }

    fun retrieveCharacter(id: Int): LiveData<Character> {
        return characterDao.get(id)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class CharacterSearchViewModelFactory(
    private val characterDao: CharacterDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterSearchViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}