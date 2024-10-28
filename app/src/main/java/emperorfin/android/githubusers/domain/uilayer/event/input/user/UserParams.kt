package emperorfin.android.githubusers.domain.uilayer.event.input.user

import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserModelParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class UserParams(
    override val login: String,
    override val id: Long,
    override val nodeId: String? = null,
    override val avatarUrl: String? = null,
    override val gravatarId: String? = null,
    override val url: String? = null,
    override val htmlUrl: String? = null,
    override val followersUrl: String? = null,
    override val followingUrl: String? = null,
    override val gistsUrl: String? = null,
    override val starredUrl: String? = null,
    override val subscriptionsUrl: String? = null,
    override val organizationsUrl: String? = null,
    override val reposUrl: String? = null,
    override val eventsUrl: String? = null,
    override val receivedEventsUrl: String? = null,
    override val type: String? = null,
    override val userViewType: String? = null,
    override val siteAdmin: Boolean? = null,
    override val score: Double? = null,
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
) : UserModelParams
