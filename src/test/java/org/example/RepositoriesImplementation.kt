package org.example

import com.thoughtworks.gauge.AfterScenario
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.Step
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

class RepositoriesImplementation : BaseTest() {

    @BeforeScenario
    fun before() {
        setupAppium()
    }

    @AfterScenario
    fun after() {
        quitAppium()
    }

    @Step("For the user cemicakir <repository> repositories should be visible")
    fun checkForRepositories(repository: String) {
        login()

        val repositoryView = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                getRepositoryBy(repository)
            )
        )

        assertThat((repositoryView.isDisplayed)).isTrue

    }

    @Step("Click <repository> repository")
    fun clickRepository(repository: String) {
        login()

        val repositoryView = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                getRepositoryBy(repository)
            )
        )

        repositoryView.click()
    }

    @Step("Commit list should be visible")
    fun isCommitListVisible() {
        val commitListView = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                View.COMMIT_LIST_VIEW.by
            )
        )

        assertThat(commitListView.isDisplayed).isTrue
    }

    private fun getRepositoryBy(repository: String): By {
        val xpathRepository = XPATH_TEXTVIEW.replace(REPOSITORY, repository)
        return By.xpath(xpathRepository)
    }

    private fun login(username: String = "cemilcakir") {
        val etUsername = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                View.EDITTEXT_USERNAME.by
            )
        )

        etUsername.sendKeys(username)

        val btnLogin = getWebDriverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                View.BUTTON_LOGIN.by
            )
        )

        btnLogin.click()
    }

    companion object {
        private enum class View(val by: By) {
            COMMIT_LIST_VIEW(By.id(getFullId("rclCommitList"))),
            EDITTEXT_USERNAME(By.id(getFullId("etUsername"))),
            BUTTON_LOGIN(By.id(getFullId("btnLogin"))),
        }

        private const val REPOSITORY = "#REPOSITORY#"
        private const val XPATH_TEXTVIEW = "//android.widget.TextView[@text='$REPOSITORY']"
    }
}