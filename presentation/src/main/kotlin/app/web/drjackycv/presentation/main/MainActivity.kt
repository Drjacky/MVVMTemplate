package app.web.drjackycv.presentation.main

import android.os.Bundle
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
import app.web.drjackycv.presentation.extension.setOnReactiveClickListener
import app.web.drjackycv.presentation.extension.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
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
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    private fun setupUI() {
        lifecycleScope.launchWhenStarted {
            dataStoreManager.themeMode.collect { mode ->
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
        AppCompatDelegate.setDefaultNightMode(mode)
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_no_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_yes_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(Settings.MODE_NIGHT_DEFAULT)
                }
            }
            else -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_default_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

}