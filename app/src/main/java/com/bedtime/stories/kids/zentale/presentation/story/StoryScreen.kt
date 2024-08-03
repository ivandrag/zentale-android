package com.bedtime.stories.kids.zentale.presentation.story

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.shared.model.StoryType
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun StoryScreen(
    navController: NavHostController
) {
    val viewModel: StoryViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
//    LaunchedEffect(storyId) {
//        viewModel.loadStory(storyId)
//    }

//    when (storyType) {
//        is StoryType.Create -> {
//            Text("Creating story with ID: ${storyType.storyId}, Language: ${storyType.language}")
//        }
//        is StoryType.ViewDemo -> {
//            Text("Viewing demo story with ID: ${storyType.storyId}")
//        }
//        is StoryType.ViewStory -> {
//            Text("Viewing story with ID: ${storyType.storyId}")
//        }
//        else -> {
//            Text("No story type found")
//        }
//    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.safe_content_padding)),
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                when (state.status) {
                    "loading" -> Text(text = "Loading...")
                    "success" -> {
                        Column(
                            modifier = Modifier
//                                .verticalScroll(rememberScrollState())
//                            .fillMaxSize()
                        ) {
                            AsyncImage(
                                model = state.storyImage,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = state.storyTitle,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.storyContent,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    lineHeight = 30.sp
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
