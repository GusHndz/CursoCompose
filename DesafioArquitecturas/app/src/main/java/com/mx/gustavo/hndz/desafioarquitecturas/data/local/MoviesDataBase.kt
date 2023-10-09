package com.mx.gustavo.hndz.desafioarquitecturas.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Database(entities = [LocalMovie::class], version = 1)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

@Dao
interface MoviesDao {
//    @Query("SELECT * FROM LocalMovie")
//    suspend fun getMovies(): List<LocalMovie>

    @Query("SELECT * FROM LocalMovie")
    fun getMovies(): Flow<List<LocalMovie>>

    @Update
    suspend fun updateMovie(movie: LocalMovie)

    @Insert
    suspend fun insertAllMovies(movie: List<LocalMovie>)

    @Query("SELECT COUNT(*) FROM LocalMovie")
    suspend fun getMovieCount(): Int
}

@Entity
data class LocalMovie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val favorite: Boolean = false
)