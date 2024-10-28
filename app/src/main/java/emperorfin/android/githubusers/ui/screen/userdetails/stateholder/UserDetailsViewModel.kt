package emperorfin.android.githubusers.ui.screen.userdetails.stateholder

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.di.DefaultDispatcher
import emperorfin.android.githubusers.di.IoDispatcher
import emperorfin.android.githubusers.domain.datalayer.repository.IUsersRepository
import emperorfin.android.githubusers.domain.exception.UserFailure
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.model.user.UserModelMapper
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.succeeded
import emperorfin.android.githubusers.ui.model.user.UserUiModel
import emperorfin.android.githubusers.ui.model.user.UserUiModelMapper
import emperorfin.android.githubusers.ui.util.InternetConnectivityUtil.hasInternetConnection
import emperorfin.android.githubusers.ui.util.WhileUiSubscribed
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.None as None_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User
import emperorfin.android.githubusers.domain.uilayer.event.input.user.None as None_User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.Delegates


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




@HiltViewModel
data class UserDetailsViewModel @Inject constructor(
    val application: Application,
    private val usersRepository: IUsersRepository,
    private val userModelMapper: UserModelMapper,
    private val userUiModelMapper: UserUiModelMapper,
    @IoDispatcher private val coroutineDispatcherIo: CoroutineDispatcher,
    @DefaultDispatcher private val coroutineDispatcherDefault: CoroutineDispatcher,
) : ViewModel() {

    companion object {
        private const val NUM_OF_USERS_MINUS_1: Int = -1
        private const val NUM_OF_USERS_0: Int = 0
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    val errorMessage: StateFlow<Int?> = _errorMessage

    private val _messageSnackBar: MutableStateFlow<Int?> = MutableStateFlow(null)
    val messageSnackBar: StateFlow<Int?> = _messageSnackBar

    private val _user: MutableStateFlow<ResultData<UserUiModel>> =  MutableStateFlow(ResultData.Loading)
    val user: StateFlow<ResultData<UserUiModel>> = _user

    val uiState: StateFlow<UserDetailsUiState> = combine(
        isLoading, errorMessage, user, messageSnackBar
    ) { isLoading, errorMessage, user, messageSnackBar ->

        when (user) {

            ResultData.Loading -> {
                UserDetailsUiState(isLoading = true)
            }
            is ResultData.Error -> {
                UserDetailsUiState(
                    errorMessage = (user.failure as UserFailure).message,
                    messageSnackBar = messageSnackBar
                )
            }
            is ResultData.Success<UserUiModel> -> {

                val _user: UserUiModel = user.data

                UserDetailsUiState(
                    user = _user,
                    isLoading = isLoading,
//                    errorMessage = errorMessage,
                    errorMessage = null,
                    messageSnackBar = messageSnackBar
                )
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = UserDetailsUiState(isLoading = true)
        )

    fun snackBarMessageShown() {
        _messageSnackBar.value = null
    }

    private fun getUserViaRepository(
        params: Params_User,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        _user.value = ResultData.Loading

        val userResultData: ResultData<UserModel> =
            usersRepository.getUser(params = params, forceUpdate = isRefresh)

        if (userResultData.succeeded) {
            val userEntity = (userResultData as ResultData.Success).data

            val userModel: UserModel = userModelMapper.transform(userEntity)

            val userUiModel: UserUiModel = userUiModelMapper.transform(userModel)

            _user.value = ResultData.Success(data = userUiModel)

        } else {
            val error: ResultData.Error = (userResultData as ResultData.Error)
            _user.value = error
        }

    }

    fun loadUser(params: UserParams, isRefresh: Boolean){
        viewModelScope.launch/*(context = coroutineDispatcherIo)*/ {

            var usersCount by Delegates.notNull<Int>()

            val usersCountDataResultEvent = usersRepository.countAllUsers(params = None_User())

            usersCount = if (usersCountDataResultEvent.succeeded)
                (usersCountDataResultEvent as ResultData.Success).data
            else
                NUM_OF_USERS_MINUS_1

            if (usersCount > NUM_OF_USERS_0 || isRefresh){

                if (hasInternetConnection(application)){

                    getUserViaRepository(
                        params = params,
                        isRefresh = true,
                    )
                } else {

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            application,
                            R.string.message_no_internet_searching_cached_users,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    getUserViaRepository(
                        params = params,
                        isRefresh = false
                    )
                }
            } else {

                if (hasInternetConnection(application)){
                    getUserViaRepository(
                        params = params,
                        isRefresh = true
                    )
                } else {

                    _messageSnackBar.value = R.string.message_no_internet_connectivity

                    _user.value = ResultData.Error(
                        failure = UserFailure.UserRemoteError(
                            message = R.string.message_no_internet_connectivity
                        )
                    )
                }
            }

        }
    }

}
