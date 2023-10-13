package ojhfoi.webTests.PageObject;

import io.qameta.allure.Step;
import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainHabrPage extends BasePage {

    @FindBy(xpath = "//a[@class='tm-main-menu__item']")
    private List<WebElement> headerElements;

    @FindBy(xpath = "//article[@class='tm-articles-list__item']")
    private List<WebElement> articlesList;

    @Step("Open block {blockName} in headers")
    public void openHeadersBlock (String blockName) {
        Assertions.assertNotEquals(0, headerElements.size());
        headerElements.stream().filter(
                webElement -> webElement.getText().contains(blockName)
        ).findFirst().ifPresentOrElse(
                (webElement) -> {webElement.click();},
                () -> {throw new AssertionError("Not found element with name: "
                        + blockName);}
        );
    }

    @Step("Open article by number {articleCount}")
    public void openArticles (int articleCount) {
        Assertions.assertNotEquals(0, articlesList.size());
        articlesList.get(0)
                .findElement(By.xpath("//a[@class='tm-title__link']"))
                .click();
    }

    @Step("Get article title by number {articleCount}")
    public String getArticleTitle (int articleCount) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(WebHelpers.init().getInstanceDriver())
                .withTimeout(Duration.ofSeconds(30))
                        .pollingEvery(Duration.ofMillis(500))
                                .ignoring(ElementClickInterceptedException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(articlesList));
        Assertions.assertNotEquals(0, articlesList.size());
        String articleTitle = articlesList.get(0)
                .findElement(By.xpath("//a[@class='tm-title__link']//span"))
                .getText();
        Assertions.assertNotNull(articleTitle);
        Assertions.assertNotEquals("", articleTitle);
        return articleTitle;
    }
}
