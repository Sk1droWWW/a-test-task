package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.DBCharacter

class CharacterDetailsViewModel(
    private val characterDao: CharacterDao
) : ViewModel() {
//    val films: LiveData<List<DBCharacter>> = characterDao.getFavorites().asLiveData()



    fun deleteCharacter(character: DBCharacter) {
        /*viewModelScope.launch {
            characterDao.delete(DBCharacter)
        }*/
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
