package by.kavalchuk.aliaksandr.news.data.network

sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Error(val exception: Exception) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}