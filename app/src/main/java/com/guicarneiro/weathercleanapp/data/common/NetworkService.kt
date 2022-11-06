package com.guicarneiro.weathercleanapp.data.common

import com.google.gson.*
import com.google.gson.internal.bind.DefaultDateTypeAdapter
import com.guicarneiro.weathercleanapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class NetworkService {

    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(provideGson()))
            client(provideOkHttp())
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    fun provideGson() : Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create()
    }

    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(3, TimeUnit.SECONDS)
            readTimeout(3, TimeUnit.SECONDS)
            writeTimeout(3, TimeUnit.SECONDS)
        }.build()
    }

}