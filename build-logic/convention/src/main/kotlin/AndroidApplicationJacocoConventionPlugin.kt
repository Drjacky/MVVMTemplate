import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.testing.jacoco.plugins.JacocoPlugin

class AndroidApplicationJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<JacocoPlugin>()
            configureJacoco(
                commonExtension = extensions.getByType<ApplicationExtension>(),
                androidComponentsExtension = extensions.getByType<ApplicationAndroidComponentsExtension>(),
            )
        }
    }
}
