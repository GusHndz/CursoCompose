package com.mx.gustavo.hndz.desafioarquitecturas.data.remote

import com.mx.gustavo.hndz.desafioarquitecturas.data.Movie
import com.mx.gustavo.hndz.desafioarquitecturas.data.toMovie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    suspend fun getMovies(): List<Movie> {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
            .getMovies()
            .results
            .map { it.toMovie() }
    }

}