package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.*
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.domain.repository.StarWarsRepository
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _btnState = MutableLiveData<Boolean>()
    val btnState: LiveData<Boolean>
        get() = _btnState

    private val _filmList = MutableLiveData<List<Film>>()
    val filmList: LiveData<List<Film>>
        get() = _filmList

    fun retrieveFilmList(character: Character) {
        viewModelScope.launch {
            _filmList.value = repository.retrieveFilms(character)
        }
    }

    fun saveCharacter(character: Character) {
        viewModelScope.launch {
            _filmList.value?.let { repository.saveCharacterAndFilms(character, it) }
        }

        setButtonsState(true)
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            repository.deleteCharacter(character)
        }

        setButtonsState(false)
    }

    fun setButtonsState(state: Boolean) {
        _btnState.postValue(state)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class CharacterDetailsViewModelFactory(
    private val repository: StarWarsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
