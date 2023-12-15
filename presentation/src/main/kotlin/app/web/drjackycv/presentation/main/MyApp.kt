package app.web.drjackycv.presentation.main


import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview

import app.web.drjackycv.presentation.base.navigation.MyAppState
import app.web.drjackycv.presentation.base.navigation.MyNavHost
import app.web.drjackycv.presentation.base.navigation.rememberMyAppState
import app.web.drjackycv.presentation.base.theme.BaseTheme
import coil.annotation.ExperimentalCoilApi


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun MyApp(
    darkMode: Boolean,
    themeFAB: @Composable () -> Unit,
    appState: MyAppState = rememberMyAppState()
) {
    BaseTheme(darkMode) {
        MainLayout(themeFAB = { themeFAB() }, appState = appState)
    }
}


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun MainLayout(
    themeFAB: @Composable () -> Unit,
    appState: MyAppState,
) {
    MyNavHost(
        navController = appState.navController,
        themeFAB = themeFAB,
        onBackClick = appState::onBackClick,
        onNavigateToDestination = { destination, route ->
            appState.navigate(destination = destination, route = route)
        }
    )
}


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
private fun MainLayoutPreview() {
    val appState: MyAppState = rememberMyAppState()
    MainLayout(themeFAB = {}, appState = appState)
}
