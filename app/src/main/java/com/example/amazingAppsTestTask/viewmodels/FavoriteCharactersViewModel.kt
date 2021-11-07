package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(
    private val characterDao: CharacterDao
) : ViewModel() {

    val characters: LiveData<List<DBCharacter>> = characterDao.getAll()


    fun deleteCharacter(DBCharacter: DBCharacter) {
        viewModelScope.launch {
            characterDao.delete(DBCharacter)
        }
    }


}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class FavoriteCharactersViewModelFactory(
    private val characterDao: CharacterDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteCharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteCharactersViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}