package app.web.drjackycv.presentation.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.datastore.DataStoreManager
import app.web.drjackycv.presentation.extension.collectIn
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    object ThemeState {
        var darkModeState: MutableState<Boolean> = mutableStateOf(false)
    }

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
                                tint = Color.Black
                            )
                        }
                    )
                })
        }
        setupUI()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
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
        lifecycleScope.launchWhenStarted {
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

    private fun setNightMode(isDark: Boolean) {
        allowReads {
            uiStateJob = lifecycleScope.launch {
                dataStoreManager.setDarkMode(isDark)
                ThemeState.darkModeState.value = isDark
            }
        }
    }

    private fun whichThemeIcon(isDark: Boolean): Int {
        return when (isDark) {
            true -> {
                R.drawable.ic_mode_night_yes_black
            }
            false -> {
                R.drawable.ic_mode_night_no_black
            }
        }
    }

}