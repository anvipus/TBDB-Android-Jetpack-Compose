package com.anvipus.explore.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import com.anvipus.core.models.Movie
import com.anvipus.core.models.Status
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(viewModel: ExploreViewModel = hiltViewModel(), navController: NavController) {
    val popularMovies = viewModel.popularMovies.collectAsState().value
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState().value
    val trailerState by viewModel.videoTrailer.collectAsState()
    var videoKey by remember { mutableStateOf<String?>(null) }

    val systemUiController = rememberSystemUiController()
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    var isSheetOpen by remember { mutableStateOf(false) }
    val videoTrailerResult by viewModel.videoTrailerResult.collectAsState()

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.White)
    }

    LaunchedEffect(videoTrailerResult) {
        if (videoTrailerResult.status == Status.SUCCESS &&
            !videoTrailerResult.data?.results.isNullOrEmpty()) {
            videoKey = videoTrailerResult.data?.results!![0].key
            isSheetOpen = true
            viewModel.resetVideoTrailer()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie DB") },
                actions = {
                    IconButton(onClick = { navController.navigate("searchList") }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            val (popularTitle, popularList, nowPlayingTitle, nowPlayingList) = createRefs()

            Text(
                text = "Popular",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(popularTitle) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            LazyRow(
                modifier = Modifier
                    .constrainAs(popularList) {
                        top.linkTo(popularTitle.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(200.dp)
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(popularMovies.size) { index ->
                    MovieCard(movie = popularMovies[index]) { movie ->
                        selectedMovie = movie
                        isSheetOpen = true
                        viewModel.getVideoTrailer2(movie.id.toString())
                    }
                }
            }

            Text(
                text = "Now Playing",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(nowPlayingTitle) {
                        top.linkTo(popularList.bottom, margin = 24.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            LazyColumn(
                modifier = Modifier
                    .constrainAs(nowPlayingList) {
                        top.linkTo(nowPlayingTitle.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(nowPlayingMovies.size) { index ->
                    VerticalListMovieCard(movie = nowPlayingMovies[index]) { movie ->
                        selectedMovie = movie
                        isSheetOpen = true
                        viewModel.getVideoTrailer2(movie.id.toString())
                    }
                }
            }
        }
    }

    if (isSheetOpen && selectedMovie != null) {
        MovieDetailBottomSheet(
            movie = selectedMovie!!,
            videoKey = videoKey
        ) {
            isSheetOpen = false
        }
    }
}



@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(140.dp)
            .height(220.dp)
            .clickable { onClick(movie) },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (imageRef, titleRef, descRef) = createRefs()

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://media.themoviedb.org/t/p/w220_and_h330_face${movie.poster_path.orEmpty()}")
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = movie.title ?: "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.value(120.dp)
                    }
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )

            Text(
                text = movie.title ?: "No Title",
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(titleRef) {
                        top.linkTo(imageRef.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 6.dp)
            )

            Text(
                text = movie.overview ?: "No Description",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 3,
                modifier = Modifier
                    .constrainAs(descRef) {
                        top.linkTo(titleRef.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 6.dp)
            )
        }
    }
}




@Composable
fun VerticalListMovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(movie) }
            .padding(8.dp)
    ) {
        val (imageRef, titleRef, descRef) = createRefs()

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.themoviedb.org/t/p/w220_and_h330_face${movie.poster_path.orEmpty()}")
                .crossfade(true)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = movie.title ?: "Movie Poster",
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .size(width = 80.dp, height = 120.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.title ?: "No Title",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(imageRef.top)
                start.linkTo(imageRef.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = movie.overview ?: "No Description",
            style = MaterialTheme.typography.bodySmall,
            maxLines = 4,
            modifier = Modifier.constrainAs(descRef) {
                top.linkTo(titleRef.bottom, margin = 4.dp)
                bottom.linkTo(imageRef.bottom)
                start.linkTo(imageRef.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}



