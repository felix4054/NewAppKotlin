package by.kavalchuk.aliaksandr.news.data.network

sealed class ResultWrapper<out T : Any> {
    class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    class Error(val exception: Exception) : ResultWrapper<Nothing>()
    class Loading<T>() : ResultWrapper<Nothing>()
}

data class ResultResource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
    ) {
    companion object {

        fun <T> success(data: T?): ResultResource<T> {
            return ResultResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResultResource<T> {
            return ResultResource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResultResource<T> {
            return ResultResource(Status.LOADING, data, null)
        }

    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
