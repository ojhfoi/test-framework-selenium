package ojhfoi.summerMobile.mobileHelpers;

import io.qameta.allure.Step;
import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.customExtensions.Test;
import ojhfoi.core.helpers.HelperUtils;
import ojhfoi.summerMobile.mobileDrivers.DriverCreator;
import org.aeonbits.owner.ConfigFactory;

import java.io.FileOutputStream;

public class MobileHelper extends HelperUtils {
    private static volatile MobileHelper instance;
    private static final Object mutex = new Object();

    private static final ThreadLocal<HelperStorage> holder = new ThreadLocal<HelperStorage> () {

        @Override
        protected HelperStorage initialValue() {
            return new HelperStorage();
        }
    };

    public static synchronized MobileHelper init () {
        MobileHelper result = holder.get().getHelpers();
        Class[] ca = new SecurityManager() {
            public Class[] getClassContext () {
                return super.getClassContext();
            }
        }.getClassContext();
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
                    result = new MobileHelper();
                    result.driverHelper = new DriverCreator();
                    result.configName = getAnnotationValue(ca, callerMethod);
                    ConfigFactory.setProperty("browser.config", result.configName);
                    result.driverConfig = ConfigFactory.create(DriverConfig.class);
                    holder.get().setHelpers(result);
                }
            }
        }
        return result;
    }

    private static String getAnnotationValue(Class[] classes, String methodName) {
        String value = "";
        try {
            if (classes[2].getMethod(methodName).isAnnotationPresent(Test.class)) {
                value = classes[2].getMethod(methodName).getAnnotation(Test.class).browserConfig();
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    @Step("Create instance driver")
    @Override
    public void tearUp() {
        init().getInstanceDriver();
    }

    @Step("Close instance driver")
    @Override
    public void tearDown() {
        if (init().getInstanceDriver() != null) {
            init().getInstanceDriver().quit();
            driver = null;
        }
    }
}
