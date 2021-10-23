package com.example.rk1.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrentCryptoData (
    @Json(name = "time") val time: Int,
    @Json(name = "high") val high: Float,
    @Json(name = "low") val low: Float,
    @Json(name = "open") val open: Float,
    @Json(name = "close") val close: Float
)