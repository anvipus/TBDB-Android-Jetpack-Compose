package com.anvipus.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class YoutubeTrailerListData (
    @Json(name = "id")
    val id: Int?,
    @Json(name = "results")
    val results: List<YoutubeTrailer>?
): Parcelable