package com.mx.gustavo.hndz.desafioarquitecturas

import retrofit2.http.GET

interface MovieService {
    @GET("discover/movie?api_key=1a94b3ad644f93860e67eecb2d2ba325")
    suspend fun getMovies(): MovieResult
}