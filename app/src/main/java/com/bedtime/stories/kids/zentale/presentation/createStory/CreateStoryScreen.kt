package com.bedtime.stories.kids.zentale.presentation.createStory

import androidx.compose.foundation.Image
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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
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
import com.bedtime.stories.kids.zentale.presentation.home.ImageScrollView
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar

@Composable
fun CreateStoryScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.safe_content_padding)),
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.create_story_title_text),
                startIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onStartIconClick = {
                    navController.popBackStack()
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
                        text = stringResource(id = R.string.create_story_button_text),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onBackground
                        ),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.content_padding))
                    )

                    Icon(
                        imageVector = Icons.Filled.Bolt,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}