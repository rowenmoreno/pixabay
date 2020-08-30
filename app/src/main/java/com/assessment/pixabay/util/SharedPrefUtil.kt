package com.assessment.pixabay.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtil(context: Context){
    private val appContext = context.applicationContext

    private val preference : SharedPreferences
    get() = appContext.getSharedPreferences("mypreferences", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String){
        preference.edit().putString(key, value).apply()
    }
}