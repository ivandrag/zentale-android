package com.bedtime.stories.kids.zentale.presentation.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.LottieAnimation
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun LibraryScreen(
    navController: NavHostController,
    viewModel: LibraryViewModel = koinViewModel()
) {
    val state: LibraryState by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.library_title_text)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            when {
                state.isLoading -> LoadingLibraryScreen()
                state.isEmpty -> EmptyLibraryScreen()
                else -> StoriesLibraryScreen(state, viewModel, navController)
            }
        }
    }
}

@Composable
fun StoriesLibraryScreen(
    state: LibraryState,
    viewModel: LibraryViewModel,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.double_content_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.double_content_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.double_content_padding))
    ) {
        itemsIndexed(state.stories) { index, story ->
            if (index == state.stories.size - 1) {
                LaunchedEffect(index) {
                    viewModel.fetchMoreStories()
                }
            }
            StoryCard(story) {
                viewModel.setDemoStoryId(story.storyId)
                navController.navigate("story")
            }
        }
    }
}

@Composable
fun StoryCard(
    story: StoryPreview,
    onStoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStoryClick(story.storyId)
            }
    ) {
        AsyncImage(
            model = story.storyImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.large)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.content_padding)))
        Text(
            text = story.storyTitle,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.content_padding))
        )
    }
}

@Composable
fun EmptyLibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.double_content_padding))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.library_empty_library_text),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.content_padding))
            )
            Text(
                text = stringResource(id = R.string.library_empty_library_text_2),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(id = R.string.library_empty_library_text_3),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.content_padding))
            )
            Text(
                text = stringResource(id = R.string.library_empty_library_text_4),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.content_padding))
            )
        }
        LottieAnimation(
            animationResId = R.raw.tiger_rocket,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun LoadingLibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.double_content_padding))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.loading_text),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.content_padding))
            )
            LottieAnimation(
                animationResId = R.raw.loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}