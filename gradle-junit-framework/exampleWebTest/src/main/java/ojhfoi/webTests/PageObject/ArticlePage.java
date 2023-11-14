package ojhfoi.webTests.PageObject;

import io.qameta.allure.Step;
import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ArticlePage extends BasePage {
    @FindBy(xpath = "//h1[contains(@class,'tm-title')]//span")
    private WebElement articleTitle;

    @Step("Check is expeceted article title {expectedTitle} equals actual title")
    public void checkArticleTitle (String expectedTitle) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(WebHelpers.init().getInstanceDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class);
        wait.until(ExpectedConditions.visibilityOf(articleTitle));
        Assertions.assertEquals(expectedTitle, articleTitle.getText());
    }
}
