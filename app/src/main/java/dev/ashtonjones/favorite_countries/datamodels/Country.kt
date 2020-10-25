package dev.ashtonjones.favorite_countries.datamodels


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "favorite_countries_table")
data class Country(
    // Made country title primary key to avoid duplicate countries
    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "notes")
    var notes: String,
) : Serializable

// Will be used to hold the last of Countries from converted JSON response
data class CountryResult(val items: List<Country>)