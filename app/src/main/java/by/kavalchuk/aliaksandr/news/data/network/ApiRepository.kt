package by.kavalchuk.aliaksandr.news.data.network

import by.kavalchuk.aliaksandr.news.data.model.NewsRoom
import by.kavalchuk.aliaksandr.news.data.model.NewsResponse

interface ApiRepository {

    suspend fun getNewsHeadLines(
        countryCode: String
    ): ResultWrapper<NewsResponse>

    suspend fun getCategorizedNewsHeadLines(
        countryCode:String,category:String
    ):ResultWrapper<NewsResponse>

    suspend fun  getApiNewsCountries(
    ):ResultWrapper<List<NewsRoom>>
}
