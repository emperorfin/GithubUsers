package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class Repo(
    @SerializedName("id")
    val id: Long, 
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("private")
    val private: Boolean?,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("fork")
    val fork: Boolean?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("forks_url")
    val forksUrl: String?,
    @SerializedName("keys_url")
    val keysUrl: String?,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String?,
    @SerializedName("teams_url")
    val teamsUrl: String?,
    @SerializedName("hooks_url")
    val hooksUrl: String?,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("assignees_url")
    val assigneesUrl: String?,
    @SerializedName("branches_url")
    val branchesUrl: String?,
    @SerializedName("tags_url")
    val tagsUrl: String?,
    @SerializedName("blobs_url")
    val blobsUrl: String?,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String?,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String?,
    @SerializedName("trees_url")
    val treesUrl: String?,
    @SerializedName("statuses_url")
    val statusesUrl: String?,
    @SerializedName("languages_url")
    val languagesUrl: String?,
    @SerializedName("stargazers_url")
    val stargazersUrl: String?,
    @SerializedName("contributors_url")
    val contributorsUrl: String?,
    @SerializedName("subscribers_url")
    val subscribersUrl: String?,
    @SerializedName("subscription_url")
    val subscriptionUrl: String?,
    @SerializedName("commits_url")
    val commitsUrl: String?,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String?,
    @SerializedName("comments_url")
    val commentsUrl: String?,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String?,
    @SerializedName("contents_url")
    val contentsUrl: String?,
    @SerializedName("compare_url")
    val compareUrl: String?,
    @SerializedName("merges_url")
    val mergesUrl: String?,
    @SerializedName("archive_url")
    val archiveUrl: String?,
    @SerializedName("downloads_url")
    val downloadsUrl: String?,
    @SerializedName("issues_url")
    val issuesUrl: String?,
    @SerializedName("pulls_url")
    val pullsUrl: String?,
    @SerializedName("milestones_url")
    val milestonesUrl: String?,
    @SerializedName("notifications_url")
    val notificationsUrl: String?,
    @SerializedName("labels_url")
    val labelsUrl: String?,
    @SerializedName("releases_url")
    val releasesUrl: String?,
    @SerializedName("deployments_url")
    val deploymentsUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("pushed_at")
    val pushedAt: String?,
    @SerializedName("git_url")
    val gitUrl: String?,
    @SerializedName("ssh_url")
    val sshUrl: String?,
    @SerializedName("clone_url")
    val cloneUrl: String?,
    @SerializedName("svn_url")
    val svnUrl: String?,
    @SerializedName("homepage")
    val homepage: String?, 
    @SerializedName("size")
    val size: Int?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("watchers_count")
    val watchersCount: Int?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("has_issues")
    val hasIssues: Boolean?,
    @SerializedName("has_projects")
    val hasProjects: Boolean?,
    @SerializedName("has_downloads")
    val hasDownloads: Boolean?,
    @SerializedName("has_wiki")
    val hasWiki: Boolean?,
    @SerializedName("has_pages")
    val hasPages: Boolean?,
    @SerializedName("has_discussions")
    val hasDiscussions: Boolean?,
    @SerializedName("forks_count")
    val forksCount: Int?,
    @SerializedName("mirror_url")
    val mirrorUrl: String?, 
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("disabled")
    val disabled: Boolean?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int?,
    @SerializedName("license")
    val license: License?,
    @SerializedName("allow_forking")
    val allowForking: Boolean?,
    @SerializedName("is_template")
    val isTemplate: Boolean?,
    @SerializedName("web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean?,
    @SerializedName("topics")
    val topics: List<String>?,
    @SerializedName("visibility")
    val visibility: String?,
    @SerializedName("forks")
    val forks: Int?,
    @SerializedName("open_issues")
    val openIssues: Int?,
    @SerializedName("watchers")
    val watchers: Int?,
    @SerializedName("default_branch")
    val defaultBranch: String?,
    @SerializedName("permissions")
    val permissions: Permissions?,
) : Serializable
