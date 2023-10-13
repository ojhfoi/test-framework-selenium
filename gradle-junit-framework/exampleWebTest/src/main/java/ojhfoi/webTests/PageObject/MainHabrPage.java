package ojhfoi.webTests.PageObject;

import io.qameta.allure.Step;
import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class MainHabrPage extends BasePage {

    @FindBy(xpath = "//a[@class='tm-main-menu__item']")
    private List<WebElement> headerElements;

    @FindBy(xpath = "//article[@class='tm-articles-list__item']")
    private List<WebElement> articlesList;

    @FindBy(xpath = "//a[contains(@href,'search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

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
        String articleTitle = articlesList.get(articleCount)
                .findElement(By.xpath("//a[@class='tm-title__link']//span"))
                .getText();
        Assertions.assertNotNull(articleTitle);
        Assertions.assertNotEquals("", articleTitle);
        return articleTitle;
    }

    @Step("Check article title {articleTitle} is equals actual article title by num {articleCount}")
    public void checkArticleTitle (int articleCount, String articleTitle) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(WebHelpers.init().getInstanceDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(articlesList));
        Assertions.assertNotEquals(0, articlesList.size());
        String actualArticleTitle = articlesList.get(articleCount)
                .findElement(By.xpath("//a[@class='tm-title__link']//span"))
                .getText();
        Assertions.assertEquals(articleTitle, actualArticleTitle);
    }

    @Step("Open search page and search article {articleName}")
    public void goToSearch (String articleName) {
        searchButton.click();
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(WebHelpers.init().getInstanceDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(articleName + Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfAllElements(articlesList));
    }
}
