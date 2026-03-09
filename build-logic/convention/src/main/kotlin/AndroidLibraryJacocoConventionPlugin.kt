import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.testing.jacoco.plugins.JacocoPlugin

class AndroidLibraryJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<JacocoPlugin>()
            configureJacoco(
                commonExtension = extensions.getByType<LibraryExtension>(),
                androidComponentsExtension = extensions.getByType<LibraryAndroidComponentsExtension>(),
            )
        }
    }
}
