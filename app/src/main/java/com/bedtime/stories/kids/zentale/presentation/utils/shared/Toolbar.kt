package com.bedtime.stories.kids.zentale.presentation.utils.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.dimensionResource
import com.bedtime.stories.kids.zentale.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onStartIconClick: (() -> Unit)? = null,
    onEndIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.double_content_padding)),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            startIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(onClick = { onStartIconClick?.invoke() })
                        .padding(end =dimensionResource(id = R.dimen.content_padding))
                )
            }
        },
        actions = {
            endIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(onClick = { onEndIconClick?.invoke() })
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}


