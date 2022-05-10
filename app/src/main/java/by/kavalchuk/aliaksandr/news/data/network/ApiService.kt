package by.kavalchuk.aliaksandr.news.data.network

import by.kavalchuk.aliaksandr.news.data.model.NewsRoom
import by.kavalchuk.aliaksandr.news.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


//Key=8372d71c2abe4cb4a823c35e608a575a
//v2/top-headlines?country=ru&apiKey=8372d71c2abe4cb4a823c35e608a575a
interface ApiService {
    @GET("/v2/top-headlines")
    suspend fun loadHeadlines(
        @Query("country") countryCode: String
    ): Response<NewsResponse>

    @GET("/v2/top-headlines")
    suspend fun loadCategoryHeadlines(
        @Query("country") countryCode: String,
        @Query("category") category: String
    ): Response<NewsResponse>

    @GET("/v2/sources")
    suspend fun getSourceBased(): Response<NewsResponse>

    @GET
    suspend fun getNewsApiCountries(
        @Url url: String
    ): Response<List<NewsRoom>>

}