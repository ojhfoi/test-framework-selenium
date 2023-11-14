package ojhfoi.summerMobile.mobileDrivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import ojhfoi.core.configs.driversConfig.DriversHelper;
import ojhfoi.summerMobile.mobileHelpers.MobileHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverCreator extends DriversHelper {

    @Override
    public <T> Object createDriver() {
        switch (MobileHelper.init().getDriverConfig().browserName()) {
            case "android":
                UiAutomator2Options androidOptions = new UiAutomator2Options();
                androidOptions.setApp(MobileHelper.init().getDriverConfig().apkPath());
                URL url;
                try {
                    url = new URL(MobileHelper.init().getDriverConfig().url());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                return new AppiumDriver(url, androidOptions);
            default:
                return null;
        }
    }
}
