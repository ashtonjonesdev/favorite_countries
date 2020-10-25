package dev.ashtonjones.favorite_countries.datalayer.repository

import androidx.lifecycle.LiveData
import dev.ashtonjones.favorite_countries.datalayer.dao.CountryDao
import dev.ashtonjones.favorite_countries.datamodels.Country

class Repository(private val countryDao: CountryDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFavoriteCountries: LiveData<List<Country>> = countryDao.getAllFavoriteCountries()

    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }

    suspend fun deleteCountry(country: Country) {
        countryDao.deleteCountry(country)
    }
}