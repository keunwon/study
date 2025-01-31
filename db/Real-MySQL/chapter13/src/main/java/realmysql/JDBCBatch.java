package realmysql;

import java.util.List;

public class JDBCBatch {

    public static void main(String[] args) throws Exception {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.createStatement();
             var preparedStatement = connection.prepareStatement("insert into test_employees values (?, ?, ?)")) {

            List<Employees> empList = List.of(new Employees(1000000, "Brandon", "Lee"),
                    new Employees(1000001, "Brandon", "Lee"),
                    new Employees(1000002, "Brandon", "Lee"));

            for (Employees employees : empList) {
                preparedStatement.setInt(1, employees.getEmpNo());
                preparedStatement.setString(2, employees.getFirstName());
                preparedStatement.setString(3, employees.getLastName());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        }
    }

    private record Employees(int empNo, String lastName, String firstName) {

        public int getEmpNo() {
            return empNo;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }
    }
}
