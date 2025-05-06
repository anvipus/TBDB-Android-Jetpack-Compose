package com.anvipus.explore.repo

import com.anvipus.core.models.Movie
import com.anvipus.core.models.PopularListData
import com.anvipus.core.models.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getNowPlayingMovies(): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    fun searchMovies2(query: String): Flow<Resource<PopularListData>>
}