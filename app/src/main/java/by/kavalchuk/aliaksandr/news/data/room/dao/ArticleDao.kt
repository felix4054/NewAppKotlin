package by.kavalchuk.aliaksandr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.kavalchuk.aliaksandr.news.data.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM `table_room_article`")
    suspend fun getRoomArticles(): List<Article>

    @Insert
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)
}