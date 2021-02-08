package app.web.drjackycv.presentation.base.preference

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.intPreferencesKey

object Settings {

    val NIGHT_MODE = intPreferencesKey("night_mode")

    val MODE_NIGHT_DEFAULT =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        } else {
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }

}