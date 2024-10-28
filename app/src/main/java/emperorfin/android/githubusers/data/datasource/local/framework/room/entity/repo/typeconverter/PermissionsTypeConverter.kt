package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.embedded.Permissions


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




class PermissionsTypeConverter {

    @TypeConverter
    fun fromPermissionsJson(permissions: Permissions): String {
        return Gson().toJson(permissions)
    }

    @TypeConverter
    fun toPermissions(jsonPermissions: String): Permissions {
        val permissionsType = object : TypeToken<Permissions>() {}.type
        return Gson().fromJson<Permissions>(jsonPermissions, permissionsType)
    }

}