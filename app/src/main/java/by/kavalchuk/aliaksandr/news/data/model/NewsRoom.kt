package by.kavalchuk.aliaksandr.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_room_news")
data class NewsRoom(
    @PrimaryKey
    @SerializedName("countryCode")
    var countryCode: String,
    @SerializedName("countryName")
    var countryName: String?
)