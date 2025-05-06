package com.anvipus.core.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
@Parcelize
data class Movie (
    @Json(name = "adult")
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdrop_path: String?,
    @PrimaryKey
    @Json(name = "id")
    val id: Int?,
    @Json(name = "original_language")
    val original_language: String?,
    @Json(name = "original_title")
    val original_title: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "poster_path")
    val poster_path: String? = "",
    @Json(name = "release_date")
    val release_date: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "video")
    val video: Boolean?,
    @Json(name = "vote_average")
    val vote_average: Double?,
    @Json(name = "vote_count")
    val vote_count: Double?
): Parcelable