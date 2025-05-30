package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import testcompose.composeapp.generated.resources.Res
import testcompose.composeapp.generated.resources.compose_multiplatform
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun App(vm: MainVM = viewModel { MainVM() }) {
    vm.fetchTodo()
    val safeInsets = WindowInsets.safeDrawing // or safeContent

    val density = LocalDensity.current
    val topDp = with(density) { safeInsets.getTop(density).toDp() }
    val bottomDp = with(density) { safeInsets.getBottom(density).toDp() }

    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color.White,
            surface = Color.White,
            primary = Color(0xFF6200EE),
            onPrimary = Color.White,
            onBackground = Color.Black,
            onSurface = Color.Black
        )
    ) {
        // ✅ Apply window insets + additional padding
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            val content by remember { vm.mutableState }

            Column(
                modifier = Modifier
                    .fillMaxSize().clipToBounds()
                    .padding(start = 8.dp, end = 8.dp), // your custom padding,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (content.isEmpty()) {
                    Text("Loading...")
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(top = topDp, bottom = bottomDp),
                        modifier = Modifier.fillMaxSize() // content fills padded Surface
                    ) {
                        itemsIndexed(content) { index, item ->
                            if (item.type == 0)
                                Text("${index + 1}. ${item.title}")
                            else{
                                val imageUrl = remember(index) {
                                    "https://picsum.photos/800/600?random=$index"
                                }
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = "Random image",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = painterResource(Res.drawable.compose_multiplatform),
                                    error = painterResource(Res.drawable.compose_multiplatform),
                                    fallback = painterResource(Res.drawable.compose_multiplatform)
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}