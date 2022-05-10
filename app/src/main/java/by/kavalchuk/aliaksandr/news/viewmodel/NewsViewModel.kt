package by.kavalchuk.aliaksandr.news.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.kavalchuk.aliaksandr.news.data.network.ResultWrapper
import by.kavalchuk.aliaksandr.news.data.model.*
import by.kavalchuk.aliaksandr.news.data.network.NewsRepository
import by.kavalchuk.aliaksandr.news.data.network.ResultResource
import by.kavalchuk.aliaksandr.news.data.room.dao.ArticleDao
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val recentNewsMutableData: MutableLiveData<List<Article>> = MutableLiveData()
    val recentNewsLiveData: LiveData<List<Article>>
        get() = recentNewsMutableData

    private val newsLocalityLiveLocal = MutableLiveData<NewsRoom>()
    val newsRoomLocal: LiveData<NewsRoom>
        get() = newsLocalityLiveLocal
//
//
//    private var newsResponse : MutableLiveData<ResultResource<NewsResponse>>()
//
//
//
//    fun getAllTopHeadLines() {
//        viewModelScope.launch {
//            newsResponse.value = ResultResource.loading(null)
//            try {
//                val data = repository.getAllTopHeadLines()
//                newsResponse.value = ResultResource.success(data)
//            }
//            catch (error: Exception) {
//                newsResponse.value = ResultResource.error(
//                    error.localizedMessage ?: "An error has occurred !",
//                    null)
//            }
//        }
//    }

    fun getLocalizedNews(countryCode: String) {
        viewModelScope.launch {
            when (val resultResponse = repository.getNewsHeadLines(countryCode)) {
                is ResultWrapper.Success -> {
                    val latestNews = resultResponse.data
                    recentNewsMutableData.postValue(latestNews.articles!!)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
                else -> {}
            }
        }
    }

//    fun getAppLocalityLocal() {
//        viewModelScope.launch {
//            val newLocalityLocal = newsLocalityDao.getAppLocality()
//            Timber.e("NEWS LOCALITY LOCAL ${Gson().toJson(newLocalityLocal)}")
//            newsLocalityLiveLocal.postValue(newLocalityLocal)
//        }
//    }
//
//    fun saveBookmark(article: Article) {
//        viewModelScope.launch {
//            articleDao.insertArticle(article)
//        }
//    }

//    var networkStatus = false
//    var backOnline = false
//
//    @ApplicationContext
//    fun showNetworkStatus() {
//        if (!networkStatus) {
//            Toast.makeText(
//                getApplication(),
//                "Not Internet",
//                Toast.LENGTH_SHORT
//            ).show()
////            saveBackOnline(true)
//        } else if (networkStatus) {
//            if (backOnline) {
//                Toast.makeText(
//                    getApplication(),
//                    "back_online_message",
//                    Toast.LENGTH_SHORT
//                ).show()
////                saveBackOnline(false)
//            }
//        }
//    }
}
