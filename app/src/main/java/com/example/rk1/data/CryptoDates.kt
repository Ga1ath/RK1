package com.example.rk1.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CryptoDates (
    @Json(name = "Response") val response: String,
    @Json(name = "Data")     val data: CryptoData
)