package emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




class TopicsTypeConverter {

    @TypeConverter
    fun fromTopicsJson(topics: List<String>): String {
        return Gson().toJson(topics)
    }

    @TypeConverter
    fun toTopicsList(jsonTopics: String): List<String> {
        val topicsType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(jsonTopics, topicsType)
    }

}