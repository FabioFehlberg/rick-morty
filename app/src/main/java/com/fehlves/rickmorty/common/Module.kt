package com.fehlves.rickmorty.common

import com.fehlves.rickmorty.data.catalogue.CatalogueApi
import com.fehlves.rickmorty.data.catalogue.CatalogueDataStore
import com.fehlves.rickmorty.data.detail.DetailApi
import com.fehlves.rickmorty.data.detail.DetailDataStore
import com.fehlves.rickmorty.features.catalogue.CatalogueViewModel
import com.fehlves.rickmorty.features.detail.DetailViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = listOf(
    module {
        viewModel {
            CatalogueViewModel(get())
        }
    },
    module {
        viewModel {
            DetailViewModel(get())
        }
    }
)

val repositoryModule = module {
    single {
        CatalogueDataStore(get())
    }
}

val detailRepositoryModule = module {
    single {
        DetailDataStore(get())
    }
}

val apiModule = module {
    fun provideCatalogueApi(retrofit: Retrofit): CatalogueApi {
        return retrofit.create(CatalogueApi::class.java)
    }

    single {
        provideCatalogueApi(get())
    }
}

val detailApiModule = module {
    fun provideDetailApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }

    single {
        provideDetailApi(get())
    }
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