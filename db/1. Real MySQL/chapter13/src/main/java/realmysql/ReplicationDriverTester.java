package realmysql;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReplicationDriverTester {

    public static void main(String[] args) throws Exception {
        final DataSource dataSource = prepareDatasource();
        final String query = "select now()";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            connection.setReadOnly(false);
            //statement.executeUpdate("delete from master_table where ...");
            connection.commit();

            connection.setReadOnly(true);
            /*try (var rs = statement.executeQuery("select fd1, fd2 from slave_table where ...")) {
                while (rs.next()) {
                    final var result = "[%s] [%s]".formatted(rs.getString("fd1"), rs.getString("fd2"));
                    System.out.println(result);
                }
            }*/

            closeDatasource(dataSource);
        }
    }

    public static DataSource prepareDatasource() throws Exception {
        final var driver = "com.mysql.jdbc.Driver";
        final var url = "jdbc:mysql:replication://master_host:3306,slave_host1:3306,slave_host2:3306/db_name";

        BasicDataSource ds = new BasicDataSource();
        ds.setMaxIdle(2);
        ds.setDriverClassName(driver);
        ds.setUsername("root");
        ds.setPassword("root");
        ds.addConnectionProperty("autoReconnect", "true");
        ds.addConnectionProperty("roundRobinLoadBalance", "true");
        ds.setValidationQuery("/* ping */ select 1");
        ds.setUrl(url);

        return ds;
    }

    public static void closeDatasource(DataSource ds) throws SQLException {
        BasicDataSource dataSource = (BasicDataSource) ds;
        dataSource.close();
    }
}
