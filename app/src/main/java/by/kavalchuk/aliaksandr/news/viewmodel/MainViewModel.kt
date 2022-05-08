package by.kavalchuk.aliaksandr.news.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
//private val repository: ApiRepository
@HiltViewModel
class MainViewModel
@Inject constructor(

): ViewModel() {
//
//    private val _allMovies = MutableLiveData<List<Movies>>()
//    val allMovies: LiveData<List<Movies>>
//        get() = _allMovies
//
//    fun getAllMovies() {
//        viewModelScope.launch {
//            repository.getAllMovies().let {
//                if (it.isSuccessful) {
//                    _allMovies.postValue(it.body())
//                } else {
//                    Log.d("checkData", "Failed to load movies: ${it.errorBody()}")
//                }
//            }
//        }
//    }
}