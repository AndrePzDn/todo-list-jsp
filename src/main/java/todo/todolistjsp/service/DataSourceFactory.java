package todo.todolistjsp.service;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceFactory {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/tododb");
        config.setUsername("test");
        config.setPassword("test");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(10000);
        config.setAutoCommit(true);
        config.setValidationTimeout(5000);
        config.setConnectionTestQuery("SELECT 1");
        dataSource = new HikariDataSource(config);

    }

    public static DataSource createDataSource() {
        return dataSource;
    }

}
