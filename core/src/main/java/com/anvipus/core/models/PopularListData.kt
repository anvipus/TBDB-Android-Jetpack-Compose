package com.anvipus.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PopularListData (
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<Movie>?,
    @Json(name = "total_pages")
    val total_pages: Int?,
    @Json(name = "total_results")
    val total_results: Int?
): Parcelable
