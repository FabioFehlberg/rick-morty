package com.fehlves.rickmorty.common

import com.fehlves.rickmorty.catalogue.CatalogueViewModel
import com.fehlves.rickmorty.data.catalogue.CatalogueApi
import com.fehlves.rickmorty.data.catalogue.CatalogueDataStore
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        CatalogueViewModel(get())
    }
}

val repositoryModule = module {
    single {
        CatalogueDataStore(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): CatalogueApi {
        return retrofit.create(CatalogueApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}