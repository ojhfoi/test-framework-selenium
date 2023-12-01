package ojhfoi.summerWeb.webHelpers;

import ojhfoi.core.configs.driversConfig.DriverConfig;
import ojhfoi.core.configs.testsConfig.TestdataConfig;
import ojhfoi.core.customExtensions.Test;
import ojhfoi.core.helpers.HelperUtils;
import ojhfoi.summerWeb.webDrivers.DriverCreator;
import org.aeonbits.owner.ConfigFactory;

public class WebHelper extends HelperUtils {

//    private static volatile WebHelper instance;
    private static final Object mutex = new Object();

    private static final ThreadLocal<HelperStorage> holder = new ThreadLocal<HelperStorage> () {

        @Override
        protected HelperStorage initialValue() {
            return new HelperStorage();
        }
    };


    public static synchronized WebHelper init () {
        WebHelper result = holder.get().getHelpers();
        var ca = new SecurityManager() {
            public Class[] getClassContext () {
                return super.getClassContext();
            }
        }.getClassContext();
        if (result == null) {
            synchronized (mutex) {
                result = holder.get().getHelpers();
                if (result == null) {
                    String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
                    result = new WebHelper();
                    result.driverHelper = new DriverCreator();
                    result.configName = getAnnotationBrowserConfigValue(ca, callerMethod);
                    result.testDataConfigName = getAnnotationTestDataConfigValue(ca, callerMethod);
                    ConfigFactory.setProperty("browser.config", result.configName);
                    ConfigFactory.setProperty("testData.config", result.testDataConfigName);
                    result.driverConfig = ConfigFactory.create(DriverConfig.class);
                    result.testData = ConfigFactory.create(TestdataConfig.class);
                    holder.get().setHelpers(result);
                }
            }
        }
        return result;
    }

    private static String getAnnotationBrowserConfigValue(Class[] classes, String methodName) {
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

    private static String getAnnotationTestDataConfigValue(Class[] classes, String methodName) {
        String value = "";
        try {
            if (classes[2].getMethod(methodName).isAnnotationPresent(Test.class)) {
                value = classes[2].getMethod(methodName).getAnnotation(Test.class).testConfig();
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    @Override
    public void tearUp() {
        init().getInstanceDriver();
    }

    @Override
    public void tearDown() {
        if (getInstanceDriver() != null) {
            getInstanceDriver().quit();
            driver = null;
        }
    }
}
