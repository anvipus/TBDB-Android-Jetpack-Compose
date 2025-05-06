package com.anvipus.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class YoutubeTrailer (
    @Json(name = "name")
    val name: String?,
    @Json(name = "key")
    val key: String?,
    @Json(name = "site")
    val site: String?,
    @Json(name = "id")
    val id: String?
): Parcelable