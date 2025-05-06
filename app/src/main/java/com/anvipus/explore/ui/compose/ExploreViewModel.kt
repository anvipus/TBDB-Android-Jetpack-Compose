package com.anvipus.explore.ui.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.anvipus.core.models.Movie
import com.anvipus.core.models.PopularListData
import com.anvipus.core.models.Resource
import com.anvipus.core.models.YoutubeTrailerListData
import com.anvipus.explore.repo.MediaRepository
import com.anvipus.explore.repo.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: MediaRepository) : ViewModel() {
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Movie>> get() = _nowPlayingMovies

    private val _searchMovies = MutableStateFlow<List<Movie>>(emptyList())
    val searchMovies: StateFlow<List<Movie>> get() = _searchMovies

    private val _searchMovies2 = MutableLiveData<Resource<PopularListData>?>()
    val searchMovies2: LiveData<Resource<PopularListData>?> get() = _searchMovies2

    private val _movies = MutableStateFlow<Resource<PopularListData>>(Resource.loading(null))
    val movies: StateFlow<Resource<PopularListData>> = _movies

    private val _videoTrailer = MutableStateFlow<Resource<YoutubeTrailerListData>>(Resource.loading(null))
    var videoTrailer: StateFlow<Resource<YoutubeTrailerListData>> = _videoTrailer

    val _videoTrailerTrigger = MutableStateFlow<String?>(null)

    val _popularMoviewTrigger = MutableStateFlow<String?>(null)

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                _popularMovies.value = repository.getPopularMovies()
                _nowPlayingMovies.value = repository.getNowPlayingMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            try {
                _searchMovies.value = repository.searchMovies(query)
                println(_searchMovies.value)
            } catch (e: Exception) {
                println("getBillDetail Error: ${e.message}")
            }
        }
    }

    fun searchMovie2(query: String) {
        viewModelScope.launch {
            repository.searchMovies2(query) // Panggil repository
                .catch { e -> _movies.value = Resource.error(e.message ?: "Unknown error") }
                .collectLatest { result ->
                    _movies.value = result
                }
        }
    }

    fun getVideoTrailer(videoId: String) {
        viewModelScope.launch {
            repository.videoTrailer(videoId)
                .catch { e -> _videoTrailer.value = Resource.error(e.message ?: "Unknown error") }
                .collectLatest { result ->
                    _videoTrailer.value = result
                }
        }
    }

    fun resetVideoTrailer() {
        _videoTrailer.value = Resource.loading(null)
        _videoTrailerTrigger.value = null
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val videoTrailerResult: StateFlow<Resource<YoutubeTrailerListData>> = _videoTrailerTrigger.flatMapLatest { id ->
        if (id.isNullOrEmpty()) flowOf(Resource.success(null))
        else repository.videoTrailer(id)
    }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.success(null))

    fun getVideoTrailer2(videoId: String) {
        if (_videoTrailerTrigger.value != videoId) {
            _videoTrailerTrigger.value = videoId
        }
    }

    fun addMovie(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }

    val moviesDb: Flow<List<Movie>> = repository.getAllMovies()
}