package com.mx.gustavo.hndz.desafioarquitecturas.data

import com.mx.gustavo.hndz.desafioarquitecturas.data.local.LocalDataSource
import com.mx.gustavo.hndz.desafioarquitecturas.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val movies: Flow<List<Movie>> = localDataSource.movies

    suspend fun requestMovies() {
        val isDbEmpty = localDataSource.countMovies() == 0

        if (isDbEmpty) {
            val lstMovie = remoteDataSource.getMovies()

            localDataSource.insertMovie(lstMovie)
        }
    }

    suspend fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    suspend fun insertMovie(movie: List<Movie>) {
        localDataSource.insertMovie(movie)
    }
}