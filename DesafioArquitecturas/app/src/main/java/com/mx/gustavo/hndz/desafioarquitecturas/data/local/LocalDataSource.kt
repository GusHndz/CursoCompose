package com.mx.gustavo.hndz.desafioarquitecturas.data.local

import com.mx.gustavo.hndz.desafioarquitecturas.data.Movie
import com.mx.gustavo.hndz.desafioarquitecturas.data.toLocalMovie
import com.mx.gustavo.hndz.desafioarquitecturas.data.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: MoviesDao) {

    val movies: Flow<List<Movie>> = dao.getMovies().map {
        it.map { localMovie -> localMovie.toMovie() }
    }

    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun insertMovie(movie: List<Movie>) {
        dao.insertAllMovies(movie.map {  it.toLocalMovie()})
    }

    suspend fun countMovies(): Int {
        return dao.getMovieCount()
    }

}