package org.example

import com.thoughtworks.gauge.AfterScenario
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.Step
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

class LoginConceptImplementation : BaseTest() {

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

    companion object {
        private enum class View(val by: By) {
            EDITTEXT_USERNAME(By.id(getFullId("etUsername"))),
            BUTTON_LOGIN(By.id(getFullId("btnLogin"))),
        }
    }
}