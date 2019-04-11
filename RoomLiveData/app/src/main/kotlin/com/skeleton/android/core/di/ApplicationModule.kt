package com.skeleton.android.core.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.skeleton.android.AndroidApplication
import com.skeleton.android.BuildConfig
import com.skeleton.android.features.people.PeopleRepository
import com.skeleton.android.features.word.WordRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {



    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun implementationFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideWordRepository(dataSource: WordRepository.Network): WordRepository = dataSource

    @Provides
    @Singleton
    fun providePeopleRepository(dataSource: PeopleRepository.Network): PeopleRepository = dataSource
}
