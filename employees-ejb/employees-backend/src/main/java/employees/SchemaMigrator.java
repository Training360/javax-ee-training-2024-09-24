package employees;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
@Slf4j
@TransactionManagement(TransactionManagementType.BEAN)
@Startup
public class SchemaMigrator {

    @Resource(mappedName = "java:/jdbc/EmployeeDS")
    private DataSource dataSource;

    @PostConstruct
    public void migrate() {
        log.info("Schema migration started");
        try (var connection = dataSource.getConnection()) {
            var database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            var liquibase = new Liquibase("db/changeLog.yaml", new ClassLoaderResourceAccessor(), database);
            var contexts = new Contexts();
            liquibase.update(contexts);
        } catch (SQLException | LiquibaseException e) {
            throw new IllegalStateException("Could not migrate database", e);
        }
    }
}
