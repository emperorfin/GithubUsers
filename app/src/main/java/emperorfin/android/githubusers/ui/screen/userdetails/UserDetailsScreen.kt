package emperorfin.android.githubusers.ui.screen.userdetails

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.palette.BitmapPalette
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.ui.component.AppBarWithArrow
import emperorfin.android.githubusers.ui.component.ContentLoader
import emperorfin.android.githubusers.ui.component.EmptyContent
import emperorfin.android.githubusers.ui.component.LoadingIndicator
import emperorfin.android.githubusers.ui.component.NetworkImage
import emperorfin.android.githubusers.ui.model.user.UserUiModel
import emperorfin.android.githubusers.ui.navigation.NavigationActions
import emperorfin.android.githubusers.ui.screen.userdetails.stateholder.UserDetailsUiState
import emperorfin.android.githubusers.ui.screen.userdetails.stateholder.UserDetailsViewModel
import emperorfin.android.githubusers.ui.theme.Purple40
import emperorfin.android.githubusers.ui.theme.background


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@Composable
fun UserDetailsScreen(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions?,
    userId: Long,
    viewModel: UserDetailsViewModel = hiltViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsState()

    val user = uiState.user

    LaunchedEffect(key1 = userId) {
        viewModel.loadUser(
            params = UserParams(id = userId, login = ""),
            isRefresh = false
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {

            val userName: String = (user?.name ?: user?.login).toString()

            AppBarWithArrow(
                title = userName,
                onBackPress = {
                    navigationActions?.navigateBack()
                }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        Content(
            modifier = Modifier.padding(paddingValues),
            userId = userId,
            user = user,
            viewModel = viewModel,
            uiState = uiState
        )

        // Check for SnackBar messages to display on the screen
        uiState.messageSnackBar?.let { message ->
            val snackBarText = stringResource(message)
            LaunchedEffect(snackBarHostState, viewModel, message, snackBarText) {
                snackBarHostState.showSnackbar(message = snackBarText)
                viewModel.snackBarMessageShown()
            }
        }

    }
}

@Composable
private fun Content(
    modifier: Modifier,
    userId: Long,
    user: UserUiModel?,
    viewModel: UserDetailsViewModel,
    uiState: UserDetailsUiState
) {

    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage

    ContentLoader(
        loading = isLoading,
        empty = user == null && !isLoading,
        emptyContent = {
            EmptyContent(
                errorLabel = errorMessage ?: R.string.content_description_error_message,
                onRetry = {
                    viewModel.loadUser(
                        params = UserParams(id = userId, login = ""),
                        isRefresh = false
                    )
                }
            )
        },
        loadingIndicator = {
            LoadingIndicator(modifier = modifier)
        }
    ) {


        user?.let {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(background)
                    .fillMaxSize(),
            ) {

                Spacer(modifier = Modifier.height(58.dp))

                Header(it)

                UserRepos(it)

                Spacer(modifier = Modifier.height(24.dp))
            }

        }

    }
}

@Composable
private fun Header(
    user: UserUiModel
) {

    Column {

        var palette by remember { mutableStateOf<Palette?>(null) }
        NetworkImage(
            networkUrl = user.avatarUrl,
            circularReveal = CircularReveal(duration = 300),
            shimmerParams = null,
            bitmapPalette = BitmapPalette {
                palette = it
            },
            modifier = Modifier
                .height(380.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        user.name.let {
            if (it.isNotEmpty()) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        Text(
            text = user.handle,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Count("Followers: ${user.followers}")

            Count("Following: ${user.following}")
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
private fun Count(text: String) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 8.dp,
        color = Purple40,
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun UserRepos(
    user: UserUiModel
) {

    Column {

        Spacer(modifier = Modifier.height(23.dp))

        Text(
            text = stringResource(R.string.heading_repos),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

    }
}