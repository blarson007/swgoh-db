package swgoh;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    private final Environment environment;

    public ApplicationConfig(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setJdbcUrl(
                generateUrl(
                        environment.getProperty("db.host"),
                        environment.getProperty("db.port", Integer.class, 3306),
                        environment.getProperty("db.database")));

        return new HikariDataSource(hikariConfig);
    }

    private String generateUrl(String host, int port, String database) {
        return String.format("jdbc:mysql://%s:%d/%s", host, port, database);
    }
}
