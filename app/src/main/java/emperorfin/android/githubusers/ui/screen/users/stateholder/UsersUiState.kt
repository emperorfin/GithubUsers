package emperorfin.android.githubusers.ui.screen.users.stateholder

import emperorfin.android.githubusers.ui.model.user.UserUiModel


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 23rd October, 2024.
 */




data class UsersUiState(
    val users: List<UserUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val messageSnackBar: Int? = null,
)
