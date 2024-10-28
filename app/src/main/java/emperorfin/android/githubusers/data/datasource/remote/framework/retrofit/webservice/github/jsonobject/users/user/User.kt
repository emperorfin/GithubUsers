package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




open class User(
    @SerializedName("login")
    open val login: String,
    @SerializedName("id")
    open val id: Long, 
    @SerializedName("node_id")
    open val nodeId: String?,
    @SerializedName("avatar_url")
    open val avatarUrl: String?,
    @SerializedName("gravatar_id")
    open val gravatarId: String?,
    @SerializedName("url")
    open val url: String?,
    @SerializedName("html_url")
    open val htmlUrl: String?,
    @SerializedName("followers_url")
    open val followersUrl: String?,
    @SerializedName("following_url")
    open val followingUrl: String?,
    @SerializedName("gists_url")
    open val gistsUrl: String?,
    @SerializedName("starred_url")
    open val starredUrl: String?,
    @SerializedName("subscriptions_url")
    open val subscriptionsUrl: String?,
    @SerializedName("organizations_url")
    open val organizationsUrl: String?,
    @SerializedName("repos_url")
    open val reposUrl: String?,
    @SerializedName("events_url")
    open val eventsUrl: String?,
    @SerializedName("received_events_url")
    open val receivedEventsUrl: String?,
    @SerializedName("type")
    open val type: String?,
    @SerializedName("user_view_type")
    open val userViewType: String?,
    @SerializedName("site_admin")
    open val siteAdmin: Boolean?,
    @SerializedName("score")
    open val score: Double?, 
    @SerializedName("name")
    open val name: String? = null, 
    @SerializedName("company")
    open val company: String? = null, 
    @SerializedName("blog")
    open val blog: String? = null, 
    @SerializedName("location")
    open val location: String? = null, 
    @SerializedName("email")
    open val email: String? = null, 
    @SerializedName("hireable")
    open val hireable: String? = null, 
    @SerializedName("bio")
    open val bio: String? = null, 
    @SerializedName("twitter_username")
    open val twitterUsername: String? = null, 
    @SerializedName("public_repos")
    open val publicRepos: Int? = null, 
    @SerializedName("public_gists")
    open val publicGists: Int? = null, 
    @SerializedName("followers")
    open val followers: Int? = null, 
    @SerializedName("following")
    open val following: Int? = null, 
    @SerializedName("created_at")
    open val createdAt: String? = null, 
    @SerializedName("updated_at")
    open val updatedAt: String? = null, 
) : Serializable
