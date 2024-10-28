package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users

import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.user.User


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class UserDetailsResponse(
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
    override val siteAdmin: Boolean,
    override val score: Double?,
    override val name: String? = null,
    override val company: String? = null,
    override val blog: String? = null,
    override val location: String? = null,
    override val email: String? = null,
    override val hireable: String? = null,
    override val bio: String? = null,
    override val twitterUsername: String? = null,
    override val publicRepos: Int? = null,
    override val publicGists: Int? = null,
    override val followers: Int? = null,
    override val following: Int? = null,
    override val createdAt: String? = null,
    override val updatedAt: String? = null
) : User(
    login,
    id,
    nodeId,
    avatarUrl,
    gravatarId,
    url,
    htmlUrl,
    followersUrl,
    followingUrl,
    gistsUrl,
    starredUrl,
    subscriptionsUrl,
    organizationsUrl,
    reposUrl,
    eventsUrl,
    receivedEventsUrl,
    type,
    userViewType,
    siteAdmin,
    score,
    name,
    company,
    blog,
    location,
    email,
    hireable,
    bio,
    twitterUsername,
    publicRepos,
    publicGists,
    followers,
    following,
    createdAt,
    updatedAt
)
