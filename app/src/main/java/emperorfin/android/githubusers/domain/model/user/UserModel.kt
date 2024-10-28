package emperorfin.android.githubusers.domain.model.user

import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserModelParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class UserModel private constructor(
    override val login: String,
    override val id: Long,
    override val nodeId: String,
    override val avatarUrl: String,
    override val gravatarId: String,
    override val url: String,
    override val htmlUrl: String,
    override val followersUrl: String,
    override val followingUrl: String,
    override val gistsUrl: String,
    override val starredUrl: String,
    override val subscriptionsUrl: String,
    override val organizationsUrl: String,
    override val reposUrl: String,
    override val eventsUrl: String,
    override val receivedEventsUrl: String,
    override val type: String,
    override val userViewType: String,
    override val siteAdmin: Boolean?,
    override val score: Double,
    override val name: String,
    override val company: String,
    override val blog: String,
    override val location: String,
    override val email: String,
    override val hireable: String,
    override val bio: String,
    override val twitterUsername: String,
    override val publicRepos: Int,
    override val publicGists: Int,
    override val followers: Int,
    override val following: Int,
    override val createdAt: String,
    override val updatedAt: String
) : UserModelParams {
    
    companion object {
        
        fun newInstance(
            login: String,
            id: Long,
            nodeId: String,
            avatarUrl: String,
            gravatarId: String,
            url: String,
            htmlUrl: String,
            followersUrl: String,
            followingUrl: String,
            gistsUrl: String,
            starredUrl: String,
            subscriptionsUrl: String,
            organizationsUrl: String,
            reposUrl: String,
            eventsUrl: String,
            receivedEventsUrl: String,
            type: String,
            userViewType: String,
            siteAdmin: Boolean?,
            score: Double,
            name: String,
            company: String,
            blog: String,
            location: String,
            email: String,
            hireable: String,
            bio: String,
            twitterUsername: String,
            publicRepos: Int,
            publicGists: Int,
            followers: Int,
            following: Int,
            createdAt: String,
            updatedAt: String
        ): UserModel {
            return UserModel(
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
    
}
