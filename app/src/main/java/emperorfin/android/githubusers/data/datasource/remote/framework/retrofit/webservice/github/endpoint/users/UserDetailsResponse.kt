package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users

import com.google.gson.annotations.SerializedName
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.user.User
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class UserDetailsResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user_view_type")
    val userViewType: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("hireable")
    val hireable: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    @SerializedName("followers")
    val followers: Int? = null,
    @SerializedName("following")
    val following: Int? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
) : Serializable
