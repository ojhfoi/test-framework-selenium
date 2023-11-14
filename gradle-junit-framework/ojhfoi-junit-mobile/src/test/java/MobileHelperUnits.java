import ojhfoi.core.customExtensions.Test;
import ojhfoi.summerMobile.mobileHelpers.MobileHelper;
import org.junit.jupiter.api.Assertions;

public class MobileHelperUnits {

    @Test(browserConfig = "androidTest")
    public void androidDriverTest () {
        MobileHelper.init().tearUp();
        Assertions.assertNotNull(MobileHelper.init().getInstanceDriver());
    }
}
