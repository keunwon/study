package realmysql;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class JDBCPaging {

    public static void main(String[] args)  throws Exception {
        final var query = "insert into tb_article3 (article_id, write_memberid, write_dttm) values (null, ?, ?)";
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.now();

        try (var connection = JDBCConnector.getConnection();
             var statement = connection.createStatement();
             var preparedStatement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);

            for (int writeMemberId = 1; writeMemberId <= 10; writeMemberId++) {
                for (int i = 1; i <= 10; i++) {
                    target = target.plusMinutes(1);
                    Timestamp timestamp = Timestamp.valueOf(target);

                    preparedStatement.setInt(1, writeMemberId);
                    preparedStatement.setTimestamp(2, timestamp);

                    preparedStatement.addBatch();
                }

                System.out.println("배치 INSERT");
                preparedStatement.executeBatch();
            }

            System.out.println(Duration.between(start, LocalDateTime.now()).getSeconds());

            connection.commit();
        }
    }
}
