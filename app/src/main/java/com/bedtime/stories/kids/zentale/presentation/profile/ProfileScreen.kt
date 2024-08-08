package com.bedtime.stories.kids.zentale.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state: ProfileViewModel.ProfileState by viewModel.state.collectAsStateWithLifecycle()
    val textCredits = buildAnnotatedString {
        append(stringResource(id = R.string.profile_text_credits))
        append(" ")
        withStyle(style = SpanStyle(color = Color.Green)) {
            append(state.textCredits)
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.profile_title_text),
                startIcon = Icons.AutoMirrored.Outlined.ArrowBack,
                onStartIconClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
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
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .weight(1f, false)
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.double_content_padding)))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = textCredits,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(
                                vertical = dimensionResource(id = R.dimen.double_content_padding),
                                horizontal = dimensionResource(id = R.dimen.content_padding)
                            )
                    )
                }
                Button(
                    shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.double_content_padding)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = {
                        viewModel.logout()
                    },
                    modifier = Modifier
                        .padding(
                            all = dimensionResource(id = R.dimen.double_content_padding)
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.profile_logout_button_text),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.content_padding))
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
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
