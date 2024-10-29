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
import emperorfin.android.githubusers.domain.exception.RepoFailure
import emperorfin.android.githubusers.domain.exception.UserFailure
import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.repo.RepoModelMapper
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.model.user.UserModelMapper
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.RepoParams
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded.Owner
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.None as None_Repo
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.succeeded
import emperorfin.android.githubusers.ui.model.repo.RepoUiModel
import emperorfin.android.githubusers.ui.model.repo.RepoUiModelMapper
import emperorfin.android.githubusers.ui.model.user.UserUiModel
import emperorfin.android.githubusers.ui.model.user.UserUiModelMapper
import emperorfin.android.githubusers.ui.util.InternetConnectivityUtil.hasInternetConnection
import emperorfin.android.githubusers.ui.util.WhileUiSubscribed
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User
import emperorfin.android.githubusers.domain.uilayer.event.input.user.None as None_User
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
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
    private val repoModelMapper: RepoModelMapper,
    private val repoUiModelMapper: RepoUiModelMapper,
    @IoDispatcher private val coroutineDispatcherIo: CoroutineDispatcher,
    @DefaultDispatcher private val coroutineDispatcherDefault: CoroutineDispatcher,
) : ViewModel() {

    companion object {
        private const val NUM_OF_USERS_MINUS_1: Int = -1
        private const val NUM_OF_USERS_0: Int = 0

        private const val NUM_OF_REPOS_MINUS_1: Int = -1
        private const val NUM_OF_REPOS_0: Int = 0
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    val errorMessage: StateFlow<Int?> = _errorMessage

    private val _messageSnackBar: MutableStateFlow<Int?> = MutableStateFlow(null)
    val messageSnackBar: StateFlow<Int?> = _messageSnackBar

    private val _user: MutableStateFlow<ResultData<UserUiModel>> =  MutableStateFlow(ResultData.Loading)
    val user: StateFlow<ResultData<UserUiModel>> = _user

    private val _repos: MutableStateFlow<ResultData<List<RepoUiModel>>> =  MutableStateFlow(ResultData.Loading)
    val repos: StateFlow<ResultData<List<RepoUiModel>>> = _repos

    private var mUserUiModelResultData: ResultData<UserUiModel> = ResultData.Loading

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
                val _repos = (repos.value as ResultData.Success).data

                UserDetailsUiState(
                    user = _user,
                    repos = _repos,
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

//    private fun getUserViaRepository(
//        params: Params_User,
//        isRefresh: Boolean = false,
//    ) = viewModelScope.launch(context = coroutineDispatcherIo) {
//
//        _user.value = ResultData.Loading
//
//        val userResultData: ResultData<UserModel> =
//            usersRepository.getUser(params = params, forceUpdate = isRefresh)
//
//        if (userResultData.succeeded) {
//            val userEntity = (userResultData as ResultData.Success).data
//
//            val userModel: UserModel = userModelMapper.transform(userEntity)
//
//            val userUiModel: UserUiModel = userUiModelMapper.transform(userModel)
//
//            _user.value = ResultData.Success(data = userUiModel)
//
//        } else {
//            val error: ResultData.Error = (userResultData as ResultData.Error)
//            _user.value = error
//        }
//
//    }

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

//            _user.value = ResultData.Success(data = userUiModel)
            mUserUiModelResultData = ResultData.Success(data = userUiModel)

            val owner = Owner(
                id = userUiModel.id,
                login = ""
            )

            loadUserRepos(
                params = RepoParams(
                    id = -1L,
                    owner = owner
                ),
                isRefresh = false
            )

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

    private fun getUserReposViaRepository(
        params: Params_Repo,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        _repos.value = ResultData.Loading

        val reposResultData: ResultData<List<RepoModel>> =
            usersRepository.getRepos(params = params, forceUpdate = isRefresh)

        if (reposResultData.succeeded) {

            val reposEntity = (reposResultData as ResultData.Success).data

            val reposUiModel: List<RepoUiModel> = reposEntity.map {
                repoModelMapper.transform(it)
            }.map {
                repoUiModelMapper.transform(it)
            }

            _repos.value = ResultData.Success(data = reposUiModel)

            _user.value = mUserUiModelResultData

        } else {

            val error: ResultData.Error = (reposResultData as ResultData.Error)
            _repos.value = error
            _user.value = ResultData.Error(failure = UserFailure.GetUserRepositoryError(
                message = (error.failure as RepoFailure).message,
                cause = (error.failure as RepoFailure).cause,
            ))

        }

    }

    private fun searchUserRepos(params: RepoParams, isRefresh: Boolean = true) {
        getUserReposViaRepository(params = params, isRefresh = isRefresh)
    }

//    private fun getAllRepos(params: None = None(), isRefresh: Boolean = false) {
//        getUserReposViaRepository(params = params, isRefresh = isRefresh)
//    }

    fun loadUserRepos(params: Params_Repo, isRefresh: Boolean){
        viewModelScope.launch/*(context = coroutineDispatcherIo)*/ {

            var reposCount by Delegates.notNull<Int>()

            val reposCountDataResultEvent = usersRepository.countAllRepos(params = None_Repo())

            reposCount = if (reposCountDataResultEvent.succeeded)
                (reposCountDataResultEvent as ResultData.Success).data
            else
                NUM_OF_REPOS_MINUS_1

            if (reposCount > NUM_OF_REPOS_0 || isRefresh) {

                if (hasInternetConnection(application)){

                    searchUserRepos(
                        params = params as RepoParams,
                        isRefresh = true,
                    )
                } else {

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            application,
                            R.string.message_no_internet_loading_cached_user_repos,
                            Toast.LENGTH_LONG
                        ).show()
                    }

//                    getAllRepos(
//                        params = None(),
//                        isRefresh = false
//                    )
                    searchUserRepos(
                        params = params as RepoParams,
                        isRefresh = false,
                    )
                }
            } else {

                if (hasInternetConnection(application)){
                    searchUserRepos(
                        params = params as RepoParams,
                        isRefresh = true
                    )
                } else {

                    _messageSnackBar.value = R.string.message_no_internet_connectivity

                    _repos.value = ResultData.Error(
                        failure = RepoFailure.RepoRemoteError(
                            message = R.string.message_no_internet_connectivity
                        )
                    )
                }
            }

        }
    }

}
