package com.anvipus.explore.api

import com.anvipus.core.models.PopularListData
import com.anvipus.core.models.YoutubeTrailerListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GeneralApi {
    @GET("movie/popular")
    fun getPopularMovie(@Header("Authorization") accessToken: String,
                        @QueryMap params: Map<String, String>): Call<PopularListData>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Header("Authorization") accessToken: String,
                        @QueryMap params: Map<String, String>): Call<PopularListData>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoTrailer(@Header("Authorization") accessToken: String,
                        @Path("movie_id") movie_id: String,
                        @QueryMap params: Map<String, String>): YoutubeTrailerListData

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): PopularListData

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") authorization: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): PopularListData

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Header("Authorization") accessToken: String,
        @QueryMap params: Map<String, String>
    ): PopularListData

    @GET("search/movie")
    suspend fun getSearchMovies2(
        @Header("Authorization") accessToken: String,
        @QueryMap params: Map<String, String>
    ): PopularListData
}