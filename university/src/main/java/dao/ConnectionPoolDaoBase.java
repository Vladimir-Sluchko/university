package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolDaoBase {
    private static DataSource dataSource;

    static {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Проверь имя драйвера!!!!", e);
        }
        pool.setJdbcUrl("jdbc:postgresql://localhost:5432/base_university");
        pool.setUser("postgres");
        pool.setPassword("postgres");

        dataSource = pool;
    }
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void close() throws Exception{
        DataSources.destroy(dataSource);
    }
}
