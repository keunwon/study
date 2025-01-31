package realmysql;

public class JDBCPrePared {

    public static void main(String[] args) {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement("select * from employees where emp_no = ?")) {

            statement.setInt(1, 10001);

            try (var rs = statement.executeQuery()) {
                while (rs.next()) {
                    System.out.println("First name: " + rs.getString("first_name"));
                }
            }

            statement.setInt(1, 10002);
            try (var rs = statement.executeQuery()) {
                while (rs.next()) {
                    System.out.println("First name: " + rs.getString("first_name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
