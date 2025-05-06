package com.anvipus.explore.repo

import com.anvipus.core.models.Movie
import com.anvipus.core.models.PopularListData
import com.anvipus.core.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MovieRepositoryImpl @Inject constructor() : MovieRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        return listOf() // Data dummy atau panggilan API
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return listOf()
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return listOf()
    }

    override fun searchMovies2(query: String): Flow<Resource<PopularListData>> {
        return flow { emit(Resource.loading(null)) }
    }
}
