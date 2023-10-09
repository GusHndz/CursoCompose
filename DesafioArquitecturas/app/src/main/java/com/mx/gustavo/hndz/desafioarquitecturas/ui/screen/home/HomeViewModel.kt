package com.mx.gustavo.hndz.desafioarquitecturas.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.gustavo.hndz.desafioarquitecturas.data.Movie
import com.mx.gustavo.hndz.desafioarquitecturas.data.MovieRepository
import com.mx.gustavo.hndz.desafioarquitecturas.data.local.MoviesDao
import com.mx.gustavo.hndz.desafioarquitecturas.data.remote.MovieService
import com.mx.gustavo.hndz.desafioarquitecturas.data.toLocalMovie
import com.mx.gustavo.hndz.desafioarquitecturas.data.toMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    //    State
//    var state by mutableStateOf(UiState())
//        private set

    //    LiveData
//    private val _state = MutableLiveData<UiState>()
//    val state:LiveData<UiState> = _state

    //    StateFlow
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.requestMovies()

            repository.movies.collect { movies ->
                _state.value = UiState(
                    loading = false,
                    movie = movies
                )
            }

//            state = UiState(
//                Retrofit.Builder()
//                    .baseUrl("https://api.themoviedb.org/3/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(MovieService::class.java)
//                    .getMovies()
//                    .results
//            )

//            val isDbEmpty = dao.getMovieCount() == 0
//
//            if (isDbEmpty) {
//                //            LiveData
//                _state.value = UiState(loading = true)
////            delay(2000)
//
//                dao.insertAllMovies(
//                    Retrofit.Builder()
//                        .baseUrl("https://api.themoviedb.org/3/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                        .create(MovieService::class.java)
//                        .getMovies()
//                        .results
//                        .map { it.toLocalMovie() }
//                )
//            }
//
//            dao.getMovies().collect { movies ->
//                _state.value = UiState(
//                    loading = false,
//                    movie = movies.map { it.toMovie() }
//                )
//            }
        }

    }

    fun onMovieClick(movie: Movie) {
//        val movies = _state.value.movie.toMutableList()
//        movies.replaceAll {
//            if (it.id == movie.id) {
//                movie.copy(favorite = !it.favorite)
//            } else {
//                it
//            }
//        }
//
//        _state.value = _state.value.copy(movie = movies)

        viewModelScope.launch {
            repository.updateMovie(movie = movie.copy(favorite = !movie.favorite))
//            dao.updateMovie(movie = movie.copy(favorite = !movie.favorite).toLocalMovie())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: List<Movie> = emptyList()
    )
}