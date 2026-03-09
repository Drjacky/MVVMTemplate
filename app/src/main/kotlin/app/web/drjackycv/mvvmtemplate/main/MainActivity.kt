package app.web.drjackycv.mvvmtemplate.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.web.drjackycv.core.common.datastore.DataStoreManager
import app.web.drjackycv.core.common.preference.Settings
import app.web.drjackycv.core.domain.extension.allowReads
import app.web.drjackycv.core.ui.R
import app.web.drjackycv.core.ui.theme.ThemeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var uiStateJob: Job? = null

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val themeMode by dataStoreManager.themeMode
                .collectAsStateWithLifecycle(
                    initialValue = AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
                )

            val darkMode = isDarkForMode(themeMode)
            ThemeState.darkModeState.value = darkMode

            MyApp(
                darkMode = darkMode,
                themeFAB = { ThemeFAB(themeMode) },
            )
        }
        setupUI()
    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onDestroy() {
        if (isTaskRoot && isFinishing) {
            finishAfterTransition()
        }
        super.onDestroy()
    }

    @Composable
    private fun isDarkForMode(mode: Int): Boolean = when (mode) {
        AppCompatDelegate.MODE_NIGHT_YES -> true
        AppCompatDelegate.MODE_NIGHT_NO -> false
        else -> isSystemInDarkTheme()
    }

    @Composable
    private fun ThemeFAB(currentMode: Int) {
        val (nextMode, icon) = when (currentMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> Pair(
                AppCompatDelegate.MODE_NIGHT_YES,
                R.drawable.ic_mode_night_default_black
            )

            AppCompatDelegate.MODE_NIGHT_YES -> Pair(
                Settings.MODE_NIGHT_DEFAULT,
                R.drawable.ic_mode_night_no_black
            )

            else -> Pair(
                AppCompatDelegate.MODE_NIGHT_NO,
                R.drawable.ic_mode_night_yes_black
            )
        }

        FloatingActionButton(
            onClick = { setNightMode(nextMode) },
            content = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.content_description_theme),
                )
            },
        )
    }

    private fun setupUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dataStoreManager.themeMode.collect { mode ->
                    if (AppCompatDelegate.getDefaultNightMode() != mode) {
                        AppCompatDelegate.setDefaultNightMode(mode)
                    }
                }
            }
        }
    }

    private fun setNightMode(mode: Int) {
        allowReads {
            uiStateJob = lifecycleScope.launch {
                dataStoreManager.setThemeMode(mode)
            }
        }
    }
}
