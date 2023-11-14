package ojhfoi.core.configs.testsConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "file:${user.dir}/src/main/resources/testDataConfigs/${testData.config}.properties",
//        "system:${testData.config}"
})
public interface TestdataConfig extends Config, Reloadable {
    @Key("projectUrl")
    String projectUrl();
    @Key("login")
    String login();
    @Key("pwd")
    String pwd();
}
