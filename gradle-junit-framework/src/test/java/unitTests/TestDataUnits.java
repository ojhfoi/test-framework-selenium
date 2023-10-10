package unitTests;

import ojhfoi.core.configs.testsConfig.TestdataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDataUnits {

    @Test
    @DisplayName("Check default test data")
    public void checkDefaultTestData () {
        System.setProperty("testData.config", "");
        TestdataConfig tdConf = ConfigFactory.create(TestdataConfig.class);
        Assertions.assertAll( "Check empty testData Config",
                () -> {Assertions.assertNull(tdConf.projectUrl(), "Check projectUrl");},
                () -> {Assertions.assertNull(tdConf.login(), "Check login");},
                () -> {Assertions.assertNull(tdConf.pwd(), "Check password");}
        );
    }

    @Test
    @DisplayName("Check test data from file")
    public void checkTestDataFromFile () {
        System.setProperty("testData.config", "testDataConfig");
        TestdataConfig tdConf = ConfigFactory.create(TestdataConfig.class, System.getProperties());
        Assertions.assertAll( "Check empty testData Config",
                () -> {Assertions.assertEquals("https://myTestProject.org", tdConf.projectUrl(), "Check projectUrl");},
                () -> {Assertions.assertEquals("testLogin", tdConf.login(), "Check login");},
                () -> {Assertions.assertEquals("testPassword", tdConf.pwd(), "Check password");}
        );
    }

}
