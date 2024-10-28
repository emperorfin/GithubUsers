package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.api

import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UserDetailsResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UsersSearchResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo.Repo
import emperorfin.android.githubusers.domain.datalayer.dao.IUsersDao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




interface UsersApi : IUsersDao {

    @GET("/search/users?&")
    override suspend fun getRemoteUsers(@Query("q") query: String): Response<UsersSearchResponse>

    @GET("/user/{id}")
    override suspend fun getRemoteUser(@Path("id") userId: Long): Response<UserDetailsResponse>

    @GET("/user/{id}/repos")
    override suspend fun getRemoteUserRepos(@Path("id") userId: Long): Response<List<Repo>>

    override suspend fun countAllUsers(): Int = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun getAllUsers(): List<UserEntity> = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun getUsers(query: String): List<UserEntity> = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun getUser(userId: Long): UserEntity = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun insertUser(user: UserEntity): Long = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun deleteUser(userId: Long): Int = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun countAllRepos(): Int = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun getAllRepos(): List<RepoEntity> = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun getUserRepos(userId: Long): List<RepoEntity> = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun insertRepo(repo: RepoEntity): Long = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

    override suspend fun deleteRepo(userId: Long): Int = throw IllegalStateException(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)

}