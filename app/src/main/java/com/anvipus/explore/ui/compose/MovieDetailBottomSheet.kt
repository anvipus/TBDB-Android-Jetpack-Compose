package com.anvipus.explore.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.anvipus.core.models.Movie
import com.anvipus.explore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailBottomSheet(movie: Movie, videoKey: String?, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(true) }

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = {
                isSheetOpen = false
                onDismiss()
            },
            sheetState = sheetState
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val (closeButton, thumbnail, playButton, title, description) = createRefs()

                IconButton(
                    onClick = {
                        isSheetOpen = false
                        onDismiss() // **Menutup BottomSheet**
                    },
                    modifier = Modifier.constrainAs(closeButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
                val url = "https://img.youtube.com/vi/${videoKey}/mqdefault.jpg"
                val url2 = "https://img.youtube.com/vi/$videoKey/0.jpg"
                videoKey?.let {
                    Image(
                        painter = rememberAsyncImagePainter(url),
                        contentDescription = "Video Thumbnail",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .constrainAs(thumbnail) {
                                top.linkTo(closeButton.bottom, margin = 8.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Play Button",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .constrainAs(playButton) {
                            centerTo(thumbnail)
                        }
                )

                Text(
                    text = movie.title ?: "No Title",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(thumbnail.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = movie.overview ?: "No Description",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.constrainAs(description) {
                        top.linkTo(title.bottom, margin = 4.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}

