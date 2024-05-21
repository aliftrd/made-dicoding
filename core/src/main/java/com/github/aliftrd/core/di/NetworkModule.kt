package com.github.aliftrd.core.di

import com.github.aliftrd.core.data.genre.remote.GenreService
import com.github.aliftrd.core.data.lib.HeaderInterceptor
import com.github.aliftrd.core.data.movie.remote.MovieService
import com.github.aliftrd.core.utils.ConstVar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        return@single OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(ConstVar.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideGenreService(get()) }
    single { providePopularService(get()) }
}

private fun getHeaderInterceptor(): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers)
}

private fun provideGenreService(retrofit: Retrofit): GenreService = retrofit.create(GenreService::class.java)
private fun providePopularService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)
