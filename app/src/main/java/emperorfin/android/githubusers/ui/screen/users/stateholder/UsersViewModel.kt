package emperorfin.android.githubusers.ui.screen.users.stateholder

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
import emperorfin.android.githubusers.domain.uilayer.event.input.user.None
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.succeeded
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params
import emperorfin.android.githubusers.ui.model.user.UserUiModel
import emperorfin.android.githubusers.ui.model.user.UserUiModelMapper
import emperorfin.android.githubusers.ui.util.InternetConnectivityUtil.hasInternetConnection
import emperorfin.android.githubusers.ui.util.WhileUiSubscribed
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
import kotlin.random.Random


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 23rd October, 2024.
 */




@HiltViewModel
data class UsersViewModel @Inject constructor(
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

    private val _users: MutableStateFlow<ResultData<List<UserUiModel>>> =  MutableStateFlow(ResultData.Loading)
    val users: StateFlow<ResultData<List<UserUiModel>>> = _users

    init {

        val letters = "abcdefghijklmnopqrstuvwxyz"

        val randomIndex = Random.nextInt(letters.length)

        val query = letters[randomIndex]

        loadUsers(
            params = UserParams(login = query.toString(), id = -1L),
            isRefresh = false
        )
    }

    val uiState: StateFlow<UsersUiState> = combine(
        isLoading, errorMessage, users, messageSnackBar
    ) { isLoading, errorMessage, users, messageSnackBar ->

        when (users) {

            ResultData.Loading -> {
                UsersUiState(isLoading = true)
            }
            is ResultData.Error -> {
                UsersUiState(
//                    errorMessage = (users.failure as UserFailure.GetUserRepositoryError).message,
//                    errorMessage = (users.failure as UserFailure.GetUserRemoteError).message,
                    errorMessage = (users.failure as UserFailure).message,
                    messageSnackBar = messageSnackBar
                )
            }
            is ResultData.Success<List<UserUiModel>> -> {

                val _users: List<UserUiModel> = users.data

                val usersSorted = _users.sortedBy {
                    it.login
                }

                UsersUiState(
                    users = usersSorted,
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
            initialValue = UsersUiState(isLoading = true)
        )

    fun snackBarMessageShown() {
        _messageSnackBar.value = null
    }

    private fun getUsersViaRepository(
        params: Params,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        _users.value = ResultData.Loading

        val usersResultData: ResultData<List<UserModel>> =
            usersRepository.getUsers(params = params, forceUpdate = isRefresh)

        if (usersResultData.succeeded) {
            val usersEntity = (usersResultData as ResultData.Success).data

            val usersUiModel: List<UserUiModel> = usersEntity.map {
                userModelMapper.transform(it)
            }.map {
                userUiModelMapper.transform(it)
            }

            _users.value = ResultData.Success(data = usersUiModel)

        } else {
            val error: ResultData.Error = (usersResultData as ResultData.Error)
            _users.value = error

        }

    }

    private fun searchUsers(params: UserParams, isRefresh: Boolean = true) {
        getUsersViaRepository(params = params, isRefresh = isRefresh)
    }

    private fun getAllUsers(params: None = None(), isRefresh: Boolean = false) {
        getUsersViaRepository(params = params, isRefresh = isRefresh)
    }

    /**
     * To get all users, pass None() as params.
     * To search for users, pass UserParams() as params.
     */
    fun loadUsers(params: Params, isRefresh: Boolean){
        viewModelScope.launch/*(context = coroutineDispatcherIo)*/ {

            var usersCount by Delegates.notNull<Int>()

            val usersCountDataResultEvent = usersRepository.countAllUsers(params = None())

            usersCount = if (usersCountDataResultEvent.succeeded)
                (usersCountDataResultEvent as ResultData.Success).data
            else
                NUM_OF_USERS_MINUS_1

            if (usersCount > NUM_OF_USERS_0 || isRefresh){

                if (hasInternetConnection(application)){

                    searchUsers(
                        params = params as UserParams,
                        isRefresh = true,
                    )
                } else {

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            application,
                            R.string.message_no_internet_loading_cached_users,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    getAllUsers(
                        params = None(),
                        isRefresh = false
                    )
                }
            } else {

                if (hasInternetConnection(application)){
                    searchUsers(
                        params = params as UserParams,
                        isRefresh = true
                    )
                } else {

                    _messageSnackBar.value = R.string.message_no_internet_connectivity

                    _users.value = ResultData.Error(
                        failure = UserFailure.UserRemoteError(
                            message = R.string.message_no_internet_connectivity
                        )
                    )
                }
            }

        }
    }

}