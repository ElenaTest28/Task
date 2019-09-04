package helpers;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:helpers.properties" })
public interface ProjectConfig extends Config {

        String baseUrl();
        String loginPage();
}
