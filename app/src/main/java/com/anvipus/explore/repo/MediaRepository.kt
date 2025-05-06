package com.anvipus.explore.repo

import com.anvipus.core.api.ApiHelper
import com.anvipus.core.models.Movie
import com.anvipus.core.models.PopularListData
import com.anvipus.core.models.Resource
import com.anvipus.core.utils.Constants
import com.anvipus.explore.BuildConfig
import com.anvipus.explore.api.GeneralApi
import kotlinx.coroutines.flow.Flow
import com.anvipus.core.api.BaseApiCall
import com.anvipus.core.models.YoutubeTrailerListData
import com.anvipus.explore.db.MovieDao
import javax.inject.Inject

class MediaRepository @Inject constructor(private val apiService: GeneralApi, private val movieDao: MovieDao) {

    private val apiCall = object : BaseApiCall<PopularListData, PopularListData>() {}
    suspend fun getPopularMovies(): List<Movie> {
        return apiService.getPopularMovies("${Constants.BEARER}${BuildConfig.API_KEY}").results ?: emptyList()
    }

    suspend fun getNowPlayingMovies(): List<Movie> {
        return apiService.getNowPlayingMovies("${Constants.BEARER}${BuildConfig.API_KEY}").results ?: emptyList()
    }

    suspend fun searchMovies(query: String): List<Movie> {
        val bodyParam = HashMap<String, String>()
        bodyParam["language"] = "en-US"
        bodyParam["page"] = "1"
        bodyParam["query"] = query
        return apiService.getSearchMovies("${Constants.BEARER}${BuildConfig.API_KEY}",bodyParam).results ?: emptyList()
    }

    fun searchMovies2(query: String): Flow<Resource<PopularListData>> {
        val bodyParam = HashMap<String, String>()
        bodyParam["language"] = "en-US"
        bodyParam["page"] = "1"
        bodyParam["query"] = query
        return ApiHelper.call {
            apiService.getSearchMovies2("${Constants.BEARER}${BuildConfig.API_KEY}",bodyParam)
        }
    }

    fun videoTrailer(videoId: String): Flow<Resource<YoutubeTrailerListData>> {
        val bodyParam = HashMap<String, String>()
        bodyParam["language"] = "en-US"
        return ApiHelper.call {
            apiService.getVideoTrailer(accessToken = "${Constants.BEARER}${BuildConfig.API_KEY}", params = bodyParam, movie_id = videoId)
        }
    }

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

}