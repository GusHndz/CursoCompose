package com.mx.gustavo.hndz.desafioarquitecturas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

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
//            state = UiState(
//                Retrofit.Builder()
//                    .baseUrl("https://api.themoviedb.org/3/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(MovieService::class.java)
//                    .getMovies()
//                    .results
//            )
//            LiveData
            _state.value = UiState(loading = true)
//            delay(2000)
            _state.value = UiState(
                loading = false,
                movie = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService::class.java)
                    .getMovies()
                    .results
            )
        }

    }

    fun onMovieClick(movie: ServerMovie) {
        val movies = _state.value.movie.toMutableList()
        movies.replaceAll {
            if (it.id == movie.id) {
                movie.copy(favorite = !it.favorite)
            } else {
                it
            }
        }

        _state.value = _state.value.copy(movie = movies)
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: List<ServerMovie> = emptyList()
    )
}