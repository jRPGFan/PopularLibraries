package com.example.popularlibraries.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.model.ApiHolder
import com.example.popularlibraries.model.IApiHolder
import com.example.popularlibraries.model.IDataSource
import com.example.popularlibraries.utils.AndroidNetworkStatus
import com.example.popularlibraries.utils.INetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun gson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun dataSource(gson: Gson, @Named("baseUrl") baseUrl: String): IDataSource =
        Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(IDataSource::class.java)


    @Singleton
    @Provides
    fun apiHolder(apiService: IDataSource): IApiHolder = ApiHolder(apiService)

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}