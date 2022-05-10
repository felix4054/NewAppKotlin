package by.kavalchuk.aliaksandr.news.di

import android.app.Application
import by.kavalchuk.aliaksandr.news.BuildConfig.NewsApiKey
import by.kavalchuk.aliaksandr.news.data.network.ApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun baseUrl() = "https://newsapi.org/"

    @Provides
    @Singleton
    internal fun provideRetrofit(
        baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    internal fun provideHttpCache(app: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setDateFormat("yyyy-MM-dd")
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(12, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor{
                    chain ->
                val newRequest = chain.request().newBuilder()
                    .header("X-API-Key", NewsApiKey)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    internal fun getNews(retroFit: Retrofit): ApiService {
        return retroFit.create(ApiService::class.java)
    }


}
