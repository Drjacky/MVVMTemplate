package app.web.drjackycv.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.compose.BaseTheme
import app.web.drjackycv.presentation.datastore.DataStoreManager
import app.web.drjackycv.presentation.extension.collectIn
import app.web.drjackycv.presentation.products.choose.ChooseView
import app.web.drjackycv.presentation.products.entity.BeerUI
import app.web.drjackycv.presentation.products.productdetail.ProductDetailView
import app.web.drjackycv.presentation.products.productlist.ProductsListView
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalAnimationGraphicsApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    object ThemeState {
        var darkModeState: MutableState<Boolean> = mutableStateOf(false)
    }

    private var uiStateJob: Job? = null
    private val isDark = ThemeState.darkModeState.value

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                MainLayout(
                    themeFAB = {
                        FloatingActionButton(
                            onClick = {
                                val theme = when (isDark) {
                                    true -> AppCompatDelegate.MODE_NIGHT_NO
                                    false -> AppCompatDelegate.MODE_NIGHT_YES
                                }
                                AppCompatDelegate.setDefaultNightMode(theme)
                                ThemeState.darkModeState.value = isDark.not()
                                setNightMode(isDark.not())
                            },
                            content = {
                                Icon(
                                    painter = painterResource(id = whichThemeIcon(isDark)),
                                    contentDescription = "Theme",
                                    tint = Color.Black
                                )
                            }
                        )
                    },
                )
            }
        }
        setupUI()
    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onBackPressed() {
        if (isTaskRoot
            && supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount == 0
            && supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
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
                //TODO
            }
        }
    }

    private fun setNightMode(isDark: Boolean) {
        allowReads {
            uiStateJob = lifecycleScope.launchWhenStarted {
                dataStoreManager.setThemeMode(whichThemeMode(isDark))
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

    private fun whichThemeMode(isDark: Boolean): Int {
        return when (isDark) {
            true -> {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            false -> {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }

}

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Composable
fun MainLayout(
    themeFAB: @Composable () -> Unit,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.ProductsScreen.route) {
        composable(Screens.ChooseScreen.route) {
            ChooseView(
                themeFAB = {
                    themeFAB()
                }
            ) {
                navController.navigate(Screens.ProductsScreen.route + "/${it}")
            }
        }
        composable(route = Screens.ProductsScreen.route) { backStackEntry ->
            ProductsListView(
                themeFAB = {
                    themeFAB()
                },
                popBackStack = { navController.popBackStack() },
                onSelectedProduct = { navController.navigate(Screens.ProductDetailsScreen.route + "/${it}") }
            )
        }
        composable(Screens.ProductDetailsScreen.route) { backStackEntry ->
            ProductDetailView(
                themeFAB = {
                    themeFAB()
                },
                popBackStack = { navController.popBackStack() },
                product = backStackEntry.arguments?.getParcelable<BeerUI>("beer") as BeerUI
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout {}
}