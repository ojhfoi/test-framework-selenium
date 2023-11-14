package unitTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.core.helpers.HelperUtils;
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
    
    private HelperUtils helperUtils;

    @Override
    public <T> Object createDriver() {
        DriverConfig config = ConfigFactory.create(DriverConfig.class);
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
        DriverConfig config = ConfigFactory.create(DriverConfig.class);
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
        ConfigFactory.setProperty("browser.config", "config");
        DriverConfig config = ConfigFactory.create(DriverConfig.class);
        assertAll( "Drivers Configuration check",
                () -> {Assertions.assertEquals("chrome1", config.browserName(), "Check browser name field");},
                () -> {Assertions.assertEquals("1024x780", config.browserSize(), "Check browser size field");},
                () -> {Assertions.assertNull(config.url(), "Check browser url field");},
                () -> {assertNull(config.browserVersion(), "Check browser version field");}
        );
        ConfigFactory.setProperty("browser.config", "");
    }

    @Test
    @DisplayName("Check chrome driver startup")
    @Disabled
    public void testChromeDriver () {
        ConfigFactory.setProperty("browser.config", "chromeLocal");
        ChromeDriver driver = (ChromeDriver) createDriver();
        Assertions.assertNotNull(driver);
        driver.quit();
        ConfigFactory.setProperty("browser.config", "");
    }

    @Test
    @DisplayName("Check firefox driver startup")
    public void testFirefoxDriver () {
        ConfigFactory.setProperty("browser.config", "firefoxLocal");
        FirefoxDriver driver = (FirefoxDriver) createDriver();
        Assertions.assertNotNull(driver);
        driver.quit();
        ConfigFactory.setProperty("browser.config", "");
    }

}
