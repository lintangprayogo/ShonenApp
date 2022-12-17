package com.example.shonenapp.data.local.dao

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        list.forEach {
            stringBuilder.append("${it},")
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String):List<String>{
        val listOfString = string.split(separator)
        return listOfString
    }
}