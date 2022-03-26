package realmysql;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {

    public static Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final var driver = "com.mysql.jdbc.Driver"; // 1
        final var url = "jdbc:mysql://127.0.0.1:3306/employees?rewriteBatchedStatements=true&useServerPrepStmts=false"; // 2
        final var uid = "root";
        final var password = "root";

        Class.forName(driver).getDeclaredConstructor().newInstance(); // 3
        return DriverManager.getConnection(url, uid, password); // 4
    }
}
