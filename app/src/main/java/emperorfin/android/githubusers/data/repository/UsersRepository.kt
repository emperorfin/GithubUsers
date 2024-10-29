package emperorfin.android.githubusers.data.repository

import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.di.UsersLocalDataSource
import emperorfin.android.githubusers.di.UsersRemoteDataSource
import emperorfin.android.githubusers.di.IoDispatcher
import emperorfin.android.githubusers.domain.datalayer.datasource.UsersDataSource
import emperorfin.android.githubusers.domain.datalayer.repository.IUsersRepository
import emperorfin.android.githubusers.domain.exception.RepoFailure
import emperorfin.android.githubusers.domain.exception.UserFailure
import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.repo.embedded.Owner as Owner_Model
import emperorfin.android.githubusers.domain.model.repo.embedded.Permissions as Permissions_Model
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.RepoParams
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded.Owner as Owner_Params
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 27th October, 2024.
 */




data class UsersRepository @Inject constructor(
    @UsersLocalDataSource private val usersLocalDataSource: UsersDataSource,
    @UsersRemoteDataSource private val usersRemoteDataSource: UsersDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IUsersRepository {

    private var cachedUsers: ConcurrentMap<Long, UserModel>? = null
    private var cachedUserRepos: ConcurrentMap<Long, RepoModel>? = null

    override suspend fun countAllUsers(
        params: Params_User, 
        countRemotely: Boolean
    ): ResultData<Int> = withContext(ioDispatcher) {
        if (countRemotely) {
            return@withContext usersRemoteDataSource.countAllUsers(params = params)
        } else {
            return@withContext usersLocalDataSource.countAllUsers(params = params)
        }
    }

    override suspend fun getUsers(
        params: Params_User,
        forceUpdate: Boolean
    ): ResultData<List<UserModel>> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {
                cachedUsers?.let { users ->
                    return@withContext ResultData.Success(users.values.sortedBy { it.login })
                }
            }

            val newUsers: ResultData<List<UserModel>> =
                fetchUsersFromRemoteOrLocal(params = params, forceUpdate = forceUpdate)

            // Refresh the cache with the new users
            (newUsers as? ResultData.Success)?.let { refreshCache(it.data) }

            cachedUsers?.values?.let { users ->
                return@withContext ResultData.Success(users.sortedBy { it.login })
            }

            (newUsers as? ResultData.Success)?.let {
                if (it.data.isNotEmpty()) { // it.data.isEmpty()
                    return@withContext ResultData.Success(it.data)
                }
            }

            return@withContext newUsers as ResultData.Error
        }
    }

    override suspend fun getUser(params: Params_User, forceUpdate: Boolean): ResultData<UserModel> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {

                if (params is UserParams) {
                    if (cachedUsers?.containsKey(params.id) == true) {

//                        val userCached = cachedUsers?.get(params.id)
                        val userCached = cachedUsers?.get(params.id)!!

                        return@withContext ResultData.Success(userCached)

//                        if (userCached?.rated != null) {
//                            return@withContext ResultData.Success(userCached)
//                        }
                    }
                }
            }

            val newUser: ResultData<UserModel> =
                fetchUserFromRemoteOrLocal(params = params, forceUpdate = forceUpdate)

            // Refresh the cache with the new productsOverviews
            (newUser as? ResultData.Success)?.let { refreshCache(it.data) }

            if (params is UserParams) {
                if (cachedUsers?.containsKey(params.id) == true) {

//                    val userCached = cachedUsers?.get(params.id)
                    val userCached = cachedUsers?.get(params.id)!!

                    return@withContext ResultData.Success(userCached)

//                    if (userCached?.rated != null) {
//                        return@withContext ResultData.Success(userCached)
//                    }
                }
            }

            (newUser as? ResultData.Success)?.let {
                return@withContext ResultData.Success(it.data)

//                if (it.data.rated != null) {
//                    return@withContext ResultData.Success(it.data)
//                }
            }

            return@withContext newUser as ResultData.Error
        }
    }

    override suspend fun saveUser(
        user: UserModel, 
        saveRemotely: Boolean
    ): ResultData<Long> = withContext(ioDispatcher) {

        if (saveRemotely) {
            return@withContext usersRemoteDataSource.saveUser(user = user)
        } else {
            return@withContext usersLocalDataSource.saveUser(user = user)
        }

    }

    override suspend fun deleteUser(
        params: Params_User, 
        deleteRemotely: Boolean
    ): ResultData<Int> = withContext(ioDispatcher) {

        if (deleteRemotely) {
            return@withContext usersRemoteDataSource.deleteUser(params = params)
        } else {
            return@withContext usersLocalDataSource.deleteUser(params = params)
        }
    }

    override suspend fun countAllRepos(
        params: Params_Repo,
        countRemotely: Boolean
    ): ResultData<Int> = withContext(ioDispatcher) {
        if (countRemotely) {
            return@withContext usersRemoteDataSource.countAllRepos(params = params)
        } else {
            return@withContext usersLocalDataSource.countAllRepos(params = params)
        }
    }

    override suspend fun getRepos(
        params: Params_Repo,
        forceUpdate: Boolean
    ): ResultData<List<RepoModel>> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {
                cachedUserRepos?.let { repos ->
                    return@withContext ResultData.Success(repos.values.sortedBy { it.name })
                }
            }

            val newRepos: ResultData<List<RepoModel>> =
                fetchReposFromRemoteOrLocal(params = params, forceUpdate = forceUpdate)

            // Refresh the cache with the new repos
            (newRepos as? ResultData.Success)?.let { refreshRepoCache(it.data) }

            cachedUserRepos?.values?.let { repos ->
                return@withContext ResultData.Success(repos.sortedBy { it.name })
            }

            (newRepos as? ResultData.Success)?.let {
//                if (it.data.isNotEmpty()) { // it.data.isEmpty()
//                    return@withContext ResultData.Success(it.data)
//                }
                return@withContext ResultData.Success(it.data)
            }

            return@withContext newRepos as ResultData.Error
        }
    }

    override suspend fun saveRepo(repo: RepoModel, saveRemotely: Boolean): ResultData<Long> = withContext(ioDispatcher) {

        if (saveRemotely) {
            return@withContext usersRemoteDataSource.saveRepo(repo = repo)
        } else {
            return@withContext usersLocalDataSource.saveRepo(repo = repo)
        }

    }

    override suspend fun deleteRepo(
        params: Params_Repo,
        deleteRemotely: Boolean
    ): ResultData<Int> = withContext(ioDispatcher) {

        if (deleteRemotely) {
            return@withContext usersRemoteDataSource.deleteRepo(params = params)
        } else {
            return@withContext usersLocalDataSource.deleteRepo(params = params)
        }
    }

    private suspend fun fetchUsersFromRemoteOrLocal(params: Params_User, forceUpdate: Boolean): ResultData<List<UserModel>> {
        var isRemoteException = false

        // Remote first
        if (forceUpdate) {
            when (val usersRemote = usersRemoteDataSource.getUsers(params = params)) {
//             is Error -> return remoteUsers // Timber.w("Remote data source fetch failed")
                is ResultData.Error -> {
                    if (usersRemote.failure is UserFailure.UserRemoteError)
                        isRemoteException = true
                }
                is ResultData.Success -> {
                    refreshLocalDataSource(users = usersRemote.data)

                    return usersRemote
                }
//             else -> throw IllegalStateException()
                else -> {}
            }
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            if (isRemoteException)
                return ResultData.Error(
                    UserFailure.GetUserRepositoryError(
                        message = R.string.exception_occurred_remote
                    )
                )

            return ResultData.Error(
                // TODO: Change GetUserRemoteError to GetUserRepositoryError and update
                //  test cases too.
                UserFailure.GetUserRepositoryError(
                    message = R.string.error_cant_force_refresh_users_remote_data_source_unavailable
                )
            )
        }

        // Local if remote fails
        val usersLocal = usersLocalDataSource.getUsers(params = params)

        if (usersLocal is ResultData.Success) return usersLocal

        if ((usersLocal as ResultData.Error).failure is UserFailure.UserLocalError)
            return ResultData.Error(
                UserFailure.GetUserRepositoryError(
                    R.string.exception_occurred_local
                )
            )

//        return Error((usersLocal as Error).failure)
        return ResultData.Error(
            UserFailure.GetUserRepositoryError(
                R.string.error_fetching_from_remote_and_local
            )
        )
    }

    private suspend fun fetchUserFromRemoteOrLocal(params: Params_User, forceUpdate: Boolean): ResultData<UserModel> {
        var isRemoteException = false

        // Remote first
        if (forceUpdate) {
            when (val userRemote = usersRemoteDataSource.getUser(params = params)) {
//             is Error -> return remoteUser // Timber.w("Remote data source fetch failed")
                is ResultData.Error -> {
                    if (userRemote.failure is UserFailure.UserRemoteError)
                        isRemoteException = true
                }
                is ResultData.Success -> {
                    refreshLocalDataSource(user = userRemote.data)

                    return userRemote
                }
//             else -> throw IllegalStateException()
                else -> {}
            }
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            if (isRemoteException)
                return ResultData.Error(
                    UserFailure.GetUserRepositoryError(
                        message = R.string.exception_occurred_remote
                    )
                )

            return ResultData.Error(
                // TODO: Change GetUserRemoteError to GetUserRepositoryError and update
                //  test cases too.
                UserFailure.GetUserRemoteError(
                    message = R.string.error_cant_force_refresh_users_remote_data_source_unavailable
                )
            )
        }

        // Local if remote fails
        val userLocal = usersLocalDataSource.getUser(params = params)

        if (userLocal is ResultData.Success) return userLocal

        if ((userLocal as ResultData.Error).failure is UserFailure.UserLocalError)
            return ResultData.Error(
                UserFailure.GetUserRepositoryError(
                    R.string.exception_occurred_local
                )
            )

//        return Error((userLocal as Error).failure)
        return ResultData.Error(
            UserFailure.GetUserRepositoryError(
                R.string.error_fetching_from_remote_and_local
            )
        )
    }

    private fun refreshCache(users: List<UserModel>) {
        cachedUsers?.clear()

        users.sortedBy { it.login }.forEach {
            cacheAndPerform(it) {}
        }
    }

    private fun refreshCache(user: UserModel) {
        cachedUsers?.remove(user.id)

        cacheAndPerform(user) {}
    }

    private suspend fun refreshLocalDataSource(users: List<UserModel>) {

        return // TODO: REMOVE THIS LINE TO REFRESH LOCAL DATA SOURCE

        users.forEach {
            val params = UserParams(id = it.id, login = it.login)

            usersLocalDataSource.deleteUser(params = params)

            usersLocalDataSource.saveUser(user = it)
        }
    }

    private suspend fun refreshLocalDataSource(user: UserModel) {

        val params = UserParams(id = user.id, login = user.login)

        usersLocalDataSource.deleteUser(params = params)

        usersLocalDataSource.saveUser(user = user)
    }

    private fun cacheUser(user: UserModel): UserModel {

        val cachedUser = UserModel.newInstance(
            login = user.login,
            id = user.id,
            nodeId = user.nodeId,
            avatarUrl = user.avatarUrl,
            gravatarId = user.gravatarId,
            url = user.url,
            htmlUrl = user.htmlUrl,
            followersUrl = user.followersUrl,
            followingUrl = user.followingUrl,
            gistsUrl = user.gistsUrl,
            starredUrl = user.starredUrl,
            subscriptionsUrl = user.subscriptionsUrl,
            organizationsUrl = user.organizationsUrl,
            reposUrl = user.reposUrl,
            eventsUrl = user.eventsUrl,
            receivedEventsUrl = user.receivedEventsUrl,
            type = user.type,
            userViewType = user.userViewType,
            siteAdmin = user.siteAdmin,
            score = user.score,
            name = user.name,
            company = user.company,
            blog = user.blog,
            location = user.location,
            email = user.email,
            hireable = user.hireable,
            bio = user.bio,
            twitterUsername = user.twitterUsername,
            publicRepos = user.publicRepos,
            publicGists = user.publicGists,
            followers = user.followers,
            following = user.following,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )

        // Create if it doesn't exist.
        if (cachedUsers == null) {
            cachedUsers = ConcurrentHashMap()
        }

        cachedUsers?.put(cachedUser.id, cachedUser)

        return cachedUser
    }

    private inline fun cacheAndPerform(user: UserModel, perform: (UserModel) -> Unit) {

        val cachedUser = cacheUser(user)

        perform(cachedUser)
    }

    private suspend fun fetchReposFromRemoteOrLocal(params: Params_Repo, forceUpdate: Boolean): ResultData<List<RepoModel>> {
        var isRemoteException = false

        // Remote first
        if (forceUpdate) {
            when (val userReposRemote = usersRemoteDataSource.getRepos(params = params)) {
//             is Error -> return remoteRepos // Timber.w("Remote data source fetch failed")
                is ResultData.Error -> {
                    if (userReposRemote.failure is RepoFailure.RepoRemoteError)
                        isRemoteException = true
                }
                is ResultData.Success -> {
                    refreshRepoLocalDataSource(repos = userReposRemote.data)

                    return userReposRemote
                }
//             else -> throw IllegalStateException()
                else -> {}
            }
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            if (isRemoteException)
                return ResultData.Error(
                    RepoFailure.GetRepoRepositoryError(
                        message = R.string.exception_occurred_remote
                    )
                )

            return ResultData.Error(
                // TODO: Change GetRepoRemoteError to GetRepoRepositoryError and update
                //  test cases too.
                RepoFailure.GetRepoRepositoryError(
                    message = R.string.error_cant_force_refresh_repos_remote_data_source_unavailable
                )
            )
        }

        // Local if remote fails
        val userReposLocal = usersLocalDataSource.getRepos(params = params)

        if (userReposLocal is ResultData.Success) return userReposLocal

        if ((userReposLocal as ResultData.Error).failure is RepoFailure.RepoLocalError)
            return ResultData.Error(
                RepoFailure.GetRepoRepositoryError(
                    R.string.exception_occurred_local
                )
            )

//        return Error((userReposLocal as Error).failure)
        return ResultData.Error(
            RepoFailure.GetRepoRepositoryError(
                R.string.error_fetching_from_remote_and_local
            )
        )
    }

    private fun refreshRepoCache(repos: List<RepoModel>) {
        cachedUserRepos?.clear()

        repos.sortedBy { it.name }.forEach {
            cacheAndPerform(it) {}
        }
    }

    private suspend fun refreshRepoLocalDataSource(repos: List<RepoModel>) {

        return // TODO: REMOVE THIS LINE TO REFRESH LOCAL DATA SOURCE

        repos.forEach {
            val params = RepoParams(
                id = it.id,
                owner = Owner_Params(id = it.id, login = it.owner.login)
            )

            usersLocalDataSource.deleteRepo(params = params)

            usersLocalDataSource.saveRepo(repo = it)
        }
    }

    private fun cacheRepo(repo: RepoModel): RepoModel {

        val repoOwner = repo.owner
        val repoPermissions = repo.permissions

        val topics = mutableListOf<String>()

        val owner = Owner_Model(
            login = repoOwner.login,
            id = repoOwner.id,
            nodeId = repoOwner.nodeId,
            avatarUrl = repoOwner.avatarUrl,
            gravatarId = repoOwner.gravatarId,
            url = repoOwner.url,
            htmlUrl = repoOwner.htmlUrl,
            followersUrl = repoOwner.followersUrl,
            followingUrl = repoOwner.followingUrl,
            gistsUrl = repoOwner.gistsUrl,
            starredUrl = repoOwner.starredUrl,
            subscriptionsUrl = repoOwner.subscriptionsUrl,
            organizationsUrl = repoOwner.organizationsUrl,
            reposUrl = repoOwner.reposUrl,
            eventsUrl = repoOwner.eventsUrl,
            receivedEventsUrl = repoOwner.receivedEventsUrl,
            type = repoOwner.type,
            userViewType = repoOwner.userViewType,
            siteAdmin = repoOwner.siteAdmin,
        )

        val permissions = Permissions_Model(
            admin = repoPermissions?.admin,
            maintain = repoPermissions?.maintain,
            push = repoPermissions?.push,
            triage = repoPermissions?.triage,
            pull = repoPermissions?.pull
        )

        repo.topics.let {
            it.forEach { topic ->
                topics.add(topic)
            }
        }

        val cachedRepo = RepoModel.newInstance(
            id = repo.id,
            nodeId = repo.nodeId,
            name = repo.name,
            fullName = repo.fullName,
            private = repo._private,
            owner = owner,
            htmlUrl = repo.htmlUrl,
            description = repo.description,
            fork = repo.fork,
            url = repo.url,
            forksUrl = repo.forksUrl,
            keysUrl = repo.keysUrl,
            collaboratorsUrl = repo.collaboratorsUrl,
            teamsUrl = repo.teamsUrl,
            hooksUrl = repo.hooksUrl,
            issueEventsUrl = repo.issuesUrl,
            eventsUrl = repo.eventsUrl,
            assigneesUrl = repo.assigneesUrl,
            branchesUrl = repo.branchesUrl,
            tagsUrl = repo.tagsUrl,
            blobsUrl = repo.blobsUrl,
            gitTagsUrl = repo.gitTagsUrl,
            gitRefsUrl = repo.gitRefsUrl,
            treesUrl = repo.treesUrl,
            statusesUrl = repo.statusesUrl,
            languagesUrl = repo.languagesUrl,
            stargazersUrl = repo.stargazersUrl,
            contributorsUrl = repo.contributorsUrl,
            subscribersUrl = repo.subscribersUrl,
            subscriptionUrl = repo.subscriptionUrl,
            commitsUrl = repo.commitsUrl,
            gitCommitsUrl = repo.gitCommitsUrl,
            commentsUrl = repo.commentsUrl,
            issueCommentUrl = repo.issueCommentUrl,
            contentsUrl = repo.contentsUrl,
            compareUrl = repo.compareUrl,
            mergesUrl = repo.mergesUrl,
            archiveUrl = repo.archiveUrl,
            downloadsUrl = repo.downloadsUrl,
            issuesUrl = repo.issuesUrl,
            pullsUrl = repo.pullsUrl,
            milestonesUrl = repo.milestonesUrl,
            notificationsUrl = repo.notificationsUrl,
            labelsUrl = repo.labelsUrl,
            releasesUrl = repo.releasesUrl,
            deploymentsUrl = repo.deploymentsUrl,
            createdAt = repo.createdAt,
            updatedAt = repo.updatedAt,
            pushedAt = repo.pushedAt,
            gitUrl = repo.gitUrl,
            sshUrl = repo.sshUrl,
            cloneUrl = repo.cloneUrl,
            svnUrl = repo.svnUrl,
            homepage = repo.homepage,
            size = repo.size,
            stargazersCount = repo.stargazersCount,
            watchersCount = repo.watchersCount,
            language = repo.language,
            hasIssues = repo.hasIssues,
            hasProjects = repo.hasProjects,
            hasDownloads = repo.hasDownloads,
            hasWiki = repo.hasWiki,
            hasPages = repo.hasPages,
            hasDiscussions = repo.hasDiscussions,
            forksCount = repo.forksCount,
            mirrorUrl = repo.mirrorUrl,
            archived = repo.archived,
            disabled = repo.disabled,
            openIssuesCount = repo.openIssuesCount,
            license = repo.license,
            allowForking = repo.allowForking,
            isTemplate = repo.isTemplate,
            webCommitSignoffRequired = repo.webCommitSignoffRequired,
            topics = topics,
            visibility = repo.visibility,
            forks = repo.forks,
            openIssues = repo.openIssues,
            watchers = repo.watchers,
            defaultBranch = repo.defaultBranch,
            permissions = permissions
        )

        // Create if it doesn't exist.
        if (cachedUserRepos == null) {
            cachedUserRepos = ConcurrentHashMap()
        }

        cachedUserRepos?.put(cachedRepo.id, cachedRepo)

        return cachedRepo
    }

    private inline fun cacheAndPerform(repo: RepoModel, perform: (RepoModel) -> Unit) {

        val cachedRepo = cacheRepo(repo)

        perform(cachedRepo)
    }
}
