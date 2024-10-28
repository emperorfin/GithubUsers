package emperorfin.android.githubusers.domain.model.user

import emperorfin.android.githubusers.domain.constant.DoubleConstants.MINUS_0_0
import emperorfin.android.githubusers.domain.constant.IntegerConstants.MINUS_0
import emperorfin.android.githubusers.domain.constant.StringConstants.EMPTY
import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserDataTransferObjectParams
import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserEntityParams
import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserUiModelParams
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October 2024.
 */




class UserModelMapper @Inject constructor() {

    fun transform(user: UserDataTransferObjectParams): UserModel {

//        val login: String = user.login ?: EMPTY
        val login: String = user.login!!
//        val id: Long = user.id ?: MINUS_0L
//        val id: Long = user.id ?: System.currentTimeMillis()
        val id: Long = user.id!!
        val nodeId: String = user.nodeId ?: EMPTY
        val avatarUrl: String = user.avatarUrl ?: EMPTY
        val gravatarId: String = user.gravatarId ?: EMPTY
        val url: String = user.url ?: EMPTY
        val htmlUrl: String = user.htmlUrl ?: EMPTY
        val followersUrl: String = user.followersUrl ?: EMPTY
        val followingUrl: String = user.followingUrl ?: EMPTY
        val gistsUrl: String = user.gistsUrl ?: EMPTY
        val starredUrl: String = user.starredUrl ?: EMPTY
        val subscriptionsUrl: String = user.subscriptionsUrl ?: EMPTY
        val organizationsUrl: String = user.organizationsUrl ?: EMPTY
        val reposUrl: String = user.reposUrl ?: EMPTY
        val eventsUrl: String = user.eventsUrl ?: EMPTY
        val receivedEventsUrl: String = user.receivedEventsUrl ?: EMPTY
        val type: String = user.type ?: EMPTY
        val userViewType: String = user.userViewType ?: EMPTY
        val siteAdmin: Boolean? = user.siteAdmin
        val score: Double = user.score ?: MINUS_0_0
        val name: String = user.name ?: EMPTY
        val company: String = user.company ?: EMPTY
        val blog: String = user.blog ?: EMPTY
        val location: String = user.location ?: EMPTY
        val email: String = user.email ?: EMPTY
        val hireable: String = user.hireable ?: EMPTY
        val bio: String = user.bio ?: EMPTY
        val twitterUsername: String = user.twitterUsername ?: EMPTY
        val publicRepos: Int = user.publicRepos ?: MINUS_0
        val publicGists: Int = user.publicGists ?: MINUS_0
        val followers: Int = user.followers ?: MINUS_0
        val following: Int = user.following ?: MINUS_0
        val createdAt: String = user.createdAt ?: EMPTY
        val updatedAt: String = user.updatedAt ?: EMPTY

        return UserModel.newInstance(
            login = login,
            id = id,
            nodeId = nodeId,
            avatarUrl = avatarUrl,
            gravatarId = gravatarId,
            url = url,
            htmlUrl = htmlUrl,
            followersUrl = followersUrl,
            followingUrl = followingUrl,
            gistsUrl = gistsUrl,
            starredUrl = starredUrl,
            subscriptionsUrl = subscriptionsUrl,
            organizationsUrl = organizationsUrl,
            reposUrl = reposUrl,
            eventsUrl = eventsUrl,
            receivedEventsUrl = receivedEventsUrl,
            type = type,
            userViewType = userViewType,
            siteAdmin = siteAdmin,
            score = score,
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            hireable = hireable,
            bio = bio,
            twitterUsername = twitterUsername,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    fun transform(user: UserEntityParams): UserModel {

        val login: String = user.login!!
//        val id: Long = user.id ?: MINUS_0L
//        val id: Long = user.id ?: System.currentTimeMillis()
        val id: Long = user.id!!
        val nodeId: String = user.nodeId!!
        val avatarUrl: String = user.avatarUrl!!
        val gravatarId: String = user.gravatarId!!
        val url: String = user.url!!
        val htmlUrl: String = user.htmlUrl!!
        val followersUrl: String = user.followersUrl!!
        val followingUrl: String = user.followingUrl!!
        val gistsUrl: String = user.gistsUrl!!
        val starredUrl: String = user.starredUrl!!
        val subscriptionsUrl: String = user.subscriptionsUrl!!
        val organizationsUrl: String = user.organizationsUrl!!
        val reposUrl: String = user.reposUrl!!
        val eventsUrl: String = user.eventsUrl!!
        val receivedEventsUrl: String = user.receivedEventsUrl!!
        val type: String = user.type!!
        val userViewType: String = user.userViewType!!
        val siteAdmin: Boolean? = user.siteAdmin
        val score: Double = user.score!!
        val name: String = user.name!!
        val company: String = user.company!!
        val blog: String = user.blog!!
        val location: String = user.location!!
        val email: String = user.email!!
        val hireable: String = user.hireable!!
        val bio: String = user.bio!!
        val twitterUsername: String = user.twitterUsername!!
        val publicRepos: Int = user.publicRepos!!
        val publicGists: Int = user.publicGists!!
        val followers: Int = user.followers!!
        val following: Int = user.following!!
        val createdAt: String = user.createdAt!!
        val updatedAt: String = user.updatedAt!!

        return UserModel.newInstance(
            login = login,
            id = id,
            nodeId = nodeId,
            avatarUrl = avatarUrl,
            gravatarId = gravatarId,
            url = url,
            htmlUrl = htmlUrl,
            followersUrl = followersUrl,
            followingUrl = followingUrl,
            gistsUrl = gistsUrl,
            starredUrl = starredUrl,
            subscriptionsUrl = subscriptionsUrl,
            organizationsUrl = organizationsUrl,
            reposUrl = reposUrl,
            eventsUrl = eventsUrl,
            receivedEventsUrl = receivedEventsUrl,
            type = type,
            userViewType = userViewType,
            siteAdmin = siteAdmin,
            score = score,
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            hireable = hireable,
            bio = bio,
            twitterUsername = twitterUsername,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    fun transform(user: UserUiModelParams): UserModel {

        val login: String = user.login!!
//        val id: Long = user.id ?: MINUS_0L
//        val id: Long = user.id ?: System.currentTimeMillis()
        val id: Long = user.id!!
        val nodeId: String = user.nodeId!!
        val avatarUrl: String = user.avatarUrl!!
        val gravatarId: String = user.gravatarId!!
        val url: String = user.url!!
        val htmlUrl: String = user.htmlUrl!!
        val followersUrl: String = user.followersUrl!!
        val followingUrl: String = user.followingUrl!!
        val gistsUrl: String = user.gistsUrl!!
        val starredUrl: String = user.starredUrl!!
        val subscriptionsUrl: String = user.subscriptionsUrl!!
        val organizationsUrl: String = user.organizationsUrl!!
        val reposUrl: String = user.reposUrl!!
        val eventsUrl: String = user.eventsUrl!!
        val receivedEventsUrl: String = user.receivedEventsUrl!!
        val type: String = user.type!!
        val userViewType: String = user.userViewType!!
        val siteAdmin: Boolean? = user.siteAdmin
        val score: Double = user.score!!
        val name: String = user.name!!
        val company: String = user.company!!
        val blog: String = user.blog!!
        val location: String = user.location!!
        val email: String = user.email!!
        val hireable: String = user.hireable!!
        val bio: String = user.bio!!
        val twitterUsername: String = user.twitterUsername!!
        val publicRepos: Int = user.publicRepos!!
        val publicGists: Int = user.publicGists!!
        val followers: Int = user.followers!!
        val following: Int = user.following!!
        val createdAt: String = user.createdAt!!
        val updatedAt: String = user.updatedAt!!

        return UserModel.newInstance(
            login = login,
            id = id,
            nodeId = nodeId,
            avatarUrl = avatarUrl,
            gravatarId = gravatarId,
            url = url,
            htmlUrl = htmlUrl,
            followersUrl = followersUrl,
            followingUrl = followingUrl,
            gistsUrl = gistsUrl,
            starredUrl = starredUrl,
            subscriptionsUrl = subscriptionsUrl,
            organizationsUrl = organizationsUrl,
            reposUrl = reposUrl,
            eventsUrl = eventsUrl,
            receivedEventsUrl = receivedEventsUrl,
            type = type,
            userViewType = userViewType,
            siteAdmin = siteAdmin,
            score = score,
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            hireable = hireable,
            bio = bio,
            twitterUsername = twitterUsername,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

}