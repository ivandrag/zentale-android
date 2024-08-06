package com.bedtime.stories.kids.zentale.presentation.story

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.LottieAnimation
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import org.koin.androidx.compose.koinViewModel

private const val SUCCESS_STATUS = "success"

@Composable
fun StoryScreen(
    navController: NavHostController
) {
    val viewModel: StoryViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.story_title_text),
                startIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onStartIconClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state.status) {
                null -> StoryLoadingScreen()
                SUCCESS_STATUS -> StoryContent(state)
            }
        }
    }
}

@Composable
fun StoryContent(storyState: StoryState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            AsyncImage(
                model = storyState.storyImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = storyState.storyTitle,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.double_content_padding))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.content_padding)))
            Text(
                text = storyState.storyContent,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 30.sp
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.double_content_padding))
            )
        }
    }
}

@Composable
fun StoryLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.double_content_padding))
                    .fillMaxWidth(),
                text = stringResource(id = R.string.story_loading_text),
                textAlign = TextAlign.Center
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
