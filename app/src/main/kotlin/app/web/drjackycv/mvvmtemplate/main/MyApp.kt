package app.web.drjackycv.mvvmtemplate.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.web.drjackycv.core.ui.theme.BaseTheme
import app.web.drjackycv.mvvmtemplate.navigation.AppNavigation

@Composable
fun MyApp(
    darkMode: Boolean,
    themeFAB: @Composable () -> Unit,
) {
    BaseTheme(darkMode) {
        AppNavigation(themeFAB = themeFAB)
    }
}

@Preview
@Composable
private fun MyAppPreview() {
    MyApp(darkMode = false, themeFAB = {})
}
