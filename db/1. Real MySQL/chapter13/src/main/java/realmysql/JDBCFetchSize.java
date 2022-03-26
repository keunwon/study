package realmysql;

import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCFetchSize {

    public static void main(String[] args) {
        try (var connection = JDBCConnector.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

            statement.setFetchSize(Integer.MIN_VALUE);

            try (var rs = statement.executeQuery("select * from employees")) {
                while (rs.next()) {
                    var log = "[%s] [%s]".formatted(rs.getString(1), rs.getString("first_name"));
                    System.out.println(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
