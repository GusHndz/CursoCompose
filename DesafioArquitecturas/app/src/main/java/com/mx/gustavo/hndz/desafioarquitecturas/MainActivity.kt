package com.mx.gustavo.hndz.desafioarquitecturas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.mx.gustavo.hndz.desafioarquitecturas.data.Movie
import com.mx.gustavo.hndz.desafioarquitecturas.data.MovieRepository
import com.mx.gustavo.hndz.desafioarquitecturas.data.local.LocalDataSource
import com.mx.gustavo.hndz.desafioarquitecturas.data.local.MoviesDataBase
import com.mx.gustavo.hndz.desafioarquitecturas.data.remote.RemoteDataSource
import com.mx.gustavo.hndz.desafioarquitecturas.ui.screen.home.Home
import com.mx.gustavo.hndz.desafioarquitecturas.ui.screen.home.MoveItems
import com.mx.gustavo.hndz.desafioarquitecturas.ui.theme.DesafioArquitecturasTheme

//1a94b3ad644f93860e67eecb2d2ba325
class MainActivity : ComponentActivity() {

    private lateinit var db: MoviesDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.db  = Room.databaseBuilder(
            this,
            MoviesDataBase::class.java, "movies-db"
        ).build()

        val repository = MovieRepository(
            localDataSource = LocalDataSource(db.moviesDao()),
            remoteDataSource = RemoteDataSource()
        )

        setContent {
            Home(repository)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesafioArquitecturasTheme {
        MoveItems(
            movie = Movie(
                id = 1,
                overview = "Spider-Man: No Way Home",
                poster_path = "https://image.tmdb.org/t/p/w500/1",
                title = "Spider-Man: No Way Home",
                favorite = true
            )
        ) {

        }
    }
}