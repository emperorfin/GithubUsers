package emperorfin.android.githubusers.domain.uilayer.event.input.repo

import emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded.Owner
import emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded.Permissions
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.RepoModelParams
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.OwnerParams
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.PermissionsParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class RepoParams(
    override val id: Long,
    override val nodeId: String? = null,
    override val name: String? = null,
    override val fullName: String? = null,
    override val _private: Boolean? = null,
    override val owner: Owner,
    override val htmlUrl: String? = null,
    override val description: String? = null,
    override val fork: Boolean? = null,
    override val url: String? = null,
    override val forksUrl: String? = null,
    override val keysUrl: String? = null,
    override val collaboratorsUrl: String? = null,
    override val teamsUrl: String? = null,
    override val hooksUrl: String? = null,
    override val issueEventsUrl: String? = null,
    override val eventsUrl: String? = null,
    override val assigneesUrl: String? = null,
    override val branchesUrl: String? = null,
    override val tagsUrl: String? = null,
    override val blobsUrl: String? = null,
    override val gitTagsUrl: String? = null,
    override val gitRefsUrl: String? = null,
    override val treesUrl: String? = null,
    override val statusesUrl: String? = null,
    override val languagesUrl: String? = null,
    override val stargazersUrl: String? = null,
    override val contributorsUrl: String? = null,
    override val subscribersUrl: String? = null,
    override val subscriptionUrl: String? = null,
    override val commitsUrl: String? = null,
    override val gitCommitsUrl: String? = null,
    override val commentsUrl: String? = null,
    override val issueCommentUrl: String? = null,
    override val contentsUrl: String? = null,
    override val compareUrl: String? = null,
    override val mergesUrl: String? = null,
    override val archiveUrl: String? = null,
    override val downloadsUrl: String? = null,
    override val issuesUrl: String? = null,
    override val pullsUrl: String? = null,
    override val milestonesUrl: String? = null,
    override val notificationsUrl: String? = null,
    override val labelsUrl: String? = null,
    override val releasesUrl: String? = null,
    override val deploymentsUrl: String? = null,
    override val createdAt: String? = null,
    override val updatedAt: String? = null,
    override val pushedAt: String? = null,
    override val gitUrl: String? = null,
    override val sshUrl: String? = null,
    override val cloneUrl: String? = null,
    override val svnUrl: String? = null,
    override val homepage: String? = null,
    override val size: Int? = null,
    override val stargazersCount: Int? = null,
    override val watchersCount: Int? = null,
    override val language: String? = null,
    override val hasIssues: Boolean? = null,
    override val hasProjects: Boolean? = null,
    override val hasDownloads: Boolean? = null,
    override val hasWiki: Boolean? = null,
    override val hasPages: Boolean? = null,
    override val hasDiscussions: Boolean? = null,
    override val forksCount: Int? = null,
    override val mirrorUrl: String? = null,
    override val archived: Boolean? = null,
    override val disabled: Boolean? = null,
    override val openIssuesCount: Int? = null,
    override val license: String? = null,
    override val allowForking: Boolean? = null,
    override val isTemplate: Boolean? = null,
    override val webCommitSignoffRequired: Boolean? = null,
    override val topics: List<String>? = null,
    override val visibility: String? = null,
    override val forks: Int? = null,
    override val openIssues: Int? = null,
    override val watchers: Int? = null,
    override val defaultBranch: String? = null,
    override val permissions: Permissions? = null
) : RepoModelParams
