package org.example

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL


open class BaseTest {

    protected lateinit var driver: AndroidDriver<AndroidElement>

    enum class VISIBILITY(val isVisible: Boolean) {
        Visible(true),
        NotVisible(false),
    }

    fun setupAppium() {
        val caps = DesiredCapabilities()

        caps.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, PLATFORM)
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE)
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY)
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME)

        driver = AndroidDriver(URL(APPIUM_SERVER_URL), caps)
    }

    fun quitAppium() {
        driver.quit()
    }

    protected fun getWebDriverWait(seconds: Long = DEFAULT_WAIT_SECONDS) = WebDriverWait(driver, seconds)

    companion object {
        private const val APPIUM_SERVER_URL = "http://localhost:4723/wd/hub"

        private const val PLATFORM = "Android"
        private const val APP_PACKAGE = "com.ccakir.androidplayground"
        private const val APP_ACTIVITY = "MainActivity"
        private const val DEVICE_NAME = "2VN5T18426000008"

        private const val DEFAULT_WAIT_SECONDS = 10L

        fun getFullId(id: String) = "$APP_PACKAGE:id/$id"
    }
}