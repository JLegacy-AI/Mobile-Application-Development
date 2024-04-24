package com.jlegacyai.sharedpreferencesexercise

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("jlegacy-ai", Context.MODE_PRIVATE)

    fun store(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            else -> throw IllegalArgumentException("Unsupported data type")
        }
        editor.apply()
    }

    fun retrieve(key: String, defaultValue: Any): Any {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) ?: defaultValue
            is Int -> sharedPreferences.getInt(key, defaultValue)
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }
}
