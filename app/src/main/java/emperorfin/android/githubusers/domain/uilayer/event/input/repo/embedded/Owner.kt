package emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded

import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.OwnerParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




data class Owner(
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
    override val siteAdmin: Boolean? = null
) : OwnerParams
