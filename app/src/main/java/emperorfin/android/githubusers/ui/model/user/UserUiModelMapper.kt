package emperorfin.android.githubusers.ui.model.user

import emperorfin.android.githubusers.domain.model.user.UserModel
import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserUiModelParams
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




class UserUiModelMapper @Inject constructor() {

    fun transform(user: UserModel): UserUiModel {

        val login: String = user.login
        val id: Long = user.id
        val nodeId: String = user.nodeId
        val avatarUrl: String = user.avatarUrl
        val gravatarId: String = user.gravatarId
        val url: String = user.url
        val htmlUrl: String = user.htmlUrl
        val followersUrl: String = user.followersUrl
        val followingUrl: String = user.followingUrl
        val gistsUrl: String = user.gistsUrl
        val starredUrl: String = user.starredUrl
        val subscriptionsUrl: String = user.subscriptionsUrl
        val organizationsUrl: String = user.organizationsUrl
        val reposUrl: String = user.reposUrl
        val eventsUrl: String = user.eventsUrl
        val receivedEventsUrl: String = user.receivedEventsUrl
        val type: String = user.type
        val userViewType: String = user.userViewType
        val siteAdmin: Boolean? = user.siteAdmin
        val score: Double = user.score
        val name: String = user.name
        val company: String = user.company
        val blog: String = user.blog
        val location: String = user.location
        val email: String = user.email
        val hireable: String = user.hireable
        val bio: String = user.bio
        val twitterUsername: String = user.twitterUsername
        val publicRepos: Int = user.publicRepos
        val publicGists: Int = user.publicGists
        val followers: Int = user.followers
        val following: Int = user.following
        val createdAt: String = user.createdAt
        val updatedAt: String = user.updatedAt

        val handle = "@$login"

        return UserUiModel.newInstance(
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
            updatedAt = updatedAt,
            handle = handle
        )
    }
    
}