package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.*
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.repository.StarWarsRepository
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    val characters: LiveData<List<Character>> = repository.favorites.asLiveData()


    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            repository.deleteCharacter(character)
        }
    }


}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class FavoriteCharactersViewModelFactory(
    private val repository: StarWarsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteCharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteCharactersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}