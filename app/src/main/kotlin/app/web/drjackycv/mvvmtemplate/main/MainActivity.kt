package app.web.drjackycv.mvvmtemplate.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.web.drjackycv.core.common.datastore.DataStoreManager
import app.web.drjackycv.core.common.extension.collectIn
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
        super.onCreate(savedInstanceState)

        setContent {
            val darkMode by dataStoreManager.isDarkMode.collectAsStateWithLifecycle(initialValue = isSystemInDarkTheme())

            MyApp(
                darkMode = darkMode,
                themeFAB = {
                    FloatingActionButton(
                        onClick = {
                            setNightMode(darkMode.not())
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = whichThemeIcon(darkMode.not())),
                                contentDescription = "Theme",
                            )
                        },
                    )
                },
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

    private fun setupUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dataStoreManager.isDarkMode.collectIn(this@MainActivity) {
                    val mode = when (it) {
                        true -> AppCompatDelegate.MODE_NIGHT_YES
                        false -> AppCompatDelegate.MODE_NIGHT_NO
                    }
                    if (AppCompatDelegate.getDefaultNightMode() != mode)
                        AppCompatDelegate.setDefaultNightMode(mode)
                }
            }
        }
    }

    private fun setNightMode(isDark: Boolean) {
        allowReads {
            uiStateJob = lifecycleScope.launch {
                dataStoreManager.setDarkMode(isDark)
                ThemeState.darkModeState.value = isDark
            }
        }
    }

    private fun whichThemeIcon(isDark: Boolean): Int = when (isDark) {
        true -> R.drawable.ic_mode_night_yes_black
        false -> R.drawable.ic_mode_night_no_black
    }
}
