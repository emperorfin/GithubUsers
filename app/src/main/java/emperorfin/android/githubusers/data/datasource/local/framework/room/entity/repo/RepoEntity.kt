package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.TABLE_NAME
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.embedded.Owner
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.embedded.Permissions
import emperorfin.android.githubusers.domain.uilayer.event.output.repo.RepoEntityParams

/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 25th October, 2024.
 */




@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [COLUMN_INFO_ID]
)
data class RepoEntity(
    @ColumnInfo(name = COLUMN_INFO_ID)
    override val id: Long,
    @ColumnInfo(name = COLUMN_INFO_NODE_ID)
    override val nodeId: String,
    @ColumnInfo(name = COLUMN_INFO_NAME)
    override val name: String,
    @ColumnInfo(name = COLUMN_INFO_FULL_NAME)
    override val fullName: String,
    @ColumnInfo(name = COLUMN_INFO_PRIVATE)
    override val _private: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_OWNER)
    override val owner: Owner,
    @ColumnInfo(name = COLUMN_INFO_HTML_URL)
    override val htmlUrl: String,
    @ColumnInfo(name = COLUMN_INFO_DESCRIPTION)
    override val description: String,
    @ColumnInfo(name = COLUMN_INFO_FORK)
    override val fork: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_URL)
    override val url: String,
    @ColumnInfo(name = COLUMN_INFO_FORKS_URL)
    override val forksUrl: String,
    @ColumnInfo(name = COLUMN_INFO_KEYS_URL)
    override val keysUrl: String,
    @ColumnInfo(name = COLUMN_INFO_COLLABORATORS_URL)
    override val collaboratorsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_TEAMS_URL)
    override val teamsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_HOOKS_URL)
    override val hooksUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ISSUE_EVENTS_URL)
    override val issueEventsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_EVENTS_URL)
    override val eventsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ASSIGNEES_URL)
    override val assigneesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_BRANCHES_URL)
    override val branchesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_TAGS_URL)
    override val tagsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_BLOBS_URL)
    override val blobsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_GIT_TAGS_URL)
    override val gitTagsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_GIT_REFS_URL)
    override val gitRefsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_TREES_URL)
    override val treesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_STATUSES_URL)
    override val statusesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_LANGUAGES_URL)
    override val languagesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_STARGAZERS_URL)
    override val stargazersUrl: String,
    @ColumnInfo(name = COLUMN_INFO_CONTRIBUTORS_URL)
    override val contributorsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_SUBSCRIBERS_URL)
    override val subscribersUrl: String,
    @ColumnInfo(name = COLUMN_INFO_SUBSCRIPTION_URL)
    override val subscriptionUrl: String,
    @ColumnInfo(name = COLUMN_INFO_COMMITS_URL)
    override val commitsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_GIT_COMMITS_URL)
    override val gitCommitsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_COMMENTS_URL)
    override val commentsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ISSUE_COMMENT_URL)
    override val issueCommentUrl: String,
    @ColumnInfo(name = COLUMN_INFO_CONTENTS_URL)
    override val contentsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_COMPARE_URL)
    override val compareUrl: String,
    @ColumnInfo(name = COLUMN_INFO_MERGES_URL)
    override val mergesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ARCHIVE_URL)
    override val archiveUrl: String,
    @ColumnInfo(name = COLUMN_INFO_DOWNLOADS_URL)
    override val downloadsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ISSUES_URL)
    override val issuesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_PULLS_URL)
    override val pullsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_MILESTONES_URL)
    override val milestonesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_NOTIFICATIONS_URL)
    override val notificationsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_LABELS_URL)
    override val labelsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_RELEASES_URL)
    override val releasesUrl: String,
    @ColumnInfo(name = COLUMN_INFO_DEPLOYMENTS_URL)
    override val deploymentsUrl: String,
    @ColumnInfo(name = COLUMN_INFO_CREATED_AT)
    override val createdAt: String,
    @ColumnInfo(name = COLUMN_INFO_UPDATED_AT)
    override val updatedAt: String,
    @ColumnInfo(name = COLUMN_INFO_PUSHED_AT)
    override val pushedAt: String,
    @ColumnInfo(name = COLUMN_INFO_GIT_URL)
    override val gitUrl: String,
    @ColumnInfo(name = COLUMN_INFO_SSH_URL)
    override val sshUrl: String,
    @ColumnInfo(name = COLUMN_INFO_CLONE_URL)
    override val cloneUrl: String,
    @ColumnInfo(name = COLUMN_INFO_SVN_URL)
    override val svnUrl: String,
    @ColumnInfo(name = COLUMN_INFO_HOMEPAGE)
    override val homepage: String,
    @ColumnInfo(name = COLUMN_INFO_SIZE)
    override val size: Int,
    @ColumnInfo(name = COLUMN_INFO_STARGAZERS_COUNT)
    override val stargazersCount: Int,
    @ColumnInfo(name = COLUMN_INFO_WATCHERS_COUNT)
    override val watchersCount: Int,
    @ColumnInfo(name = COLUMN_INFO_LANGUAGE)
    override val language: String,
    @ColumnInfo(name = COLUMN_INFO_HAS_ISSUES)
    override val hasIssues: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_HAS_PROJECTS)
    override val hasProjects: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_HAS_DOWNLOADS)
    override val hasDownloads: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_HAS_WIKI)
    override val hasWiki: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_HAS_PAGES)
    override val hasPages: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_HAS_DISCUSSIONS)
    override val hasDiscussions: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_FORKS_COUNT)
    override val forksCount: Int,
    @ColumnInfo(name = COLUMN_INFO_MIRROR_URL)
    override val mirrorUrl: String,
    @ColumnInfo(name = COLUMN_INFO_ARCHIVED)
    override val archived: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_DISABLED)
    override val disabled: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_OPEN_ISSUES_COUNT)
    override val openIssuesCount: Int,
    @ColumnInfo(name = COLUMN_INFO_LICENSE)
    override val license: String,
    @ColumnInfo(name = COLUMN_INFO_ALLOW_FORKING)
    override val allowForking: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_IS_TEMPLATE)
    override val isTemplate: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_WEB_COMMIT_SIGNOFF_REQUIRED)
    override val webCommitSignoffRequired: Boolean?,
    @ColumnInfo(name = COLUMN_INFO_TOPICS)
    override val topics: List<String>,
    @ColumnInfo(name = COLUMN_INFO_VISIBILITY)
    override val visibility: String,
    @ColumnInfo(name = COLUMN_INFO_FORKS)
    override val forks: Int,
    @ColumnInfo(name = COLUMN_INFO_OPEN_ISSUES)
    override val openIssues: Int,
    @ColumnInfo(name = COLUMN_INFO_WATCHERS)
    override val watchers: Int,
    @ColumnInfo(name = COLUMN_INFO_DEFAULT_BRANCH)
    override val defaultBranch: String,
    @ColumnInfo(name = COLUMN_INFO_PERMISSIONS)
    override val permissions: Permissions?,
    @ColumnInfo(name = COLUMN_INFO_OWNER_ID)
    val ownerId: Long = owner.id
) : RepoEntityParams {

    companion object {

        const val TABLE_NAME = "table_repos"

        const val COLUMN_INFO_ID = "id"
        const val COLUMN_INFO_OWNER_ID = "owner_id"
        const val COLUMN_INFO_NODE_ID = "node_id"
        const val COLUMN_INFO_NAME = "name"
        const val COLUMN_INFO_FULL_NAME = "full_name"
        const val COLUMN_INFO_PRIVATE = "private"
        const val COLUMN_INFO_OWNER = "owner"
        const val COLUMN_INFO_HTML_URL = "html_url"
        const val COLUMN_INFO_DESCRIPTION = "description"
        const val COLUMN_INFO_FORK = "fork"
        const val COLUMN_INFO_URL = "url"
        const val COLUMN_INFO_FORKS_URL = "forks_url"
        const val COLUMN_INFO_KEYS_URL = "keys_url"
        const val COLUMN_INFO_COLLABORATORS_URL = "collaborators_url"
        const val COLUMN_INFO_TEAMS_URL = "teams_url"
        const val COLUMN_INFO_HOOKS_URL = "hooks_url"
        const val COLUMN_INFO_ISSUE_EVENTS_URL = "issue_events_url"
        const val COLUMN_INFO_EVENTS_URL = "events_url"
        const val COLUMN_INFO_ASSIGNEES_URL = "assignees_url"
        const val COLUMN_INFO_BRANCHES_URL = "branches_url"
        const val COLUMN_INFO_TAGS_URL = "tags_url"
        const val COLUMN_INFO_BLOBS_URL = "blobs_url"
        const val COLUMN_INFO_GIT_TAGS_URL = "git_tags_url"
        const val COLUMN_INFO_GIT_REFS_URL = "git_refs_url"
        const val COLUMN_INFO_TREES_URL = "trees_url"
        const val COLUMN_INFO_STATUSES_URL = "statuses_url"
        const val COLUMN_INFO_LANGUAGES_URL = "languages_url"
        const val COLUMN_INFO_STARGAZERS_URL = "stargazers_url"
        const val COLUMN_INFO_CONTRIBUTORS_URL = "contributors_url"
        const val COLUMN_INFO_SUBSCRIBERS_URL = "subscribers_url"
        const val COLUMN_INFO_SUBSCRIPTION_URL = "subscription_url"
        const val COLUMN_INFO_COMMITS_URL = "commits_url"
        const val COLUMN_INFO_GIT_COMMITS_URL = "git_commits_url"
        const val COLUMN_INFO_COMMENTS_URL = "comments_url"
        const val COLUMN_INFO_ISSUE_COMMENT_URL = "issue_comment_url"
        const val COLUMN_INFO_CONTENTS_URL = "contents_url"
        const val COLUMN_INFO_COMPARE_URL = "compare_url"
        const val COLUMN_INFO_MERGES_URL = "merges_url"
        const val COLUMN_INFO_ARCHIVE_URL = "archive_url"
        const val COLUMN_INFO_DOWNLOADS_URL = "downloads_url"
        const val COLUMN_INFO_ISSUES_URL = "issues_url"
        const val COLUMN_INFO_PULLS_URL = "pulls_url"
        const val COLUMN_INFO_MILESTONES_URL = "milestones_url"
        const val COLUMN_INFO_NOTIFICATIONS_URL = "notifications_url"
        const val COLUMN_INFO_LABELS_URL = "labels_url"
        const val COLUMN_INFO_RELEASES_URL = "releases_url"
        const val COLUMN_INFO_DEPLOYMENTS_URL = "deployments_url"
        const val COLUMN_INFO_CREATED_AT = "created_at"
        const val COLUMN_INFO_UPDATED_AT = "updated_at"
        const val COLUMN_INFO_PUSHED_AT = "pushed_at"
        const val COLUMN_INFO_GIT_URL = "git_url"
        const val COLUMN_INFO_SSH_URL = "ssh_url"
        const val COLUMN_INFO_CLONE_URL = "clone_url"
        const val COLUMN_INFO_SVN_URL = "svn_url"
        const val COLUMN_INFO_HOMEPAGE = "homepage"
        const val COLUMN_INFO_SIZE = "size"
        const val COLUMN_INFO_STARGAZERS_COUNT = "stargazers_count"
        const val COLUMN_INFO_WATCHERS_COUNT = "watchers_count"
        const val COLUMN_INFO_LANGUAGE = "language"
        const val COLUMN_INFO_HAS_ISSUES = "has_issues"
        const val COLUMN_INFO_HAS_PROJECTS = "has_projects"
        const val COLUMN_INFO_HAS_DOWNLOADS = "has_downloads"
        const val COLUMN_INFO_HAS_WIKI = "has_wiki"
        const val COLUMN_INFO_HAS_PAGES = "has_pages"
        const val COLUMN_INFO_HAS_DISCUSSIONS = "has_discussions"
        const val COLUMN_INFO_FORKS_COUNT = "forks_count"
        const val COLUMN_INFO_MIRROR_URL = "mirror_url"
        const val COLUMN_INFO_ARCHIVED = "archived"
        const val COLUMN_INFO_DISABLED = "disabled"
        const val COLUMN_INFO_OPEN_ISSUES_COUNT = "open_issues_count"
        const val COLUMN_INFO_LICENSE = "license"
        const val COLUMN_INFO_ALLOW_FORKING = "allow_forking"
        const val COLUMN_INFO_IS_TEMPLATE = "is_template"
        const val COLUMN_INFO_WEB_COMMIT_SIGNOFF_REQUIRED = "web_commit_signoff_required"
        const val COLUMN_INFO_TOPICS = "topics"
        const val COLUMN_INFO_VISIBILITY = "visibility"
        const val COLUMN_INFO_FORKS = "forks"
        const val COLUMN_INFO_OPEN_ISSUES = "open_issues"
        const val COLUMN_INFO_WATCHERS = "watchers"
        const val COLUMN_INFO_DEFAULT_BRANCH = "default_branch"
        const val COLUMN_INFO_PERMISSIONS = "permissions"

        fun newInstance(
            id: Long,
            nodeId: String,
            name: String,
            fullName: String,
            private: Boolean?,
            owner: Owner,
            htmlUrl: String,
            description: String,
            fork: Boolean?,
            url: String,
            forksUrl: String,
            keysUrl: String,
            collaboratorsUrl: String,
            teamsUrl: String,
            hooksUrl: String,
            issueEventsUrl: String,
            eventsUrl: String,
            assigneesUrl: String,
            branchesUrl: String,
            tagsUrl: String,
            blobsUrl: String,
            gitTagsUrl: String,
            gitRefsUrl: String,
            treesUrl: String,
            statusesUrl: String,
            languagesUrl: String,
            stargazersUrl: String,
            contributorsUrl: String,
            subscribersUrl: String,
            subscriptionUrl: String,
            commitsUrl: String,
            gitCommitsUrl: String,
            commentsUrl: String,
            issueCommentUrl: String,
            contentsUrl: String,
            compareUrl: String,
            mergesUrl: String,
            archiveUrl: String,
            downloadsUrl: String,
            issuesUrl: String,
            pullsUrl: String,
            milestonesUrl: String,
            notificationsUrl: String,
            labelsUrl: String,
            releasesUrl: String,
            deploymentsUrl: String,
            createdAt: String,
            updatedAt: String,
            pushedAt: String,
            gitUrl: String,
            sshUrl: String,
            cloneUrl: String,
            svnUrl: String,
            homepage: String,
            size: Int,
            stargazersCount: Int,
            watchersCount: Int,
            language: String,
            hasIssues: Boolean?,
            hasProjects: Boolean?,
            hasDownloads: Boolean?,
            hasWiki: Boolean?,
            hasPages: Boolean?,
            hasDiscussions: Boolean?,
            forksCount: Int,
            mirrorUrl: String,
            archived: Boolean?,
            disabled: Boolean?,
            openIssuesCount: Int,
            license: String,
            allowForking: Boolean?,
            isTemplate: Boolean?,
            webCommitSignoffRequired: Boolean?,
            topics: List<String>,
            visibility: String,
            forks: Int,
            openIssues: Int,
            watchers: Int,
            defaultBranch: String,
            permissions: Permissions?
        ): RepoEntity {
            return RepoEntity(
                id = id,
                nodeId = nodeId,
                name = name,
                fullName = fullName,
                _private = private,
                owner = owner,
                htmlUrl = htmlUrl,
                description = description,
                fork = fork,
                url = url,
                forksUrl = forksUrl,
                keysUrl = keysUrl,
                collaboratorsUrl = collaboratorsUrl,
                teamsUrl = teamsUrl,
                hooksUrl = hooksUrl,
                issueEventsUrl = issueEventsUrl,
                eventsUrl = eventsUrl,
                assigneesUrl = assigneesUrl,
                branchesUrl = branchesUrl,
                tagsUrl = tagsUrl,
                blobsUrl = blobsUrl,
                gitTagsUrl = gitTagsUrl,
                gitRefsUrl = gitRefsUrl,
                treesUrl = treesUrl,
                statusesUrl = statusesUrl,
                languagesUrl = languagesUrl,
                stargazersUrl = stargazersUrl,
                contributorsUrl = contributorsUrl,
                subscribersUrl = subscribersUrl,
                subscriptionUrl = subscriptionUrl,
                commitsUrl = commitsUrl,
                gitCommitsUrl = gitCommitsUrl,
                commentsUrl = commentsUrl,
                issueCommentUrl = issueCommentUrl,
                contentsUrl = contentsUrl,
                compareUrl = compareUrl,
                mergesUrl = mergesUrl,
                archiveUrl = archiveUrl,
                downloadsUrl = downloadsUrl,
                issuesUrl = issuesUrl,
                pullsUrl = pullsUrl,
                milestonesUrl = milestonesUrl,
                notificationsUrl = notificationsUrl,
                labelsUrl = labelsUrl,
                releasesUrl = releasesUrl,
                deploymentsUrl = deploymentsUrl,
                createdAt = createdAt,
                updatedAt = updatedAt,
                pushedAt = pushedAt,
                gitUrl = gitUrl,
                sshUrl = sshUrl,
                cloneUrl = cloneUrl,
                svnUrl = svnUrl,
                homepage = homepage,
                size = size,
                stargazersCount = stargazersCount,
                watchersCount = watchersCount,
                language = language,
                hasIssues = hasIssues,
                hasProjects = hasProjects,
                hasDownloads = hasDownloads,
                hasWiki = hasWiki,
                hasPages = hasPages,
                hasDiscussions = hasDiscussions,
                forksCount = forksCount,
                mirrorUrl = mirrorUrl,
                archived = archived,
                disabled = disabled,
                openIssuesCount = openIssuesCount,
                license = license,
                allowForking = allowForking,
                isTemplate = isTemplate,
                webCommitSignoffRequired = webCommitSignoffRequired,
                topics = topics,
                visibility = visibility,
                forks = forks,
                openIssues = openIssues,
                watchers = watchers,
                defaultBranch = defaultBranch,
                permissions = permissions
            )
        }

    }

}
