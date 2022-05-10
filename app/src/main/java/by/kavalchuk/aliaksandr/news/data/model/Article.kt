package by.kavalchuk.aliaksandr.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "table_room_article")
data class Article(
    @SerializedName("author")
    var author: String? = "",
    @SerializedName("content")
    var content: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @PrimaryKey
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("source")
    var source: Source? = null,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = "",
    var isBookmark:Boolean = false
)