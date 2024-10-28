package emperorfin.android.githubusers.ui.screen.userdetails.stateholder

import emperorfin.android.githubusers.ui.model.user.UserUiModel


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




data class UserDetailsUiState(
    val user: UserUiModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val messageSnackBar: Int? = null,
)
