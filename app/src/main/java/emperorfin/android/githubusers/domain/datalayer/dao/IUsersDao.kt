package emperorfin.android.githubusers.domain.datalayer.dao

import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UserDetailsResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UsersSearchResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo.Repo
import retrofit2.Response


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




// Users instead of User in IUsersDao is based on the /users resource naming from GitHub.
interface IUsersDao {

    suspend fun countAllUsers(): Int

    suspend fun getAllUsers(): List<UserEntity>

    suspend fun getUsers(query: String): List<UserEntity>

    suspend fun getRemoteUsers(query: String): Response<UsersSearchResponse>

    suspend fun getUser(userId: Long): UserEntity

    suspend fun getRemoteUser(userId: Long): Response<UserDetailsResponse>

    suspend fun insertUser(user: UserEntity): Long

    suspend fun deleteUser(userId: Long): Int

    suspend fun countAllRepos(): Int

    suspend fun getAllRepos(): List<RepoEntity>

    suspend fun getUserRepos(userId: Long): List<RepoEntity>

    suspend fun getRemoteUserRepos(userId: Long): Response<List<Repo>>

    suspend fun insertRepo(repo: RepoEntity): Long

    suspend fun deleteRepo(userId: Long): Int
}