package emperorfin.android.githubusers.domain.exception

import androidx.annotation.StringRes
import emperorfin.android.githubusers.R
import emperorfin.android.githubusers.domain.exception.Failure.FeatureFailure


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




sealed class RepoFailure(
    @StringRes open val message: Int,
    open val cause: Throwable?
) : FeatureFailure() {

    class RepoListNotAvailableMemoryError(
        @StringRes override val message: Int = R.string.error_memory_repo_list_not_available,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class RepoListNotAvailableLocalError(
        @StringRes override val message: Int = R.string.error_local_repo_list_not_available,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class RepoListNotAvailableRemoteError(
        @StringRes override val message: Int = R.string.error_remote_repo_list_not_available,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class RepoMemoryError(
        @StringRes override val message: Int = R.string.error_memory_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class RepoLocalError(
        @StringRes override val message: Int = R.string.error_local_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class RepoRemoteError(
        @StringRes override val message: Int = R.string.error_remote_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class GetRepoMemoryError(
        @StringRes override val message: Int = R.string.error_memory_get_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class GetRepoLocalError(
        @StringRes override val message: Int = R.string.error_local_get_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class GetRepoRemoteError(
        @StringRes override val message: Int = R.string.error_remote_get_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class InsertRepoMemoryError(
        @StringRes override val message: Int = R.string.error_memory_insert_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class InsertRepoLocalError(
        @StringRes override val message: Int = R.string.error_local_insert_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class InsertRepoRemoteError(
        @StringRes override val message: Int = R.string.error_remote_insert_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class UpdateRepoMemoryError(
        @StringRes override val message: Int = R.string.error_memory_update_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class UpdateRepoLocalError(
        @StringRes override val message: Int = R.string.error_local_update_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class UpdateRepoRemoteError(
        @StringRes override val message: Int = R.string.error_remote_update_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class DeleteRepoMemoryError(
        @StringRes override val message: Int = R.string.error_memory_delete_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class DeleteRepoLocalError(
        @StringRes override val message: Int = R.string.error_local_delete_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class DeleteRepoRemoteError(
        @StringRes override val message: Int = R.string.error_remote_delete_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class NonExistentRepoDataMemoryError(
        @StringRes override val message: Int = R.string.error_memory_non_existent_repo_data,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class NonExistentRepoDataLocalError(
        @StringRes override val message: Int = R.string.error_local_non_existent_repo_data,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class NonExistentRepoDataRemoteError(
        @StringRes override val message: Int = R.string.error_remote_non_existent_repo_data,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    // For Repositories
    class GetRepoRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_get_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class InsertRepoRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_insert_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)

    class DeleteRepoRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_delete_repo,
        override val cause: Throwable? = null
    ) : RepoFailure(message = message, cause = cause)
}
