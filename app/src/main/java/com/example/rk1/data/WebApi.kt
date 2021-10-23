package com.example.rk1.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val base_url = "https://min-api.cryptocompare.com/data/v2/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(base_url)
    .build()

interface WebApiService {
    @GET("histoday")
    suspend fun getData(
        @Query("fsym") coin: String,
        @Query("tsym") cur: String,
        @Query("limit") limit: String,
    ): CryptoDates
}

object WebApi {
    val retrofitService: WebApiService by lazy { retrofit.create(WebApiService::class.java) }
}