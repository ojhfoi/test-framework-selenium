package ojhfoi.core.configs.driversConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "file:${user.dir}/src/main/resources/browserConfigs/${browser.config}.properties",
        "file:${user.dir}/src/main/resources/${browser.config}.properties",
//        "system:browser.config"
})
public interface DriverConfig extends Config,Reloadable {
//    @DefaultValue("empty")
    @Key("browser.url")
    String url ();
    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName ();
    @Key("browser.size")
    @DefaultValue("1280x800")
    String browserSize ();
    @Key("browser.version")
    String browserVersion ();
    @Key("browser.headless")
    @DefaultValue("false")
    boolean headless ();
    @Key("apk.path")
    String apkPath();
}
