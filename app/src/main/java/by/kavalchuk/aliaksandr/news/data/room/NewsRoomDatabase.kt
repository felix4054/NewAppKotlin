package by.kavalchuk.aliaksandr.news.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.kavalchuk.aliaksandr.news.data.model.Article
import by.kavalchuk.aliaksandr.news.data.model.NewsRoom
import by.kavalchuk.aliaksandr.news.data.model.SourcesConverter
import by.kavalchuk.aliaksandr.news.data.room.dao.ArticleDao
import by.kavalchuk.aliaksandr.news.data.room.dao.NewsRoomDao


@Database(entities = [NewsRoom::class, Article::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class)
abstract class NewsRoomDatabase: RoomDatabase() {

    abstract fun newsRoomDao(): NewsRoomDao

    abstract fun articleDao(): ArticleDao
}