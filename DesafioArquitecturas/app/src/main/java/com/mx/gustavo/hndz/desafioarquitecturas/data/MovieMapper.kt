package com.mx.gustavo.hndz.desafioarquitecturas.data

import com.mx.gustavo.hndz.desafioarquitecturas.data.local.LocalMovie
import com.mx.gustavo.hndz.desafioarquitecturas.data.remote.ServerMovie

fun LocalMovie.toMovie() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster_path = this.poster_path,
    favorite = this.favorite
)

fun ServerMovie.toLocalMovie() = LocalMovie(
    id = 0,
    title = this.title,
    overview = this.overview,
    poster_path = this.poster_path,
    favorite = this.favorite
)
fun ServerMovie.toMovie() = Movie(
    id = 0,
    title = this.title,
    overview = this.overview,
    poster_path = this.poster_path,
    favorite = this.favorite
)

fun Movie.toLocalMovie() = LocalMovie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster_path = this.poster_path,
    favorite = this.favorite
)
