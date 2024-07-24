package com.bedtime.stories.kids.zentale.presentation.utils.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.bedtime.stories.kids.zentale.R

@Composable
fun Toolbar(
    title: String,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onStartIconClick: (() -> Unit)? = null,
    onEndIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = dimensionResource(id = R.dimen.half_content_padding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (startIcon != null) {
                IconButton(
                    onClick = { onStartIconClick?.invoke() }) {
                    Icon(
                        imageVector = startIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.double_content_padding))
            )
            if (endIcon != null) {
                IconButton(onClick = { onEndIconClick?.invoke() }) {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}
