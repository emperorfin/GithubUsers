package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dtosource

import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED
import emperorfin.android.githubusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntityMapper
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo.RepoDataTransferObject
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo.embedded.Owner
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo.embedded.Permissions
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.user.UserDataTransferObject
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.user.UserDataTransferObjectMapper
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UserDetailsResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UserReposResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users.UsersSearchResponse
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo.Repo
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.user.User
import emperorfin.android.githubusers.di.IoDispatcher
import emperorfin.android.githubusers.di.MainDispatcher
import emperorfin.android.githubusers.di.RemoteUsersDao
import emperorfin.android.githubusers.domain.datalayer.dao.IUsersDao
import emperorfin.android.githubusers.domain.datalayer.datasource.UsersDataSource
import emperorfin.android.githubusers.domain.exception.RepoFailure
import emperorfin.android.githubusers.domain.exception.UserFailure
import emperorfin.android.githubusers.domain.model.repo.RepoModel
import emperorfin.android.githubusers.domain.model.repo.RepoModelMapper
import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.model.user.UserModelMapper
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.RepoParams
import emperorfin.android.githubusers.domain.uilayer.event.input.user.UserParams
import emperorfin.android.githubusers.domain.uilayer.event.output.ResultData
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.Params as Params_Repo
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.None as None_Repo
import emperorfin.android.githubusers.domain.uilayer.event.output.user.Params as Params_User
import emperorfin.android.githubusers.domain.uilayer.event.input.user.None as None_User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




data class UsersRemoteDataSourceRetrofit @Inject internal constructor(
    @RemoteUsersDao private val usersDao: IUsersDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val userDtoMapper: UserDataTransferObjectMapper,
    private val userModelMapper: UserModelMapper,
    private val repoEntityMapper: RepoEntityMapper,
    private val repoModelMapper: RepoModelMapper
) : UsersDataSource {
    override suspend fun countAllUsers(params: Params_User): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(params: Params_User): ResultData<List<UserModel>> = withContext(ioDispatcher) {
        when(params){
            is None_User -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
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

                    val response: Response<UsersSearchResponse> = usersDao.getRemoteUsers(
                        query = searchQuery,
                    )

                    withContext(mainDispatcher){
                        if (response.isSuccessful){

                            response.body()?.let {

                                val usersModel: List<UserModel> =
                                    buildUserModelList(users = it.items)

                                // try block doesn't seem to return without return@withContext
                                return@withContext ResultData.Success(usersModel)
                            }
                        }

//                        println("HTTP status code: ${response.code()}")

                        return@withContext ResultData.Error(failure = UserFailure.GetUserRemoteError())
                    }

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserRemoteError(cause = e))
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

                    val response: Response<UserDetailsResponse> = usersDao.getRemoteUser(
                        userId = params.id!!,
                    )

                    withContext(mainDispatcher){
                        if (response.isSuccessful){

                            response.body()?.let {

                                val userModel: UserModel = buildUserModel(user = it)

                                // try block doesn't seem to return without return@withContext
                                return@withContext ResultData.Success(userModel)
                            }
                        }

//                        println("HTTP status code: ${response.code()}")

                        return@withContext ResultData.Error(failure = UserFailure.GetUserRemoteError())
                    }

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserRemoteError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveUser(user: UserModel): ResultData<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(params: Params_User): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun countAllRepos(params: Params_Repo): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getRepos(params: Params_Repo): ResultData<List<RepoModel>> = withContext(ioDispatcher) {
        when(params){
            is None_Repo -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            is RepoParams -> {

                return@withContext try {

                    val response: Response<List<Repo>> = usersDao.getRemoteUserRepos(
                        userId = params.owner?.id!!,
                    )

                    withContext(mainDispatcher){
                        if (response.isSuccessful){

                            response.body()?.let {

                                val userReposResponse = UserReposResponse(
                                    repos = it
                                )

                                val reposModel: List<RepoModel> =
                                    buildRepoModelList(repos = userReposResponse.repos)

                                // try block doesn't seem to return without return@withContext
                                return@withContext ResultData.Success(reposModel)
                            }
                        }

//                        println("HTTP status code: ${response.code()}")

                        return@withContext ResultData.Error(failure = RepoFailure.GetRepoRemoteError())
                    }

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = RepoFailure.RepoRemoteError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveRepo(repo: RepoModel): ResultData<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRepo(params: Params_Repo): ResultData<Int> {
        TODO("Not yet implemented")
    }

    private fun buildUserModelList(users: List<User>): List<UserModel> {
        val usersDto = mutableListOf<UserDataTransferObject>()

        users.forEach {

            val userDto = UserDataTransferObject.newInstance(
                login = it.login,
                id = it.id,
                nodeId = it.nodeId,
                avatarUrl = it.avatarUrl,
                gravatarId = it.gravatarId,
                url = it.url,
                htmlUrl = it.htmlUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                gistsUrl = it.gistsUrl,
                starredUrl = it.starredUrl,
                subscriptionsUrl = it.subscriptionsUrl,
                organizationsUrl = it.organizationsUrl,
                reposUrl = it.reposUrl,
                eventsUrl = it.eventsUrl,
                receivedEventsUrl = it.receivedEventsUrl,
                type = it.type,
                userViewType = it.userViewType,
                siteAdmin = it.siteAdmin,
                score = it.score,
                name = it.name,
                company = it.company,
                blog = it.blog,
                location = it.location,
                email = it.email,
                hireable = it.hireable,
                bio = it.bio,
                twitterUsername = it.twitterUsername,
                publicRepos = it.publicRepos,
                publicGists = it.publicGists,
                followers = it.followers,
                following = it.following,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )

            usersDto.add(userDto)
        }

        return usersDto.map {
            userModelMapper.transform(it)
        }
    }

    private fun buildUserModel(user: UserDetailsResponse): UserModel {

        val userDto = UserDataTransferObject.newInstance(
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

        return userModelMapper.transform(userDto)
    }

    private fun buildRepoModelList(repos: List<Repo>): List<RepoModel> {
        val reposDto = mutableListOf<RepoDataTransferObject>()

        repos.forEach { repo ->

            val owner: Owner

            repo.owner.let {
                owner = Owner(
                    login = it.login,
                    id = it.id,
                    nodeId = it.nodeId,
                    avatarUrl = it.avatarUrl,
                    gravatarId = it.gravatarId,
                    url = it.url,
                    htmlUrl = it.htmlUrl,
                    followersUrl = it.followersUrl,
                    followingUrl = it.followingUrl,
                    gistsUrl = it.gistsUrl,
                    starredUrl = it.starredUrl,
                    subscriptionsUrl = it.subscriptionsUrl,
                    organizationsUrl = it.organizationsUrl,
                    reposUrl = it.reposUrl,
                    eventsUrl = it.eventsUrl,
                    receivedEventsUrl = it.receivedEventsUrl,
                    type = it.type,
                    userViewType = it.userViewType,
                    siteAdmin = it.siteAdmin,
                )
            }

            var permissions: Permissions? = null

            repo.permissions?.let {
                permissions = Permissions(
                    admin = it.admin,
                    maintain = it.maintain,
                    push = it.push,
                    triage = it.triage,
                    pull = it.pull
                )
            }

            val topics = mutableListOf<String>()

            repo.topics?.let {
                it.forEach { topic ->
                    topics.add(topic)
                }
            }

            val repoDto = RepoDataTransferObject.newInstance(
                id = repo.id,
                nodeId = repo.nodeId,
                name = repo.name,
                fullName = repo.fullName,
                private = repo.private,
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
                topics = topics.ifEmpty { null },
                visibility = repo.visibility,
                forks = repo.forks,
                openIssues = repo.openIssues,
                watchers = repo.watchers,
                defaultBranch = repo.defaultBranch,
                permissions = permissions
            )

            reposDto.add(repoDto)
        }

        return reposDto.map {
            repoModelMapper.transform(it)
        }
    }

}
