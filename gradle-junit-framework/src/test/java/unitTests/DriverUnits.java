package unitTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DriverUnits extends DriversHelper {

    @Override
    public <T> Object createDriver() {
        switch (config.browserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--windows-size=" + config.browserSize(),
                        "--no-sandbox",
                        "--incognito");
                if (config.headless()) {
                    chOptions.addArguments("--disable-gpu",
                            "--headless");
                }
                return new ChromeDriver(chOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--private-window");
                return new FirefoxDriver(ffOptions);
            default:
                return null;
        }
    }

    @Test
    @DisplayName("Check default driver config")
    public void testDefaultDriverConfig() {
        System.setProperty("browser.config", "");
        assertAll( "Drivers Configuration check",
                () -> {Assertions.assertNotNull(config.browserName(), "Check browser name field");},
                () -> {Assertions.assertNotNull(config.browserSize(), "Check browser size field");},
                () -> {Assertions.assertNull(config.url(), "Check browser url field");},
                () -> {assertNull(config.browserVersion(), "Check browser version field");}
        );
    }

    @Test
    @DisplayName("Check driver config loaded from file")
    public void testDriverConfigsFromFile () {
        System.setProperty("browser.config", "config");
        config = ConfigFactory.create(DriverConfig.class, System.getProperties());
        assertAll( "Drivers Configuration check",
                () -> {Assertions.assertEquals("chrome1", config.browserName(), "Check browser name field");},
                () -> {Assertions.assertEquals("1024x780", config.browserSize(), "Check browser size field");},
                () -> {Assertions.assertNull(config.url(), "Check browser url field");},
                () -> {assertNull(config.browserVersion(), "Check browser version field");}
        );
    }

    @Test
    @DisplayName("Check chrome driver startup")
    @Disabled
    public void testChromeDriver () {
        System.setProperty("browser.config", "chromeLocal");
        config = ConfigFactory.create(DriverConfig.class, System.getProperties());
        ChromeDriver driver = (ChromeDriver) createDriver();
        Assertions.assertNotNull(driver);
        driver.quit();
        System.setProperty("browser.config", "");
    }

    @Test
    @DisplayName("Check firefox driver startup")
    public void testFirefoxDriver () {
        System.setProperty("browser.config", "firefoxLocal");
        config = ConfigFactory.create(DriverConfig.class, System.getProperties());
        FirefoxDriver driver = (FirefoxDriver) createDriver();
        Assertions.assertNotNull(driver);
        driver.quit();
        System.setProperty("browser.config", "");
    }

}
