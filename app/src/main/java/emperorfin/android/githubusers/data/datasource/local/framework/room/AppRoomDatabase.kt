package emperorfin.android.githubusers.data.datasource.local.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import emperorfin.android.githubusers.data.datasource.local.framework.room.dao.UsersDao
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.RepoEntity
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter.OwnerTypeConverter
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter.PermissionsTypeConverter
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.repo.typeconverter.TopicsTypeConverter
import emperorfin.android.githubusers.data.datasource.local.framework.room.entity.user.UserEntity


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




@Database(entities = [UserEntity::class, RepoEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [(OwnerTypeConverter::class), (PermissionsTypeConverter::class), (TopicsTypeConverter::class)])
abstract class AppRoomDatabase : RoomDatabase() {

    abstract val mUsersDao: UsersDao

    companion object {

        private const val DATABASE_NAME = "database_app"

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase{

            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }

        }

    }
}