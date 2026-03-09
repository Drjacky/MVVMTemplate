import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")
            apply(plugin = "com.google.dagger.hilt.android")

            val hiltCompiler = libs.findLibrary("hilt.compiler").get()
            val hiltAndroidTesting = libs.findLibrary("hilt.android.testing").get()

            dependencies {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("ksp", hiltCompiler)

                add("kspTest", hiltCompiler)
                add("testImplementation", hiltAndroidTesting)
                add("kspAndroidTest", hiltCompiler)
                add("androidTestImplementation", hiltAndroidTesting)
            }
        }
    }
}
