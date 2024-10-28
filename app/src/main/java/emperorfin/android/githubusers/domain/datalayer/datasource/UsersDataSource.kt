package emperorfin.android.githubusers.domain.datalayer.datasource

import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




interface UsersDataSource {

    suspend fun countAllUsers(params: Params_User): ResultData<Int>

    suspend fun getUsers(params: Params_User): ResultData<List<UserModel>>

    suspend fun getUser(params: Params_User): ResultData<UserModel>

    suspend fun saveUser(user: UserModel): ResultData<Long>

    suspend fun deleteUser(params: Params_User): ResultData<Int>

    suspend fun countAllRepos(params: Params_Repo): ResultData<Int>

    suspend fun getRepos(params: Params_Repo): ResultData<List<RepoModel>>

    suspend fun saveRepo(repo: RepoModel): ResultData<Long>

    suspend fun deleteRepo(params: Params_Repo): ResultData<Int>
    
}