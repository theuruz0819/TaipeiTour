package com.example.tptour.base

import android.content.Context
import android.content.SharedPreferences
import com.example.tptour.R
import java.util.*

class PreferencesManager(context: Context) {

    companion object {
        const val LANG_CODE = "lang_code"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)


    fun setLangCode(langCode: String) {
        val editor = preferences.edit()
        editor.putString(LANG_CODE, langCode)
        editor.apply()
    }

    fun getLangCode(): String {
        return preferences.getString(LANG_CODE, LanguageCode.ZH_TW.name)?:LanguageCode.ZH_TW.name
    }

    fun getRequestLangCode(): String{
        return LanguageCode.valueOf(getLangCode()).code
    }
}