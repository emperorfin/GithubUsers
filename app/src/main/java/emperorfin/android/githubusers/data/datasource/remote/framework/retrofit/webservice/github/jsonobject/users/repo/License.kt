package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.repo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 29th October, 2024.
 */




data class License(
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("spdx_id")
    val spdxId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("node_id")
    val nodeId: String?,
) : Serializable
