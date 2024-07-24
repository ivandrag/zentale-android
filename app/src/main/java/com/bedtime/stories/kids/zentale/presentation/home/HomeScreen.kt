package com.bedtime.stories.kids.zentale.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar

@Composable
fun HomeScreen(
    navController: NavHostController
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
                    .fillMaxHeight(),
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
                    onClick = { },
                    modifier = Modifier
                        .padding(
                            all = dimensionResource(id = R.dimen.double_content_padding)
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.home_add_a_toy_photo),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onBackground
                        ),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.content_padding))
                    )
                }
            }
        }
    }
}
