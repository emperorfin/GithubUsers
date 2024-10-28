package emperorfin.android.githubusers.domain.datalayer.repository

import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 27th October, 2024.
 */




interface IUsersRepository {

    suspend fun countAllUsers(params: Params_User, countRemotely: Boolean = false): ResultData<Int>

    suspend fun getUsers(params: Params_User, forceUpdate: Boolean = false): ResultData<List<UserModel>>

    suspend fun getUser(params: Params_User, forceUpdate: Boolean = false): ResultData<UserModel>

    suspend fun saveUser(user: UserModel, saveRemotely: Boolean = false): ResultData<Long>

    suspend fun deleteUser(params: Params_User, deleteRemotely: Boolean = false): ResultData<Int>

    suspend fun countAllRepos(params: Params_Repo, countRemotely: Boolean = false): ResultData<Int>

    suspend fun getRepos(params: Params_Repo, forceUpdate: Boolean = false): ResultData<List<RepoModel>>

    suspend fun saveRepo(repo: RepoModel, saveRemotely: Boolean = false): ResultData<Long>

    suspend fun deleteRepo(params: Params_Repo, deleteRemotely: Boolean = false): ResultData<Int>

}