package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users

import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo.Repo


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class UserReposResponse(
    val repos: List<Repo>
)
