package com.game.clicker.data.utils

import android.content.Context
import com.game.clicker.domain.models.AppSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesAppSettings @Inject constructor(
    @ApplicationContext appContext: Context
) : AppSettings {

    companion object {
        private const val USER_ID = "USER_ID"
        private const val NAME_PREFERENCES = "settings"
    }

    private val sharedPreferences = appContext.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)

    override fun setUserId(userId: Int?) {
        val editor = sharedPreferences.edit()
        if (userId == null) {
            editor.remove(USER_ID)
        } else {
            editor.putString(USER_ID, userId.toString())
        }
        editor.apply()
    }

    override fun getUserId(): Int? =
        sharedPreferences.getString(USER_ID, null)?.toInt()
}