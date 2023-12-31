package com.mx.gustavo.hndz.desafioarquitecturas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                val movies = produceState<List<ServerMovie>>(initialValue = emptyList()) {
                    value = Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(MovieService::class.java)
                        .getMovies()
                        .results
                }

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
                        LazyVerticalGrid(
                            modifier = Modifier.padding(padding),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            columns = GridCells.Adaptive(120.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(movies.value) {
                                Column {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500/${it.poster_path}",
                                        contentDescription = it.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(2 / 3f)
                                    )
                                    Text(
                                        text = it.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        maxLines = 1
                                    )
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesafioArquitecturasTheme {
        Greeting("Android")
    }
}