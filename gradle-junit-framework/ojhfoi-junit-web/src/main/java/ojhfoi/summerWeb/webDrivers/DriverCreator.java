package ojhfoi.summerWeb.webDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.summerWeb.webHelpers.WebHelper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverCreator extends DriversHelper {

    @Override
    public synchronized  <T> Object createDriver() {
        switch (WebHelper.init().getDriverConfig().browserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--windows-size=" + WebHelper.init().getDriverConfig().browserSize(),
                        "--no-sandbox",
                        "--incognito",
                        "--remote-allow-origins=*");
                if (WebHelper.init().getDriverConfig().headless()) {
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
