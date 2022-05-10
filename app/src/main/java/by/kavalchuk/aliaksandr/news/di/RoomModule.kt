package by.kavalchuk.aliaksandr.news.di

import android.content.Context
import androidx.room.Room
import by.kavalchuk.aliaksandr.news.data.room.NewsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsRoomDatabase::class.java,
        "table_room_news"
    ).build()

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsRoomDatabase) = database.newsRoomDao()
}