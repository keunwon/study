package realmysql;

public class PointTransferAction {

    public void transferByNotAutoCommit(String fromId, String toId, int howMuch) {

        try (var connection = JDBCConnector.getConnection();
             var statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            statement.executeUpdate(
                    "update user_point set point=point-%d where user_id=%s".formatted(howMuch, fromId));

            statement.executeUpdate(
                    "update user_point set point=point+%d where user_id=%s".formatted(howMuch, toId));
            connection.commit();

        } catch (Exception e) {

        }
    }

    public void transferByAutoCommit(String fromId, String toId, int howMuch) {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.createStatement()) {
            statement.execute("begin");

            statement.executeUpdate(
                    "update user_point set point=point-%d where user_id=%s".formatted(howMuch, fromId));

            statement.executeUpdate(
                    "update user_point set point=point+%d where user_id=%s".formatted(howMuch, toId));

            connection.rollback();
        } catch (Exception e) {
        }
    }
}
