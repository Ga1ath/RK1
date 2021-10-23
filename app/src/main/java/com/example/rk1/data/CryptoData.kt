package com.example.rk1.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CryptoData (
    @Json(name = "Data") val data: List<CurrentCryptoData>
)