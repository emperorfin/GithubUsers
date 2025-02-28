package emperorfin.android.githubusers.domain.uilayer.event.output.repo

import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.OwnerParams
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.PermissionsParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October? 2024.
 */




interface RepoDataTransferObjectParams : Params {

    val id: Long
    val nodeId: String?
    val name: String?
    val fullName: String?
//    val private: Boolean?
    val _private: Boolean?
    val owner: OwnerParams
    val htmlUrl: String?
    val description: String?
    val fork: Boolean?
    val url: String?
    val forksUrl: String?
    val keysUrl: String?
    val collaboratorsUrl: String?
    val teamsUrl: String?
    val hooksUrl: String?
    val issueEventsUrl: String?
    val eventsUrl: String?
    val assigneesUrl: String?
    val branchesUrl: String?
    val tagsUrl: String?
    val blobsUrl: String?
    val gitTagsUrl: String?
    val gitRefsUrl: String?
    val treesUrl: String?
    val statusesUrl: String?
    val languagesUrl: String?
    val stargazersUrl: String?
    val contributorsUrl: String?
    val subscribersUrl: String?
    val subscriptionUrl: String?
    val commitsUrl: String?
    val gitCommitsUrl: String?
    val commentsUrl: String?
    val issueCommentUrl: String?
    val contentsUrl: String?
    val compareUrl: String? 
    val mergesUrl: String?
    val archiveUrl: String?
    val downloadsUrl: String?
    val issuesUrl: String?
    val pullsUrl: String?
    val milestonesUrl: String?
    val notificationsUrl: String?
    val labelsUrl: String?
    val releasesUrl: String?
    val deploymentsUrl: String?
    val createdAt: String?
    val updatedAt: String?
    val pushedAt: String?
    val gitUrl: String?
    val sshUrl: String?
    val cloneUrl: String?
    val svnUrl: String?
    val homepage: String?
    val size: Int?
    val stargazersCount: Int?
    val watchersCount: Int?
    val language: String?
    val hasIssues: Boolean?
    val hasProjects: Boolean?
    val hasDownloads: Boolean?
    val hasWiki: Boolean?
    val hasPages: Boolean?
    val hasDiscussions: Boolean?
    val forksCount: Int?
    val mirrorUrl: String?
    val archived: Boolean?
    val disabled: Boolean?
    val openIssuesCount: Int?
    val license: String?
    val allowForking: Boolean?
    val isTemplate: Boolean?
    val webCommitSignoffRequired: Boolean?
    val topics: List<String>?
    val visibility: String?
    val forks: Int?
    val openIssues: Int?
    val watchers: Int?
    val defaultBranch: String?
    val permissions: PermissionsParams?
    
}
