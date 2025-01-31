package realmysql;

import java.sql.Types;

public class JDBCCallableStatement {

    public static void main(String[] args) throws Exception {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareCall("call sp_multiple_resultset(?, ?)")) {

            statement.setInt(1, 10001);
            statement.registerOutParameter(2, Types.VARCHAR);

            boolean isResultSet = statement.execute();
            String empNoList = statement.getString(2);
            System.out.println(">> 사원 번호:" + empNoList);

            if (!isResultSet) {
                System.out.println(">> 첫 번째 결과가 ResultSet(이) 아닙니다.");
                return;
            }

            System.out.println(">> 첫 번째 결과 셋:");
            try (var rs = statement.getResultSet()) {
                while (rs.next()) {
                    System.out.println(rs.getString("first_name") + ", " + rs.getString("last_name"));
                }
            }

            isResultSet = statement.getMoreResults();
            if (!isResultSet) {
                System.out.println(">> 두 번째가 결과가 ResultSet(이) 아닙니다");
                return;
            }

            System.out.println(">> 두 번째 결과 셋:");
            try (var rs = statement.getResultSet()) {
                while (rs.next()) {
                    System.out.println(rs.getString("first_name") + ", " + rs.getString("last_name"));
                }
            }
        }
    }
}
