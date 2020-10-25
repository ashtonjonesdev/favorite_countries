package dev.ashtonjones.favorite_countries.datalayer.api

import dev.ashtonjones.favorite_countries.datamodels.CountryResult
import retrofit2.Call
import retrofit2.http.GET

interface WorldBankService {

    @GET("/countries?format=json")
    fun getAllCountries(): Call<CountryResult>



}