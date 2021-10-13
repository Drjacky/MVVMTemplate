package app.web.drjackycv.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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

@ExperimentalCoilApi
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

    val viewModel = hiltViewModel<ProductsListViewModel>()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.ChooseScreen.route) {
        composable(Screens.ChooseScreen.route) {
            ChooseView(
                themeFAB = {
                    themeFAB()
                }
            ) { choosePathType ->
                navController.navigate(Screens.ProductsScreen.route + "/${choosePathType}")
            }
        }
        composable(
            route = Screens.ProductsScreen.route + "/{choosePathType}",
            arguments = listOf(navArgument("choosePathType") { type = NavType.StringType })
        ) { backStackEntry ->
            val choosePathType: String = backStackEntry.arguments?.getString("choosePathType")
                ?: ChoosePathType.COROUTINE.name

            ProductsListView(
                viewModel = viewModel,
                themeFAB = {
                    themeFAB()
                },
                popBackStack = { navController.popBackStack() },
                choosePathType = ChoosePathType.valueOf(choosePathType),
                onSelectedProduct = { beerId ->
                    navController.navigate(Screens.ProductDetailsScreen.route + "/${beerId}")
                }
            )
        }
        composable(
            route = Screens.ProductDetailsScreen.route + "/{beerId}",
            arguments = listOf(navArgument("beerId") { type = NavType.StringType })
        ) { backStackEntry ->
            val beerId: String? = backStackEntry.arguments?.getString("beerId")

            beerId?.let {
                viewModel.getProductByCoroutine(beerId)
                    .collectAsState(initial = null).apply {
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

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout {}
}