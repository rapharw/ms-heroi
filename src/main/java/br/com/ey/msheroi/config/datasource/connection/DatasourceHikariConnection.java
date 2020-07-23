package br.com.ey.msheroi.config.datasource.connection;

import br.com.ey.msheroi.config.datasource.vo.Credential;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public class DatasourceHikariConnection {

    private static final String SPRING_DATASOURCE = "spring.datasource-";

    private HikariConfig getConnection(Environment environment, String contextDatabase, Credential credential){

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(credential.getUsername());
        hikariConfig.setPassword(credential.getPassword());

        hikariConfig.setJdbcUrl(environment.getProperty(SPRING_DATASOURCE + contextDatabase +                           ".url"));
        hikariConfig.setDriverClassName(environment.getProperty(SPRING_DATASOURCE + contextDatabase +                   ".driver-class-name"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty(SPRING_DATASOURCE + contextDatabase +  ".hikari.maximum-pool-size")));
        hikariConfig.setMinimumIdle(Integer.parseInt(environment.getProperty(SPRING_DATASOURCE + contextDatabase +      ".hikari.minimum-idle")));
        hikariConfig.setConnectionTestQuery(environment.getProperty(SPRING_DATASOURCE + contextDatabase +               ".hikari.connection-test-query"));
        hikariConfig.setReadOnly(Boolean.parseBoolean(environment.getProperty(SPRING_DATASOURCE + contextDatabase +     ".hikari.read-only")));
        hikariConfig.setIdleTimeout(Long.parseLong(environment.getProperty(SPRING_DATASOURCE + contextDatabase +        ".hikari.idle-timeout")));
        hikariConfig.setConnectionTimeout(Long.parseLong(environment.getProperty(SPRING_DATASOURCE + contextDatabase +  ".hikari.connection-timeout")));

        return hikariConfig;
    }

    public HikariConfig getConnectionDatasource(Environment environment, String contextDatabase){
        Credential credential = Credential.builder()
                .username(environment.getProperty(SPRING_DATASOURCE + contextDatabase + ".username"))
                .password(environment.getProperty(SPRING_DATASOURCE + contextDatabase + ".password"))
                .build();

        return getConnection(environment, contextDatabase, credential);
    }

    public Map<String, String> getProperties(Environment environment, String contextDatabase) {
        Map<String, String> props = new HashMap<>();

        String dialect = environment.getProperty(SPRING_DATASOURCE + contextDatabase + ".dialect");
        props.put("spring.jpa.database-platform", dialect);
        props.put("hibernate.dialect", dialect);

        return props;
    }
}
