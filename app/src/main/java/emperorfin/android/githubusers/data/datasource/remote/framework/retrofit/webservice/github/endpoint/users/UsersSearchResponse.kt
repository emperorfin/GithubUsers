package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.endpoint.users

import com.google.gson.annotations.SerializedName
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.jsonobject.users.user.User
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */





data class UsersSearchResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<User>
) : Serializable
