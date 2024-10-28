package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.embedded.Owner


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




class OwnerTypeConverter {

    @TypeConverter
    fun fromOwnerJson(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun toOwner(jsonOwner: String): Owner {
        val ownerType = object : TypeToken<Owner>() {}.type
        return Gson().fromJson<Owner>(jsonOwner, ownerType)
    }

}