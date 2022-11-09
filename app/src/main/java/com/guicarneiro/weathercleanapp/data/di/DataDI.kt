package com.guicarneiro.weathercleanapp.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.guicarneiro.weathercleanapp.data.datasources.network.RemoteDataSource
import com.guicarneiro.weathercleanapp.data.datasources.network.RetrofitNetwork
import com.guicarneiro.weathercleanapp.data.repositories.WeatherRepositoriesImpl
import com.guicarneiro.weathercleanapp.domain.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataDI {
    @Provides
    fun provideGson(): GsonConverterFactory {
        return  GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create())
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface DomainModuleI {

    @Binds
    fun bindsDatasource(
        remoteDataSource: RetrofitNetwork
    ): RemoteDataSource

    @Binds
    fun bindsRepository(
        weatherRepository: WeatherRepositoriesImpl
    ): WeatherRepository
}