package com.bedtime.stories.kids.zentale.presentation.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.LottieAnimation
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state = rememberOneTapSignInState()
    OneTapSignInWithGoogle(
        state = state,
        clientId = stringResource(id = R.string.google_sign_in_client_id),
        onTokenIdReceived = { tokenId ->
            viewModel.onSuccessfulLogin(tokenId)
        },
        onDialogDismissed = { message ->
//            viewModel.onErrorLogin()
        }
    )
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append(stringResource(id = R.string.login_agree_with_terms_start))
        }
        pushStringAnnotation(
            tag = "URL",
            annotation = stringResource(id = R.string.terms_of_service_url)
        )
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(stringResource(id = R.string.login_terms_of_service))
        }
        pop()

        withStyle(style = SpanStyle(color = Color.White)) {
            append(stringResource(id = R.string.login_agree_with_terms_and))
        }
        pushStringAnnotation(
            tag = "URL",
            annotation = stringResource(id = R.string.privacy_policy_url)
        )
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(stringResource(id = R.string.login_privacy_policy))
        }
        pop()
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.double_content_padding)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.large_content_padding)
                    )
                )
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(id = R.dimen.large_content_padding)
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(id = R.dimen.content_padding)
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.login_sub_title_text),
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(id = R.dimen.content_padding)
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.login_description_text),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                LottieAnimation(
                    animationResId = R.raw.tiger,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(
                    modifier = Modifier.height(
                        dimensionResource(id = R.dimen.large_content_padding)
                    )
                )
                Column {
                    Button(
                        onClick = {
                            state.open()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = ""
                        )
                        Text(
                            text = stringResource(id = R.string.login_sign_in_with_google),
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(id = R.dimen.half_content_padding)
                            ),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(
                        dimensionResource(id = R.dimen.double_content_padding)
                    ))
                    ClickableText(
                        text = annotatedString,
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(
                                tag = "URL",
                                start = offset,
                                end = offset
                            )
                                .firstOrNull()?.let { annotation ->
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(annotation.item)
                                        )
                                    )
                                }
                        },
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(
                        dimensionResource(id = R.dimen.double_content_padding)
                    ))
                }
            }
        }
    }
}
