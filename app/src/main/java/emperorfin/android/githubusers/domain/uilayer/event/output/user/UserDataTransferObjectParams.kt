package emperorfin.android.githubusers.domain.uilayer.event.output.user


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




interface UserDataTransferObjectParams : Params {
    val login: String
    val id: Long
    val nodeId: String?
    val avatarUrl: String?
    val gravatarId: String?
    val url: String?
    val htmlUrl: String?
    val followersUrl: String?
    val followingUrl: String?
    val gistsUrl: String?
    val starredUrl: String?
    val subscriptionsUrl: String?
    val organizationsUrl: String?
    val reposUrl: String?
    val eventsUrl: String?
    val receivedEventsUrl: String?
    val type: String?
    val userViewType: String?
    val siteAdmin: Boolean?
    val score: Double?
    val name: String?
    val company: String?
    val blog: String?
    val location: String?
    val email: String?
    val hireable: String?
    val bio: String?
    val twitterUsername: String?
    val publicRepos: Int?
    val publicGists: Int?
    val followers: Int?
    val following: Int?
    val createdAt: String?
    val updatedAt: String?
}