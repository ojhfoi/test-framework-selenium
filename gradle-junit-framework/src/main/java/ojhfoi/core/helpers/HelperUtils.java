package ojhfoi.core.helpers;

import io.qameta.allure.Step;
import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.core.configs.testsConfig.TestdataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public abstract class HelperUtils {

    protected DriversHelper driverHelper;

    protected TestdataConfig testData;

    protected DriverConfig driverConfig;

    protected String configName = "";

    protected String testDataConfigName = "";

    protected static RemoteWebDriver driver;

    public RemoteWebDriver getInstanceDriver () {
        if (driver == null) {
            driver = (RemoteWebDriver) driverHelper.createDriver();
        }
        return driver;
    }

    public DriverConfig getDriverConfig () {
        if (this.driverConfig == null) {
            ConfigFactory.setProperty("browser.config", configName);
            this.driverConfig = ConfigFactory.create(DriverConfig.class);
        }
        return driverConfig;
    }

    public TestdataConfig getTestData () {
        if (this.testData == null) {
            this.testData = ConfigFactory.create(TestdataConfig.class);
        }
        return this.testData;
    }

    @Step("Init instance driver and open browser")
    public void tearUp () {}

    @Step("Close and clean instance driver")
    public void tearDown () {}

    @Step("Navigate browser to url {url}")
    public void navigateTo (String url) {
        this.getInstanceDriver().navigate().to(url);
    }

    @Step("Navigate browser to url {url}")
    public void navigateTo (URL url) {
        this.getInstanceDriver().navigate().to(url);
    }

    @Step("Check is current url equals expected url {expectedUrl}")
    public void checkCurrentUrl (String expectedUrl) {
        Assertions.assertTrue(this.getInstanceDriver().getCurrentUrl().contains(expectedUrl),
                "Current URL " + this.getInstanceDriver().getCurrentUrl() +
                "does't contains expectedUrl " + expectedUrl);
    }
}
