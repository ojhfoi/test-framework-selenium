import ojhfoi.core.customExtensions.Test;
import ojhfoi.summerWeb.webHelpers.WebHelper;
import ojhfoi.webTests.PageObject.ArticlePage;
import ojhfoi.webTests.PageObject.MainHabrPage;
import org.junit.jupiter.api.DisplayName;

public class CheckArticleSearchTest {

    MainHabrPage mainHabrPage;
    ArticlePage articlePage;

    @Test(browserConfig = "firefoxLocal", testConfig = "testDataConfig")
    @DisplayName("Check first article and check it title")
    public void checkArticleTest () {
        try {
            WebHelper.init().tearUp();
            WebHelper.init().navigateTo(WebHelper.init().getTestData().projectUrl());
            WebHelper.init().checkCurrentUrl(WebHelper.init().getTestData().projectUrl());

            mainHabrPage = new MainHabrPage();
            mainHabrPage.goToSearch("junit");
            mainHabrPage.checkArticleTitle(1, "10 интересных нововведений в JUnit 5");
            mainHabrPage.openArticles(1);

            articlePage = new ArticlePage();
            articlePage.checkArticleTitle("10 интересных нововведений в JUnit 5");
        } finally {
            WebHelper.init().tearDown();
        }
    }

}
