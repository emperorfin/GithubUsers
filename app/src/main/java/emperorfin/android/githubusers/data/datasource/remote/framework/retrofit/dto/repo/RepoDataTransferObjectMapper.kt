package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo

import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo.embedded.Owner
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dto.repo.embedded.Permissions
import emperorfin.android.githubusers.domain.model.repo.RepoModel
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 25th October, 2024.
 */




class RepoDataTransferObjectMapper @Inject constructor() {

    fun transform(repo: RepoModel): RepoDataTransferObject {

        val id: Long = repo.id
        val nodeId: String = repo.nodeId
        val name: String = repo.name
        val fullName: String = repo.fullName
        val private: Boolean? = repo._private
        val htmlUrl: String = repo.htmlUrl
        val description: String = repo.description
        val fork: Boolean? = repo.fork
        val url: String = repo.url
        val forksUrl: String = repo.forksUrl
        val keysUrl: String = repo.keysUrl
        val collaboratorsUrl: String = repo.collaboratorsUrl
        val teamsUrl: String = repo.teamsUrl
        val hooksUrl: String = repo.hooksUrl
        val issueEventsUrl: String = repo.issueEventsUrl
        val eventsUrl: String = repo.eventsUrl
        val assigneesUrl: String = repo.assigneesUrl
        val branchesUrl: String = repo.branchesUrl
        val tagsUrl: String = repo.tagsUrl
        val blobsUrl: String = repo.blobsUrl
        val gitTagsUrl: String = repo.gitTagsUrl
        val gitRefsUrl: String = repo.gitRefsUrl
        val treesUrl: String = repo.treesUrl
        val statusesUrl: String = repo.statusesUrl
        val languagesUrl: String = repo.languagesUrl
        val stargazersUrl: String = repo.stargazersUrl
        val contributorsUrl: String = repo.contributorsUrl
        val subscribersUrl: String = repo.subscribersUrl
        val subscriptionUrl: String = repo.subscriptionUrl
        val commitsUrl: String = repo.commitsUrl
        val gitCommitsUrl: String = repo.gitCommitsUrl
        val commentsUrl: String = repo.commentsUrl
        val issueCommentUrl: String = repo.issueCommentUrl
        val contentsUrl: String = repo.contentsUrl
        val compareUrl: String = repo.compareUrl
        val mergesUrl: String = repo.mergesUrl
        val archiveUrl: String = repo.archiveUrl
        val downloadsUrl: String = repo.downloadsUrl
        val issuesUrl: String = repo.issuesUrl
        val pullsUrl: String = repo.pullsUrl
        val milestonesUrl: String = repo.milestonesUrl
        val notificationsUrl: String = repo.notificationsUrl
        val labelsUrl: String = repo.labelsUrl
        val releasesUrl: String = repo.releasesUrl
        val deploymentsUrl: String = repo.deploymentsUrl
        val createdAt: String = repo.createdAt
        val updatedAt: String = repo.updatedAt
        val pushedAt: String = repo.pushedAt
        val gitUrl: String = repo.gitUrl
        val sshUrl: String = repo.sshUrl
        val cloneUrl: String = repo.cloneUrl
        val svnUrl: String = repo.svnUrl
        val homepage: String = repo.homepage
        val size: Int = repo.size
        val stargazersCount: Int = repo.stargazersCount
        val watchersCount: Int = repo.watchersCount
        val language: String = repo.language
        val hasIssues: Boolean? = repo.hasIssues
        val hasProjects: Boolean? = repo.hasProjects
        val hasDownloads: Boolean? = repo.hasDownloads
        val hasWiki: Boolean? = repo.hasWiki
        val hasPages: Boolean? = repo.hasPages
        val hasDiscussions: Boolean? = repo.hasDiscussions
        val forksCount: Int = repo.forksCount
        val mirrorUrl: String = repo.mirrorUrl
        val archived: Boolean? = repo.archived
        val disabled: Boolean? = repo.disabled
        val openIssuesCount: Int = repo.openIssuesCount
        val license: String = repo.license
        val allowForking: Boolean? = repo.allowForking
        val isTemplate: Boolean? = repo.isTemplate
        val webCommitSignoffRequired: Boolean? = repo.webCommitSignoffRequired
        val topics: List<String> = repo.topics
        val visibility: String = repo.visibility
        val forks: Int = repo.forks
        val openIssues: Int = repo.openIssues
        val watchers: Int = repo.watchers
        val defaultBranch: String = repo.defaultBranch

        val owner: Owner

        repo.owner.let {
            owner = Owner(
                login = it.login,
                id = it.id,
                nodeId = it.nodeId,
                avatarUrl = it.avatarUrl,
                gravatarId = it.gravatarId,
                url = it.url,
                htmlUrl = it.htmlUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                gistsUrl = it.gistsUrl,
                starredUrl = it.starredUrl,
                subscriptionsUrl = it.subscriptionsUrl,
                organizationsUrl = it.organizationsUrl,
                reposUrl = it.reposUrl,
                eventsUrl = it.eventsUrl,
                receivedEventsUrl = it.receivedEventsUrl,
                type = it.type,
                userViewType = it.userViewType,
                siteAdmin = it.siteAdmin,
            )
        }

        var permissions: Permissions? = null

        repo.permissions?.let {
            permissions = Permissions(
                admin = it.admin,
                maintain = it.maintain,
                push = it.push,
                triage = it.triage,
                pull = it.pull
            )
        }

        val ownerNew: Owner = owner
        val permissionsNew: Permissions? = permissions

        return RepoDataTransferObject.newInstance(
            id = id,
            nodeId = nodeId,
            name = name,
            fullName = fullName,
            private = private,
            owner = ownerNew,
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
            permissions = permissionsNew
        )
    }

}