package by.kavalchuk.aliaksandr.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.kavalchuk.aliaksandr.news.data.model.Article
import by.kavalchuk.aliaksandr.news.data.model.NewsRoom
import by.kavalchuk.aliaksandr.news.data.network.NewsRepository
import by.kavalchuk.aliaksandr.news.data.network.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsCategoriesViewModel
@Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val recentNewsMutableData: MutableLiveData<List<Article>> = MutableLiveData()
    val recentNewsLiveData: LiveData<List<Article>>
        get() = recentNewsMutableData

    private val newsLocalityLiveLocal = MutableLiveData<NewsRoom>()
    val newsRoomLocal: LiveData<NewsRoom>
        get() = newsLocalityLiveLocal


    fun getLocalizedNews(countryCode: String, category: String) {
        viewModelScope.launch {
            when (val resultResponse =
                repository.getCategorizedNewsHeadLines(countryCode, category)) {
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

//    fun getAppLocalityLocal(){
//        viewModelScope.launch {
//            val newLocalityLocal=newsLocalityDao.getAppLocality()
//            Timber.e("NEWS LOCALITY LOCAL ${Gson().toJson(newLocalityLocal)}")
//            newsLocalityLiveLocal.postValue(newLocalityLocal)
//        }
//    }
//
//    fun saveBookmark(article: Article){
//        viewModelScope.launch {
//            articleDao.insertArticle(article)
//        }
//    }
}