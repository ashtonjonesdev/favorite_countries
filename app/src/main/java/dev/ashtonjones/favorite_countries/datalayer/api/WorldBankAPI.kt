package dev.ashtonjones.favorite_countries.datalayer.api

import dev.ashtonjones.favorite_countries.datamodels.CountryResult
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WorldBankAPI {
    private val service: WorldBankService

    companion object {
        //1
        const val BASE_URL = "https://api.worldbank.org/v2/"
    }

    init {

        val client = OkHttpClient.Builder().build()


        // 2
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL)
            //3
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        //4
        service = retrofit.create(WorldBankService::class.java)
    }

    fun getAllCountries(callback: Callback<CountryResult>) {
        val call = service.getAllCountries()
        call.enqueue(callback)
    }
}