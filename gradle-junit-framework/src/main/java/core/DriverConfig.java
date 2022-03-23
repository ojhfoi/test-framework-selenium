package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

@Log4j2
public class DriverConfig extends Base {

    public void driverCreate () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--no-sandbox", "--windows-size=1280,1024");
        driver = new ChromeDriver(options);
    }

    public void driverNavigate ( String url ) {
        driver.navigate().to(url);
    }

    public void driverNavigate ( URL url ) {
        driver.navigate().to(url);
    }

    public void driverClose () {
        if ( driver != null ) {
            WebDriverManager.chromedriver().quit();
        }
    }

}
