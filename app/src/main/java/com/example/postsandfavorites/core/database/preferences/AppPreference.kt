package com.example.postsandfavorites.core.database.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreference(
    private val appContext: Context,
    private val preference: SharedPreferences = appContext.getSharedPreferences(
        KEY_PREFERENCES,
        Context.MODE_PRIVATE
    )
) {

    fun putIntArray(key: String, value: IntArray) {
        preference.edit()
            .putString(
                key, value.joinToString(
                    separator = ",",
                    transform = { it.toString() })
            )
            .apply()
    }

    fun getIntArray(key: String): IntArray {
        with(preference.getString(key, "") ?: "") {
            with(if (isNotEmpty()) split(',') else return intArrayOf()) {
                return IntArray(count()) { this[it].toInt() }
            }
        }
    }
}

const val KEY_PREFERENCES = "ORCAS_PREFERENCE"