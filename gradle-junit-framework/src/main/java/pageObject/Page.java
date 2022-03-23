package pageObject;

import core.Base;
import org.openqa.selenium.support.PageFactory;

public class Page extends Base {

    public Page () {
        PageFactory.initElements(driver, this);
    }

}
