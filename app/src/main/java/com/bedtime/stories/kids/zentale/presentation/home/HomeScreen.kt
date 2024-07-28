package com.bedtime.stories.kids.zentale.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.safe_content_padding)),
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.home_title_text),
                startIcon = Icons.Outlined.Person,
                onStartIconClick = {
                    navController.navigate("profile")
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(dimensionResource(id = R.dimen.double_content_padding)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f, false)
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 44.sp,
                        ),
                        text = stringResource(id = R.string.home_turn_toys_into_stories),
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.double_content_padding)))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.rabbit),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.double_content_padding)))
                    Text(
                        text = stringResource(id = R.string.home_your_stories),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    ImageScrollView(viewModel = viewModel)
                }

                Button(
                    shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.double_content_padding)
                    ),
                    onClick = {
                        navController.navigate("createStory")
                    },
                    modifier = Modifier
                        .padding(
                            all = dimensionResource(id = R.dimen.double_content_padding)
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.home_add_a_toy_photo),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.content_padding))
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun imageCell(imageResId: Int, storyId: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(storyId) }
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(150.dp, 200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ImageScrollView(viewModel: HomeViewModel) {
    val images by viewModel.allImages

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        images.keys.sorted().forEach { storyId ->
            images[storyId]?.let { imageResId ->
                imageCell(imageResId = imageResId, storyId = storyId) { selectedStoryId ->
                    viewModel.selectedStoryId.value = selectedStoryId
                    viewModel.isStoryViewPresented.value = true
                }
            }
        }
    }
}