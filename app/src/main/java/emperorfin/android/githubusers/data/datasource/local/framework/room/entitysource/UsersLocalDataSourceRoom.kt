package emperorfin.android.githubusers.data.datasource.local.framework.room.entitysource

import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED
import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntityMapper
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntityMapper
import emperorfin.android.githubusers.di.IoDispatcher
import emperorfin.android.githubusers.domain.model.user.UserModelMapper
import emperorfin.android.githubusers.di.LocalUsersDao
import emperorfin.android.githubusers.domain.datalayer.dao.IUsersDao
import emperorfin.android.githubusers.domain.datalayer.datasource.UsersDataSource
import emperorfin.android.githubusers.domain.exception.RepoFailure
import emperorfin.android.githubusers.domain.exception.UserFailure
import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.repo.RepoModelMapper
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.RepoParams
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.None as None_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User
import emperorfin.android.githubusers.domain.uilayer.event.input.user.None as None_User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




data class UsersLocalDataSourceRoom @Inject internal constructor(
    @LocalUsersDao private val usersDao: IUsersDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val userEntityMapper: UserEntityMapper,
    private val userModelMapper: UserModelMapper,
    private val repoEntityMapper: RepoEntityMapper,
    private val repoModelMapper: RepoModelMapper
) : UsersDataSource {

    private companion object {
        const val NUM_OF_USERS_0: Int = 0
        const val NUM_OF_USER_ROWS_DELETED_1: Int = 1
        const val NUM_OF_REPOS_0: Int = 0
        const val NUM_OF_REPO_ROWS_DELETED_1: Int = 1

        const val USER_TABLE_ROW_ID_1: Long = 1L
        const val REPO_TABLE_ROW_ID_1: Long = 1L
    }

    override suspend fun countAllUsers(params: Params_User): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None_User -> {
                return@withContext try {

                    val numOfUsers: Int = usersDao.countAllUsers()

                    if (numOfUsers > NUM_OF_USERS_0) {
                        return@withContext ResultData.Success(data = numOfUsers)
                    } else if (numOfUsers == NUM_OF_USERS_0) {
                        return@withContext ResultData.Error(failure = UserFailure.NonExistentUserDataLocalError())
                    }

                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError())

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            is UserParams -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }


    }

    override suspend fun getUsers(params: Params_User): ResultData<List<UserModel>> = withContext(ioDispatcher) {

        when(params){
            is None_User -> {
                return@withContext try {
                    val entityUsers: List<UserEntity> = usersDao.getAllUsers()

                    if (entityUsers == null) // TODO: Remove deliberate check.
                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())
                    else if (entityUsers.isEmpty())
                        return@withContext ResultData.Error(failure = UserFailure.UserListNotAvailableLocalError())

                    val modelUsers = entityUsers.map {
                        userModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelUsers)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            is UserParams -> {
                return@withContext try {

                    var searchQuery = ""

                    if (params.login != null) {
                        searchQuery = params.login
                    } else if (params.name != null) {
                        searchQuery = params.name
                    } else if (params.bio != null) {
                        searchQuery = params.bio
                    }

//                    val entityUsers: List<UserEntity> = usersDao.getUsers(params.login!!) as List<UserEntity>
                    var entityUsers: List<UserEntity>? = null

                    if (searchQuery.isNotEmpty()) {
                        entityUsers = usersDao.getUsers(searchQuery)
                    } else {
                        entityUsers = usersDao.getAllUsers()
                    }

                    if (entityUsers == null) // TODO: Remove deliberate check.
                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())
                    else if (entityUsers.isEmpty())
                        return@withContext ResultData.Error(failure = UserFailure.UserListNotAvailableLocalError())

                    val modelUsers = entityUsers.map {
                        userModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelUsers)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun getUser(params: Params_User): ResultData<UserModel> = withContext(ioDispatcher) {

        when(params){
            is None_User -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            is UserParams -> {
                return@withContext try {

                    val entityUser: UserEntity = usersDao.getUser(params.id!!)

//                    if (entityUser == null)
//                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())

                    val modelUser = userModelMapper.transform(entityUser)

                    return@withContext ResultData.Success(modelUser)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveUser(user: UserModel): ResultData<Long> = withContext(ioDispatcher) {

        val entityUser = userEntityMapper.transform(user)

        // TODO: Put this in a try/catch block and then write test cases for it too.
        val tableRowId: Long = usersDao.insertUser(entityUser)

        if (tableRowId < USER_TABLE_ROW_ID_1)
            return@withContext ResultData.Error(
                UserFailure.InsertUserLocalError(message = R.string.error_local_insert_user)
            )

        return@withContext ResultData.Success(tableRowId)
    }

    override suspend fun deleteUser(params: Params_User): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None_User -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }

            is UserParams -> {
                return@withContext try {

                    val numOfUserDeleted: Int = usersDao.deleteUser(params.id!!)

                    if (numOfUserDeleted < NUM_OF_USER_ROWS_DELETED_1) {
                        return@withContext ResultData.Error(failure = UserFailure.DeleteUserLocalError(R.string.error_local_delete_user))
                    }

                    return@withContext ResultData.Success(numOfUserDeleted)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.DeleteUserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun countAllRepos(params: Params_Repo): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None_Repo -> {
                return@withContext try {

                    val numOfRepos: Int = usersDao.countAllRepos()

                    if (numOfRepos > NUM_OF_REPOS_0) {
                        return@withContext ResultData.Success(data = numOfRepos)
                    } else if (numOfRepos == NUM_OF_REPOS_0) {
                        return@withContext ResultData.Error(failure = RepoFailure.NonExistentRepoDataLocalError())
                    }

                    return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError())

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError(cause = e))
                }
            }
            is RepoParams -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }


    }

    override suspend fun getRepos(params: Params_Repo): ResultData<List<RepoModel>> = withContext(ioDispatcher) {

        when(params){
            is None_Repo -> {
                return@withContext try {
                    val entityRepos: List<RepoEntity> = usersDao.getAllRepos()

                    if (entityRepos == null) // TODO: Remove deliberate check.
                        return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError())
                    else if (entityRepos.isEmpty())
                        return@withContext ResultData.Error(failure = RepoFailure.RepoListNotAvailableLocalError())

                    val modelRepos = entityRepos.map {
                        repoModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelRepos)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError(cause = e))
                }
            }
            is RepoParams -> {
                return@withContext try {

                    val entityRepos: List<RepoEntity> = usersDao.getUserRepos(params.owner?.id!!)

                    if (entityRepos == null) // TODO: Remove deliberate check.
                        return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError())
                    else if (entityRepos.isEmpty())
                        return@withContext ResultData.Error(failure = RepoFailure.RepoListNotAvailableLocalError())

                    val modelRepos = entityRepos.map {
                        repoModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelRepos)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = RepoFailure.RepoLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveRepo(repo: RepoModel): ResultData<Long> = withContext(ioDispatcher) {

        val entityRepo = repoEntityMapper.transform(repo)

        // TODO: Put this in a try/catch block and then write test cases for it too.
        val tableRowId: Long = usersDao.insertRepo(entityRepo)

        if (tableRowId < REPO_TABLE_ROW_ID_1)
            return@withContext ResultData.Error(
                RepoFailure.InsertRepoLocalError(message = R.string.error_local_insert_repo)
            )

        return@withContext ResultData.Success(tableRowId)
    }

    override suspend fun deleteRepo(params: Params_Repo): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None_Repo -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }

            is RepoParams -> {
                return@withContext try {

                    val numOfRepoDeleted: Int = usersDao.deleteRepo(params.id!!)

                    if (numOfRepoDeleted < NUM_OF_REPO_ROWS_DELETED_1) {
                        return@withContext ResultData.Error(failure = RepoFailure.DeleteRepoLocalError(R.string.error_local_delete_repo))
                    }

                    return@withContext ResultData.Success(numOfRepoDeleted)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = RepoFailure.DeleteRepoLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }
}
