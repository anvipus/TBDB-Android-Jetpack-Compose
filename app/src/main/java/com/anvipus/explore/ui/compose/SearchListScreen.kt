package com.anvipus.explore.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.anvipus.core.models.Movie
import com.anvipus.core.models.Status
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchListScreen(viewModel: ExploreViewModel = hiltViewModel(), navController: NavController) {
    val searchMovies = viewModel.searchMovies.collectAsState().value
    val movieState by viewModel.movies.collectAsState()
    val searchMoviesState by viewModel.searchMovies2.observeAsState()
    val popularMovies = viewModel.popularMovies.collectAsState().value
    var searchQuery by remember { mutableStateOf("") }
    val trailerState by viewModel.videoTrailer.collectAsState()
    var videoKey by remember { mutableStateOf<String?>(null) }
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    var isSheetOpen by remember { mutableStateOf(false) }

    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(color = Color.White) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val videoTrailerResult by viewModel.videoTrailerResult.collectAsState()

    LaunchedEffect(videoTrailerResult) {
        if (videoTrailerResult.status == Status.SUCCESS &&
            !videoTrailerResult.data?.results.isNullOrEmpty()
        ) {
            videoKey = videoTrailerResult.data?.results!![0].key
            isSheetOpen = true
            viewModel.resetVideoTrailer()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { query ->
                            searchQuery = query
                            viewModel.searchMovie2(query)
                        },
                        placeholder = { Text("Search movies...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
                .padding(16.dp)
        ) {
            val (contentRef, loadingRef, errorRef) = createRefs()

            if (searchQuery.isEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .constrainAs(contentRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        },
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(popularMovies.size) { index ->
                        MovieCard(movie = popularMovies[index]) { movie ->
                            selectedMovie = movie
                            isSheetOpen = true
                            viewModel.getVideoTrailer2(movie.id.toString())
                        }
                    }
                }
            } else {
                when (movieState.status) {
                    Status.LOADING -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .constrainAs(loadingRef) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }

                    Status.SUCCESS -> {
                        if (!movieState.data?.results.isNullOrEmpty()) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .constrainAs(contentRef) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                        height = Dimension.fillToConstraints
                                    },
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 8.dp)
                            ) {
                                items(movieState.data?.results!!.size) { index ->
                                    MovieCard(movie = movieState.data?.results!![index]) { movie ->
                                        selectedMovie = movie
                                        isSheetOpen = true
                                        viewModel.getVideoTrailer2(movie.id.toString())
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "No movies found",
                                color = Color.Red,
                                modifier = Modifier.constrainAs(errorRef) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                            )
                        }
                    }

                    Status.ERROR, Status.TIMEOUT -> {
                        Text(
                            text = "Error: ${movieState.msg ?: "No movies found"}",
                            color = Color.Red,
                            modifier = Modifier.constrainAs(errorRef) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }

                    else -> {}
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