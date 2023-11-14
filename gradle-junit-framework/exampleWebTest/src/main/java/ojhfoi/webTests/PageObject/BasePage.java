package ojhfoi.webTests.PageObject;

import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage () {
        PageFactory.initElements(WebHelpers.init().getInstanceDriver(), this);
    }

}
