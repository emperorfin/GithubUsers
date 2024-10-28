package emperorfin.android.githubusers.domain.uilayer.event.input.repo.embedded

import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.PermissionsParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 28th October, 2024.
 */




data class Permissions(
    override val admin: Boolean? = null,
    override val maintain: Boolean? = null,
    override val push: Boolean? = null,
    override val triage: Boolean? = null,
    override val pull: Boolean? = null
) : PermissionsParams
