package by.kavalchuk.aliaksandr.news.data.network

import androidx.multidex.BuildConfig
import by.kavalchuk.aliaksandr.news.BuildConfig.NewsApiKey
import by.kavalchuk.aliaksandr.news.data.model.NewsRoom
import by.kavalchuk.aliaksandr.news.data.model.NewsResponse
import com.google.gson.Gson
import timber.log.Timber
import javax.inject.Inject


@Suppress("UNREACHABLE_CODE")
class NewsRepository @Inject constructor(private val apiService: ApiService) : ApiRepository {

    override suspend fun getNewsHeadLines(
        countryCode: String
    ): ResultWrapper<NewsResponse> {

        return try {
            val dataResponse = apiService.loadHeadlines(countryCode)
            var newsResponse: NewsResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getCategorizedNewsHeadLines(
        countryCode: String,
        category: String
    ): ResultWrapper<NewsResponse> {
        return try {
            val dataResponse = apiService.loadCategoryHeadlines(countryCode, category)
            var newsResponse: NewsResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getApiNewsCountries(): ResultWrapper<List<NewsRoom>> {
        return try {
            val countriesResponse =
                apiService.getNewsApiCountries("ru&apiKey=${NewsApiKey}")
            val countriesList: MutableList<NewsRoom> = mutableListOf()
            when {
                countriesResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(countriesResponse.body()!!.size)}")
                    countriesList.addAll(countriesResponse.body()!!)
                }
                else -> {
                    Timber.e("Error RESP ${Gson().toJson(countriesResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(countriesList)
        } catch (exception: Exception) {
            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }
}