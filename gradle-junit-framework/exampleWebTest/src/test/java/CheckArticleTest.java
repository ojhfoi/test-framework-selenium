import ojhfoi.core.customExtensions.Test;
import ojhfoi.coreWeb.webHelpers.WebHelpers;
import ojhfoi.webTests.PageObject.ArticlePage;
import ojhfoi.webTests.PageObject.MainHabrPage;
import org.junit.jupiter.api.DisplayName;

public class CheckArticleTest {

    @Test(browserConfig = "firefoxLocal", testConfig = "testDataConfig")
    @DisplayName("Check first article and check it title")
    public void checkArticleTest () {
        try {
            WebHelpers.init().tearUp();
            WebHelpers.init().navigateTo(WebHelpers.init().getTestData().projectUrl());
            WebHelpers.init().checkCurrentUrl(WebHelpers.init().getTestData().projectUrl());

            MainHabrPage mainHabrPage = new MainHabrPage();
            mainHabrPage.openHeadersBlock("Разработка");

            mainHabrPage = new MainHabrPage();
            String articleName = mainHabrPage.getArticleTitle(0);
            mainHabrPage.openArticles(0);

            ArticlePage articlePage = new ArticlePage();
            articlePage.checkArticleTitle(articleName);
        } finally {
            WebHelpers.init().tearDown();
        }
    }

}
