package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




data class Permissions(
    @SerializedName("admin")
    val admin: Boolean?,
    @SerializedName("maintain")
    val maintain: Boolean?,
    @SerializedName("push")
    val push: Boolean?,
    @SerializedName("triage")
    val triage: Boolean?,
    @SerializedName("pull")
    val pull: Boolean?,
) : Serializable
