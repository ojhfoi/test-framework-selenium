package ojhfoi.coreWeb.webDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverCreator extends DriversHelper {

    @Override
    public <T> Object createDriver() {
        config = ConfigFactory.create(DriverConfig.class, System.getProperties());
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

}
