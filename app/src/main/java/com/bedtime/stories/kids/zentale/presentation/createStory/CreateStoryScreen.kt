package com.bedtime.stories.kids.zentale.presentation.createStory

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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bedtime.stories.kids.zentale.R
import com.bedtime.stories.kids.zentale.presentation.utils.shared.Toolbar
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.bedtime.stories.kids.zentale.presentation.utils.extensions.rotateBitmap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel
import java.util.concurrent.Executor

@Composable
fun CreateStoryScreen(
    navController: NavHostController,
    viewModel: CreateStoryViewModel = koinViewModel(),
    hasCameraPermission: Boolean,
    onCameraRequiredPermission: (() -> Unit)? = null
) {

    val cameraState: CreateStoryState by viewModel.state.collectAsStateWithLifecycle()

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
                    PermissionsRequired(
                        onPhotoCaptured = viewModel::storePhotoInGallery,
                        onLastPhotoCaptured = cameraState.capturedImage,
                        navController = navController,
                        hasCameraPermission = hasCameraPermission,
                        onCameraRequiredPermission = onCameraRequiredPermission
                    )
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
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.content_padding))
                    )

                    Icon(
                        imageVector = Icons.Filled.Bolt,
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsRequired(
    navController: NavHostController,
    onCameraRequiredPermission: (() -> Unit)? = null,
    hasCameraPermission: Boolean,
    onPhotoCaptured: (Bitmap) -> Unit,
    onLastPhotoCaptured: Bitmap?
) {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
//                val bitmap = BitmapFactory.decodeFile(imageUri.path)
//                val byteArray = ByteArrayOutputStream().use { outputStream ->
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//                    outputStream.toByteArray()
//                }
            }
        }
    )

    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                galleryLauncher.launch("image/*")
            } else {
                // Permission denied
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (hasCameraPermission) {
                    navController.navigate("takePicture")
                } else {
                    onCameraRequiredPermission?.invoke()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.create_take_a_picture))
        }

        Button(
            onClick = {
                val permission =
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        Manifest.permission.READ_MEDIA_IMAGES
                    } else {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    }
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    galleryLauncher.launch("image/*")
                } else {
                    galleryPermissionLauncher.launch(permission)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.create_add_from_gallery))
        }
        println("imageUri $imageUri")
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = imageUri),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
            )
        }
    }
}
