package emperorfin.android.githubusers.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emperorfin.android.githubusers.BuildConfig
import emperorfin.android.githubusers.data.datasource.local.framework.room.AppRoomDatabase
import emperorfin.android.githubusers.data.datasource.local.framework.room.entitysource.UsersLocalDataSourceRoom
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.dtosource.UsersRemoteDataSourceRetrofit
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.api.UsersApi
import emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.interceptor.RequestInterceptor
import emperorfin.android.githubusers.data.repository.UsersRepository
import emperorfin.android.githubusers.domain.datalayer.dao.IUsersDao
import emperorfin.android.githubusers.domain.datalayer.datasource.UsersDataSource
import emperorfin.android.githubusers.domain.datalayer.repository.IUsersRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 26th October, 2024.
 */




@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class UsersLocalDataSource

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class UsersRemoteDataSource

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class LocalUsersDao

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RemoteUsersDao

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    @UsersLocalDataSource
    abstract fun bindUsersLocalDataSourceRoom(dataSource: UsersLocalDataSourceRoom): UsersDataSource

    @Singleton
    @Binds
    @UsersRemoteDataSource
    abstract fun bindUsersRemoteDataSourceRetrofit(dataSource: UsersRemoteDataSourceRetrofit): UsersDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUsersRepository(repository: UsersRepository): IUsersRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppRoomDatabase {
        return AppRoomDatabase.getInstance(context)
    }

    @Provides
    @LocalUsersDao
    fun provideUsersDao(database: AppRoomDatabase): IUsersDao = database.mUsersDao
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okhHttpClient)
            .baseUrl(BuildConfig.GITHUB_BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RemoteUsersDao
    fun provideUsersApi(retrofit: Retrofit): IUsersDao {
        return retrofit.create(UsersApi::class.java)
    }
}