package dev.ashtonjones.favorite_countries.datalayer.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.ashtonjones.favorite_countries.datamodels.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(country: Country)

    @Query("DELETE FROM favorite_countries_table")
    fun deleteAll()

    @Query("SELECT * from favorite_countries_table ORDER BY title ASC")
    fun getAllFavoriteCountries(): LiveData<List<Country>>


    // Method that will be called to delete a single country upon swiping it away
    @Delete
    fun deleteCountry(country: Country)

    @Update
    fun updateCountry(country: Country)
}