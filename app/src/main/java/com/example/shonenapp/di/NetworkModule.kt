package com.example.shonenapp.di

import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.remote.ShonenApiService
import com.example.shonenapp.data.repository.RemoteDataSourceImpl
import com.example.shonenapp.domain.respository.RemoteDataSource
import com.example.shonenapp.utils.Constant.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES).build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val mediaType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(mediaType))
            .baseUrl(BASE_URL).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ShonenApiService =
        retrofit.create(ShonenApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSouce(
        shonenApiService: ShonenApiService,
        shonenDataBase: ShonenDataBase
    ): RemoteDataSource = RemoteDataSourceImpl(shonenApiService, shonenDataBase)


}