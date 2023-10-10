package ojhfoi.coreWeb.webHelpers;

import ojhfoi.core.helpers.HelperUtils;
import ojhfoi.coreWeb.webDrivers.DriverCreator;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebHelpers extends HelperUtils {

    private static volatile WebHelpers instance;

    public static WebHelpers init () {
        if (instance == null) {
            instance = new WebHelpers();
            instance.driverHelper = new DriverCreator();
        }
        return instance;
    }

    @Override
    public void tearUp() {
        init().getInstanceDriver();
    }

    @Override
    public void tearDown() {
        if (getInstanceDriver() != null) {
            getInstanceDriver().quit();
            setInstanceDriver(null);
        }
    }
}
