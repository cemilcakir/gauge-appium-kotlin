package org.example

import com.thoughtworks.gauge.AfterScenario
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.Step
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.support.ui.ExpectedConditions

class LoginImplementation : BaseTest() {

    @BeforeScenario
    fun before() {
        setupAppium()
    }

    @AfterScenario
    fun after() {
        quitAppium()
    }

    @Step("Enter username <username>")
    fun enterUsername(username: String) {
        val etUsername = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                View.EDITTEXT_USERNAME.by
            )
        )

        etUsername.sendKeys(username)
    }

    @Step("Click Login button")
    fun clickLoginButton() {
        val btnLogin = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                View.BUTTON_LOGIN.by
            )
        )

        btnLogin.click()
    }

    @Step("Progress indicator should be <visibility>")
    fun progressIndicatorVisibility(visibility: VISIBILITY) {
        try {
            val progressIndicator = getWebDriverWait(5).until(
                ExpectedConditions.visibilityOfElementLocated(
                    View.PROGRESS_INDICATOR.by
                )
            )

            assertThat(progressIndicator.isDisplayed).isEqualTo(visibility.isVisible)
        } catch (e: TimeoutException) {
            assertThat(true).isEqualTo(!visibility.isVisible)
        }
    }

    @Step("Toast message should say <message>")
    fun checkToastMessage(message: String) {
        getWebDriverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                View.TOAST.by
            )
        )

        val toast = driver.findElement(View.TOAST.by)

        assertThat(toast.text).isEqualTo(message)
    }

    companion object {
        private enum class View(val by: By) {
            EDITTEXT_USERNAME(By.id(getFullId("etUsername"))),
            BUTTON_LOGIN(By.id(getFullId("btnLogin"))),
            PROGRESS_INDICATOR(By.id(getFullId("progressIndicator"))),
            TOAST(By.xpath("//android.widget.Toast[1]")),
        }
    }
}