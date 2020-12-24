package app.web.drjackycv.presentation.base.preference

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object Settings {

    const val NIGHT_MODE = "night_mode"

    val MODE_NIGHT_DEFAULT =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        } else {
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }

}