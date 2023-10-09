package com.mx.gustavo.hndz.desafioarquitecturas.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val favorite: Boolean = false
)