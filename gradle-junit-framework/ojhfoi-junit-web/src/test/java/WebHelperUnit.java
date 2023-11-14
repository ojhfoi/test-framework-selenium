import ojhfoi.summerWeb.webHelpers.WebHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WebHelperUnit {

    @DisplayName("Create Web Driver with Default params")
    @Test
    public void createDeafultDriver () {
        WebHelper.init().tearUp();
        Assertions.assertNotNull(WebHelper.init().getInstanceDriver());
        WebHelper.init().tearDown();
    }

    @DisplayName("Create Firefox Driver from properties file")
    @ojhfoi.core.customExtensions.Test(browserConfig = "firefoxLocal")
    public void createFirefoxDriver () {
        WebHelper.init().tearUp();
        Assertions.assertNotNull(WebHelper.init().getInstanceDriver());
        WebHelper.init().tearDown();
    }

}
