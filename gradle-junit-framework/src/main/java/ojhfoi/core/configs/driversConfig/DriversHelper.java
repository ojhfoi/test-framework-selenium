package ojhfoi.core.configs.driversConfig;

import org.aeonbits.owner.ConfigFactory;

public class DriversHelper {

    protected DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());

    public <T extends Object> Object createDriver () {
        return null;
    }

}
