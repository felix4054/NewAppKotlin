package by.kavalchuk.aliaksandr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.kavalchuk.aliaksandr.news.data.model.NewsRoom

@Dao
interface NewsRoomDao {

    @Query("SELECT * FROM `table_room_news`")
    suspend fun getAppLocality(): NewsRoom

    @Insert
    suspend fun insertNewsLocality(newsLocality: NewsRoom)


}