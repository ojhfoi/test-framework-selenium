package ojhfoi.webTests.PageObject;

import ojhfoi.summerWeb.webHelpers.WebHelper;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage () {
        PageFactory.initElements(WebHelper.init().getInstanceDriver(), this);
    }

}
