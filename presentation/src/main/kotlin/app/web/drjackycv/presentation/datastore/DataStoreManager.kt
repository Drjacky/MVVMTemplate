package app.web.drjackycv.presentation.datastore

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import app.web.drjackycv.presentation.base.preference.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(appContext: Context) {

    private val settingsDataStore = appContext.createDataStore("settings")

    suspend fun setThemeMode(mode: Int) {
        settingsDataStore.edit { settings ->
            settings[Settings.NIGHT_MODE] = mode
        }
    }

    val themeMode: Flow<Int> = settingsDataStore.data.map { preferences ->
        preferences[Settings.NIGHT_MODE] ?: AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
    }

}