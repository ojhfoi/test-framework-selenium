package ojhfoi.coreWeb.webDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.coreWeb.webHelpers.WebHelpers;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverCreator extends DriversHelper {

    @Override
    public synchronized  <T> Object createDriver() {
        switch (WebHelpers.init().getDriverConfig().browserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--windows-size=" + WebHelpers.init().getDriverConfig().browserSize(),
                        "--no-sandbox",
                        "--incognito");
                if (WebHelpers.init().getDriverConfig().headless()) {
                    chOptions.addArguments("--disable-gpu",
                            "--headless");
                }

                return new ChromeDriver(chOptions);
            case "firefox":
                String width = WebHelpers.init().getDriverConfig().browserSize()
                                .split("x")[0];
                String height = WebHelpers.init().getDriverConfig().browserSize()
                        .split("x")[1];
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--private-window");
                ffOptions.addArguments("--width=" + width);
                ffOptions.addArguments("--height=" + height);
                return new FirefoxDriver(ffOptions);
            default:
                return null;
        }
    }

}
