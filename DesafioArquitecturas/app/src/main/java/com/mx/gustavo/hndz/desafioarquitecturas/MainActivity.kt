package com.mx.gustavo.hndz.desafioarquitecturas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mx.gustavo.hndz.desafioarquitecturas.ui.theme.DesafioArquitecturasTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//1a94b3ad644f93860e67eecb2d2ba325
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioArquitecturasTheme {
                val viewModel: MainViewModel = viewModel()
                // LiveData
//                val state by viewModel.state.observeAsState(MainViewModel.UiState())
                // StateFlow
                val state by viewModel.state.collectAsState()

//                val movies = produceState<List<ServerMovie>>(initialValue = emptyList()) {
//                    value = Retrofit.Builder()
//                        .baseUrl("https://api.themoviedb.org/3/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                        .create(MovieService::class.java)
//                        .getMovies()
//                        .results
//                }

                // A surface container using the 'background' color from the theme
//                LaunchedEffect(key1 = true){
//                    Retrofit.Builder()
//                        .baseUrl("https://api.themoviedb.org/3/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                        .create(MovieService::class.java)
//                        .getMovies()
//                        .results
//                        .forEach {
//                            Log.i("GHP",it.toString())
//                        }
//                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Movies")
                                }
                            )
                        }
                    ) { padding ->
                        if (state.loading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }

                        }

                        if (state.movie.isNotEmpty()) {
                            LazyVerticalGrid(
                                modifier = Modifier.padding(padding),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                columns = GridCells.Adaptive(120.dp),
                                contentPadding = PaddingValues(4.dp)
                            ) {
//                            items(viewModel.state.movie) {
                                // LiveData/StateFlow
                                items(state.movie) { movie ->
                                    MoveItems(movie = movie) {
                                        viewModel.onMovieClick(movie)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Pelicula: $name",
        modifier = modifier
    )
}

@Composable
fun MoveItems(movie: ServerMovie, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }) {
        Box {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
            )

            if (movie.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color(0xFFEC0F21)
                )
            }
        }

        Text(
            text = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesafioArquitecturasTheme {
//        Greeting("Android")
        MoveItems(
            movie = ServerMovie(
                adult = false,
                backdrop_path = "https://image.tmdb.org/t/p/w500/1",
                genre_ids = listOf(),
                id = 1,
                original_language = "en",
                original_title = "Spider-Man: No Way Home",
                overview = "Spider-Man: No Way Home",
                popularity = 0.0,
                poster_path = "https://image.tmdb.org/t/p/w500/1",
                release_date = "2021-12-15",
                title = "Spider-Man: No Way Home",
                video = false,
                vote_average = 0.0,
                vote_count = 0,
                favorite = true
            )
        ) {

        }
    }
}