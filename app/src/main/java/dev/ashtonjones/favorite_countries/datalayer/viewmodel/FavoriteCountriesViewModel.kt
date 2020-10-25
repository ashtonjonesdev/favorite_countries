package dev.ashtonjones.favorite_countries.datalayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.ashtonjones.favorite_countries.datalayer.database.FavoriteCountriesRoomDatabase
import dev.ashtonjones.favorite_countries.datalayer.repository.Repository
import dev.ashtonjones.favorite_countries.datamodels.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteCountriesViewModel( application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Country>>

    init {
        val countryDao = FavoriteCountriesRoomDatabase.getDatabase(application).countryDao()
        repository = Repository(countryDao)
        allWords = repository.allFavoriteCountries
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(country: Country) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(country)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun deleteCountry(country: Country) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCountry(country)
    }

}