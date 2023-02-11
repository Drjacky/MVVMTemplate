package app.web.drjackycv.presentation.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.preference.Settings
import app.web.drjackycv.presentation.databinding.ActivityMainBinding
import app.web.drjackycv.presentation.datastore.DataStoreManager
import app.web.drjackycv.presentation.extension.collectIn
import app.web.drjackycv.presentation.extension.setOnReactiveClickListener
import app.web.drjackycv.presentation.extension.setStatusBarColor
import app.web.drjackycv.presentation.extension.viewInflateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewInflateBinding(ActivityMainBinding::inflate)
    private val navController: NavController by lazy {
        findNavController(R.id.activityMainChooseHostFragment)
    }
    private var uiStateJob: Job? = null

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

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
            dataStoreManager.themeMode.collectIn(this@MainActivity) { mode ->
                setNightMode(mode)
            }
        }
    }

    private fun setNightMode(mode: Int) {
        allowReads {
            uiStateJob = lifecycleScope.launchWhenStarted {
                dataStoreManager.setThemeMode(mode)
            }
        }
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> applyThemeMode(
                AppCompatDelegate.MODE_NIGHT_YES,
                R.drawable.ic_mode_night_default_black
            )
            AppCompatDelegate.MODE_NIGHT_YES -> applyThemeMode(
                Settings.MODE_NIGHT_DEFAULT,
                R.drawable.ic_mode_night_no_black
            )
            else -> applyThemeMode(
                AppCompatDelegate.MODE_NIGHT_NO,
                R.drawable.ic_mode_night_yes_black
            )
        }
    }

    private fun applyThemeMode(themeMode: Int, @DrawableRes icon: Int) {
        setStatusBarColor(R.color.status_bar)
        binding.activityMainSwitchThemeFab.setImageResource(icon)
        binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
            setNightMode(themeMode)
        }
        if (AppCompatDelegate.getDefaultNightMode() != themeMode) {
            AppCompatDelegate.setDefaultNightMode(themeMode)
            window?.setWindowAnimations(R.style.WindowAnimationFadeInOut)
        }
    }

}