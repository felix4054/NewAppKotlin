package by.kavalchuk.aliaksandr.news.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourcesConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Source?>() {}.type

    @TypeConverter
    fun fromSource(source: Source?): String{
        return gson.toJson(source,type)
    }

    @TypeConverter
    fun toSource(json: String?): Source {
        return gson.fromJson(json,type)
    }
}