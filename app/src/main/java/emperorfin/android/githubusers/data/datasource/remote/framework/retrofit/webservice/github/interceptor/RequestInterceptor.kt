package emperorfin.android.githubusers.data.datasource.remote.framework.retrofit.webservice.github.interceptor

import emperorfin.android.githubusers.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response




/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 24th October, 2024.
 */




internal class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_API_KEY}")
            .build()

        return chain.proceed(newRequest)
    }
}