package emperorfin.android.githubusers.domain.uilayer.event.output.repo.embedded


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October? 2024.
 */




interface PermissionsParams {

    val admin: Boolean?
    val maintain: Boolean?
    val push: Boolean?
    val triage: Boolean?
    val pull: Boolean?

}