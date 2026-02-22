import com.android.build.api.dsl.CommonExtension
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke

internal fun configureGradleManagedDevices(
    commonExtension: CommonExtension,
) {
    val pixel6Api31 = DeviceConfig("Pixel 6", 31, "aosp")
    val pixel8Api34 = DeviceConfig("Pixel 8", 34, "google")

    val allDevices = listOf(pixel6Api31, pixel8Api34)
    val ciDevices = listOf(pixel6Api31)

    commonExtension.testOptions.apply {
        managedDevices {
            localDevices {
                allDevices.forEach { deviceConfig ->
                    maybeCreate(deviceConfig.taskName).apply {
                        device = deviceConfig.device
                        apiLevel = deviceConfig.apiLevel
                        systemImageSource = deviceConfig.systemImageSource
                    }
                }
            }
            groups {
                maybeCreate("ci").apply {
                    ciDevices.forEach { deviceConfig ->
                        targetDevices.add(localDevices[deviceConfig.taskName])
                    }
                }
            }
        }
    }
}

private data class DeviceConfig(
    val device: String,
    val apiLevel: Int,
    val systemImageSource: String,
) {
    val taskName = buildString {
        append(device.lowercase().replace(" ", ""))
        append("api")
        append(apiLevel.toString())
        append(systemImageSource.replace("-", ""))
    }
}
