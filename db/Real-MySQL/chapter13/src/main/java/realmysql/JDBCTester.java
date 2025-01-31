package realmysql;

public class JDBCTester {

    public static void main(String[] args) {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.createStatement()) {

            try (var rs = statement.executeQuery("select * from employees limit 2;")) {
                while (rs.next()) {
                    var log = "[%s] [%s]".formatted(rs.getString(1), rs.getString("first_name"));
                    System.out.println(log);
                }
            }

            connection.setAutoCommit(false);
            int count = statement.executeUpdate("update employees set first_name = 'Lee' where emp_no = 10001");
            if (count == 1) {
                System.out.println("사원명이 변경되었습니다.");
                connection.commit();
            } else {
                System.out.println("사원을 찾을 수 없습니다.");
                connection.rollback();
            }
        } catch (Exception e) {
            System.out.println("Can't open connection, because" + e.getMessage());
            e.printStackTrace();
        }
    }
}
