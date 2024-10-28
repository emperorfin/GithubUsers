package emperorfin.android.githubusers.ui.model.repo.embedded

import emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded.PermissionsParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 25th October, 2024.
 */




data class Permissions(
    override val admin: Boolean?,
    override val maintain: Boolean?,
    override val push: Boolean?,
    override val triage: Boolean?,
    override val pull: Boolean?
) : PermissionsParams
