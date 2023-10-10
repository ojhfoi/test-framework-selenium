import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WebHelperUnit {

    @DisplayName("Create Web Driver with Default params")
    @Test
    public void createDeafultDriver () {
        System.setProperty("browser.config", "");
        WebHelpers.init().tearUp();
        Assertions.assertNotNull(WebHelpers.init().getInstanceDriver());
        WebHelpers.init().tearDown();
    }

    @DisplayName("Create Firefox Driver from properties file")
    @Test
    public void createFirefoxDriver () {
        System.setProperty("browser.config", "firefoxLocal");
        WebHelpers.init().tearUp();
        Assertions.assertNotNull(WebHelpers.init().getInstanceDriver());
        WebHelpers.init().tearDown();
    }

}
