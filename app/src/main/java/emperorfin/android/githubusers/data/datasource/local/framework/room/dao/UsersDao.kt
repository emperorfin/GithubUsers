package emperorfin.android.githubusers.data.datasource.local.framework.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.COLUMN_INFO_FULL_NAME
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.COLUMN_INFO_ID as COLUMN_INFO_ID_REPO
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.COLUMN_INFO_OWNER_ID
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.TABLE_NAME as TABLE_NAME_REPO
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.COLUMN_INFO_NAME as COLUMN_INFO_NAME_REPO
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_BIO
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.TABLE_NAME as TABLE_NAME_USER
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ID as COLUMN_INFO_ID_USER
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_LOGIN
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_NAME as COLUMN_INFO_NAME_USER
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UserDetailsResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UsersSearchResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo.Repo
import emperorfin.android.githubusers.domain.datalayer.dao.IUsersDao
import retrofit2.Response


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




@Dao
interface UsersDao : IUsersDao {

    @Query("SELECT COUNT(*) FROM $TABLE_NAME_USER")
    override suspend fun countAllUsers(): Int

    @Query("SELECT * FROM $TABLE_NAME_USER ORDER BY $COLUMN_INFO_LOGIN ASC")
    override suspend fun getAllUsers(): List<UserEntity>

//    @Query("SELECT * FROM $TABLE_NAME_USER WHERE $COLUMN_INFO_LOGIN LIKE '%' || :query || '%' ORDER BY $COLUMN_INFO_LOGIN ASC")
    @Query("SELECT * FROM $TABLE_NAME_USER WHERE ($COLUMN_INFO_LOGIN LIKE '%' || :query || '%') || ($COLUMN_INFO_NAME_USER LIKE '%' || :query || '%') || ($COLUMN_INFO_BIO LIKE '%' || :query || '%') ORDER BY $COLUMN_INFO_LOGIN ASC")
    override suspend fun getUsers(query: String): List<UserEntity>

    @Query("SELECT * FROM $TABLE_NAME_USER WHERE $COLUMN_INFO_ID_USER = :userId")
    override suspend fun getUser(userId: Long): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertUser(user: UserEntity): Long

    @Query("DELETE FROM $TABLE_NAME_USER WHERE $COLUMN_INFO_ID_USER = :userId")
    override suspend fun deleteUser(userId: Long): Int

    @Query("SELECT COUNT(*) FROM $TABLE_NAME_REPO")
    override suspend fun countAllRepos(): Int

    @Query("SELECT * FROM $TABLE_NAME_REPO ORDER BY $COLUMN_INFO_NAME_REPO ASC")
    override suspend fun getAllRepos(): List<RepoEntity>

    @Query("SELECT * FROM $TABLE_NAME_REPO WHERE $COLUMN_INFO_OWNER_ID = :userId ORDER BY $COLUMN_INFO_NAME_REPO ASC")
    override suspend fun getUserRepos(userId: Long): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertRepo(repo: RepoEntity): Long

    @Query("DELETE FROM $TABLE_NAME_REPO WHERE $COLUMN_INFO_ID_REPO = :userId")
    override suspend fun deleteRepo(userId: Long): Int

    override suspend fun getRemoteUsers(query: String): Response<UsersSearchResponse> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun getRemoteUser(userId: Long): Response<UserDetailsResponse> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun getRemoteUserRepos(userId: Long): Response<List<Repo>> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

}