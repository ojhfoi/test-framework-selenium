package ojhfoi.core.helpers;

import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.core.configs.testsConfig.TestdataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public abstract class HelperUtils {

    protected DriversHelper driverHelper;

    protected TestdataConfig testData;

    protected DriverConfig driverConfig;

    protected String configName = "";

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
            this.testData = ConfigFactory.create(TestdataConfig.class, System.getProperties());
        }
        return this.testData;
    }

    public void tearUp () {}

    public void tearDown () {}

    public void navigateTo (String url) {
        this.getInstanceDriver().navigate().to(url);
    }

    public void navigateTo (URL url) {
        this.getInstanceDriver().navigate().to(url);
    }
}
