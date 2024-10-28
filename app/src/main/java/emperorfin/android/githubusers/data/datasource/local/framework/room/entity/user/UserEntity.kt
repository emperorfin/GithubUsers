package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.TABLE_NAME
import emperorfin.android.githubusers.domain.uilayer.event.output.user.UserEntityParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [COLUMN_INFO_ID]
)
data class UserEntity(
    @ColumnInfo(name = COLUMN_INFO_ID)
    override val id: Long,
    @ColumnInfo(name = COLUMN_INFO_LOGIN)
    override val login: String,
    @ColumnInfo(name = COLUMN_INFO_NODE_ID)
    override val nodeId: String,
    @ColumnInfo(name = COLUMN_INFO_AVATAR_URL)
    override val avatarUrl: String,
    @ColumnInfo(name = COLUMN_INFO_GRAVATAR_ID)
    override val gravatarId: String,
    @ColumnInfo(name = COLUMN_INFO_URL)
    override val url: String,
    @ColumnInfo(name = COLUMN_INFO_HTML_URL)
    override val htmlUrl: String,
    @ColumnInfo(name = COLUMN_INFO_FOLLOWERS_URL)
    override val followersUrl: String,
    @ColumnInfo(name = COLUMN_INFO_FOLLOWING_URL)
    override val followingUrl: String,
    @ColumnInfo(name = COLUMN_INFO_GISTS_URL)
    override val gistsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_STARRED_URL)
    override val starredUrl: String,
    @ColumnInfo(name = COLUMN_INFO_SUBSCRIPTIONS_URL)
    override val subscriptionsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ORGANIZATIONS_URL)
    override val organizationsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_REPOS_URL)
    override val reposUrl: String,
    @ColumnInfo(name = COLUMN_INFO_EVENTS_URL)
    override val eventsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_RECEIVED_EVENTS_URL)
    override val receivedEventsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_TYPE)
    override val type: String,
    @ColumnInfo(name = COLUMN_INFO_USER_VIEW_TYPE)
    override val userViewType: String,
    @ColumnInfo(name = COLUMN_INFO_SITE_ADMIN)
    override val siteAdmin: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_SCORE)
    override val score: Double,
    @ColumnInfo(name = COLUMN_INFO_NAME)
    override val name: String,
    @ColumnInfo(name = COLUMN_INFO_COMPANY)
    override val company: String,
    @ColumnInfo(name = COLUMN_INFO_BLOG)
    override val blog: String,
    @ColumnInfo(name = COLUMN_INFO_LOCATION)
    override val location: String,
    @ColumnInfo(name = COLUMN_INFO_EMAIL)
    override val email: String,
    @ColumnInfo(name = COLUMN_INFO_HIREABLE)
    override val hireable: String,
    @ColumnInfo(name = COLUMN_INFO_BIO)
    override val bio: String,
    @ColumnInfo(name = COLUMN_INFO_TWITTER_USERNAME)
    override val twitterUsername: String,
    @ColumnInfo(name = COLUMN_INFO_PUBLIC_REPOS)
    override val publicRepos: Int,
    @ColumnInfo(name = COLUMN_INFO_PUBLIC_GISTS)
    override val publicGists: Int,
    @ColumnInfo(name = COLUMN_INFO_FOLLOWERS)
    override val followers: Int,
    @ColumnInfo(name = COLUMN_INFO_FOLLOWING)
    override val following: Int,
    @ColumnInfo(name = COLUMN_INFO_CREATED_AT)
    override val createdAt: String,
    @ColumnInfo(name = COLUMN_INFO_UPDATED_AT)
    override val updatedAt: String
) : UserEntityParams {

    companion object {

        const val TABLE_NAME = "table_users"

        const val COLUMN_INFO_ID = "id"
        const val COLUMN_INFO_LOGIN = "login"
        const val COLUMN_INFO_NODE_ID = "node_id"
        const val COLUMN_INFO_AVATAR_URL = "avatar_url"
        const val COLUMN_INFO_GRAVATAR_ID = "gravatar_id"
        const val COLUMN_INFO_URL = "url"
        const val COLUMN_INFO_HTML_URL = "html_url"
        const val COLUMN_INFO_FOLLOWERS_URL = "followers_url"
        const val COLUMN_INFO_FOLLOWING_URL = "following_url"
        const val COLUMN_INFO_GISTS_URL = "gists_url"
        const val COLUMN_INFO_STARRED_URL = "starred_url"
        const val COLUMN_INFO_SUBSCRIPTIONS_URL = "subscriptions_url"
        const val COLUMN_INFO_ORGANIZATIONS_URL = "organizations_url"
        const val COLUMN_INFO_REPOS_URL = "repos_url"
        const val COLUMN_INFO_EVENTS_URL = "events_url"
        const val COLUMN_INFO_RECEIVED_EVENTS_URL = "received_events_url"
        const val COLUMN_INFO_TYPE = "type"
        const val COLUMN_INFO_USER_VIEW_TYPE = "user_view_type"
        const val COLUMN_INFO_SITE_ADMIN = "site_admin"
        const val COLUMN_INFO_SCORE = "score"
        const val COLUMN_INFO_NAME = "name"
        const val COLUMN_INFO_COMPANY = "company"
        const val COLUMN_INFO_BLOG = "blog"
        const val COLUMN_INFO_LOCATION = "location"
        const val COLUMN_INFO_EMAIL = "email"
        const val COLUMN_INFO_HIREABLE = "hireable"
        const val COLUMN_INFO_BIO = "bio"
        const val COLUMN_INFO_TWITTER_USERNAME = "twitter_username"
        const val COLUMN_INFO_PUBLIC_REPOS = "public_repos"
        const val COLUMN_INFO_PUBLIC_GISTS = "public_gists"
        const val COLUMN_INFO_FOLLOWERS = "followers"
        const val COLUMN_INFO_FOLLOWING = "following"
        const val COLUMN_INFO_CREATED_AT = "created_at"
        const val COLUMN_INFO_UPDATED_AT = "updated_at"


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
        ): UserEntity {
            return UserEntity(
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
