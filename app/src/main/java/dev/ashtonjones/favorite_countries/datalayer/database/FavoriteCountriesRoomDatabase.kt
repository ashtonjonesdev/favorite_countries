package dev.ashtonjones.favorite_countries.datalayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ashtonjones.favorite_countries.datalayer.dao.CountryDao
import dev.ashtonjones.favorite_countries.datamodels.Country


@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class FavoriteCountriesRoomDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FavoriteCountriesRoomDatabase? = null

        fun getDatabase(context: Context): FavoriteCountriesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteCountriesRoomDatabase::class.java,
                    "favorite_countries_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}