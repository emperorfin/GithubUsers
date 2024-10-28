package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.embedded

import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.OwnerParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 25th October, 2024.
 */




data class Owner(
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
    override val siteAdmin: Boolean?
) : OwnerParams
