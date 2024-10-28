package emperorfin.android.githubusers.ui.screen.users

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.ui.component.AppBar
import emperorfin.android.githubusers.ui.component.ContentLoader
import emperorfin.android.githubusers.ui.component.EmptyContent
import emperorfin.android.githubusers.ui.component.LoadingIndicator
import emperorfin.android.githubusers.ui.component.SearchField
import emperorfin.android.githubusers.ui.component.UserListItem
import emperorfin.android.githubusers.ui.navigation.NavigationActions
import emperorfin.android.githubusers.ui.screen.users.stateholder.UsersUiState
import emperorfin.android.githubusers.ui.screen.users.stateholder.UsersViewModel
import emperorfin.android.githubusers.ui.util.InternetConnectivityUtil.hasInternetConnection


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 23rd October, 2024.
 */




@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navigationActions: NavigationActions?,
    viewModel: UsersViewModel = hiltViewModel(),
) {

    val snackBarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = { AppBar() },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        Content(
            context = context,
            modifier = Modifier.padding(paddingValues),
            navigationActions = navigationActions,
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
    navigationActions: NavigationActions?,
    context: Context,
    viewModel: UsersViewModel,
    uiState: UsersUiState
) {

    val users = uiState.users
    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage

    var searchInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
//            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        SearchField(
            modifier = Modifier
                .fillMaxWidth(),
            searchInput = searchInput,
            onSearchInputChanged = {
                searchInput = it

//                viewModel.loadUsers(
//                    params = UserParams(title = searchInput),
//                    isRefresh = false
//                )
            },
            onSearchClicked = {

                if (!hasInternetConnection(context.applicationContext as Application)){
                    Toast.makeText(context, R.string.message_no_internet_connectivity, Toast.LENGTH_SHORT).show()

                    return@SearchField
                }

                if (searchInput.isEmpty()) {
                    Toast.makeText(context, R.string.message_enter_user_handle_name_or_text_found_in_bio, Toast.LENGTH_SHORT).show()

                    return@SearchField
                }

                viewModel.loadUsers(
                    params = UserParams(login = searchInput, id = -1L),
                    isRefresh = false
                )
            }
        )

        ContentLoader(
            loading = isLoading,
            empty = users.isEmpty() && !isLoading,
            emptyContent = {
                EmptyContent(
                    errorLabel = errorMessage ?: R.string.content_description_error_message,
                    onRetry = {
                        if (searchInput.isEmpty()) {
                            Toast.makeText(context, R.string.message_enter_user_handle_name_or_text_found_in_bio, Toast.LENGTH_SHORT).show()

                            return@EmptyContent
                        }

                        viewModel.loadUsers(
                            params = UserParams(login = searchInput, id = -1L),
                            isRefresh = false
                        )
                    }
                )
            },
            loadingIndicator = {
                LoadingIndicator(modifier = modifier)
            }
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
            ) {

                itemsIndexed(users) { _, user ->

                    UserListItem(
                        user = user,
                        onClick = {

//                            if (!hasInternetConnection(context.applicationContext as Application)){
//                                Toast.makeText(context, R.string.message_no_internet_connectivity, Toast.LENGTH_SHORT).show()
//
//                                return@UserListItem
//                            }

                            navigationActions?.navigateToUserDetailsScreen(it)

                            Toast.makeText(
                                context,
                                it.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }

            }

        }

    }

}