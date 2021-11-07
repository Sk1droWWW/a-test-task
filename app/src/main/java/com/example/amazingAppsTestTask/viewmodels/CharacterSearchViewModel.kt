package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.amazingAppsTestTask.model.network.model.Character
import com.example.amazingAppsTestTask.model.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {

    fun searchCharacters(query: String) {
        listData = repository.searchCharacters(query).cachedIn(viewModelScope)
    }

//    private val _listData = MutableStateFlow<PagingData<Character>?>(null)
    var listData: Flow<PagingData<Character>>? = null

    init {
        searchCharacters("")
    }

    fun saveCharacter(character: Character) {
        viewModelScope.launch {
//            characterDao.insert(character)
        }
    }

/*    fun retrieveCharacter(id: Int): LiveData<Character> {
        return characterDao.get(id)
    }*/
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class CharacterSearchViewModelFactory(
    private val repository: StarWarsRepository
//    private val characterDao: CharacterDao,
//    private val apiService: StarWarsApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterSearchViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}