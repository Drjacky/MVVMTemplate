package app.web.drjackycv.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.presentation.R
import app.web.drjackycv.presentation.base.theme.BaseTheme
import app.web.drjackycv.presentation.datastore.DataStoreManager
import app.web.drjackycv.presentation.extension.collectIn
import app.web.drjackycv.presentation.main.Screens.*
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.choose.ChooseView
import app.web.drjackycv.presentation.products.entity.BeerMapper
import app.web.drjackycv.presentation.products.productdetail.ProductDetailView
import app.web.drjackycv.presentation.products.productlist.ProductsListView
import app.web.drjackycv.presentation.products.productlist.ProductsListViewModel
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalLifecycleComposeApi
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

            BaseTheme(darkMode) {
                MainLayout(
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

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun MainLayout(
    themeFAB: @Composable () -> Unit,
) {

    val viewModel = hiltViewModel<ProductsListViewModel>()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ChooseScreen.route) {
        composable(ChooseScreen.route) {
            ChooseView(
                themeFAB = {
                    themeFAB()
                }
            ) { choosePathType ->
                navController.navigate(ProductsScreen.route + "/${choosePathType}")
            }
        }
        composable(
            route = ProductsScreen.route + "/{" + ProductsScreen.navArgumentKey + "}",
            arguments = listOf(navArgument(ProductsScreen.navArgumentKey) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val choosePathType: String =
                backStackEntry.arguments?.getString(ProductsScreen.navArgumentKey)!!

            ProductsListView(
                viewModel = viewModel,
                themeFAB = {
                    themeFAB()
                },
                popBackStack = { navController.popBackStack() },
                choosePathType = ChoosePathType.valueOf(choosePathType),
                onSelectedProduct = { beerId ->
                    navController.navigate(ProductDetailsScreen.route + "/${beerId}")
                }
            )
        }
        composable(
            route = ProductDetailsScreen.route + "/{" + ProductDetailsScreen.navArgumentKey + "}",
            arguments = listOf(navArgument(ProductDetailsScreen.navArgumentKey) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val beerId: String? =
                backStackEntry.arguments?.getString(ProductDetailsScreen.navArgumentKey)

            beerId?.let {
                viewModel.getProductByCoroutine(beerId)
                    .collectAsStateWithLifecycle(initialValue = null).apply {
                        this.value?.let {
                            ProductDetailView(
                                themeFAB = {
                                    themeFAB()
                                },
                                popBackStack = { navController.popBackStack() },
                                product = BeerMapper().mapLeftToRight(it)
                            )
                        }
                    }
            }


        }
    }

}

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
private fun MainLayoutPreview() {
    MainLayout {}
}