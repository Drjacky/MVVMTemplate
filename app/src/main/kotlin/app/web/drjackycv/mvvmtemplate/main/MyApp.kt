package app.web.drjackycv.mvvmtemplate.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.web.drjackycv.core.ui.theme.BaseTheme
import app.web.drjackycv.mvvmtemplate.navigation.AppNavigation

@Composable
fun MyApp(
    darkMode: Boolean,
    themeFAB: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseTheme(darkMode) {
        AppNavigation(
            themeFAB = themeFAB,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun MyAppPreview() {
    MyApp(darkMode = false, themeFAB = {})
}
