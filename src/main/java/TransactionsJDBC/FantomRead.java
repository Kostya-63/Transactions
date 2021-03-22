package TransactionsJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FantomRead {
    static String hostName = "localhost";
    static String dbName = "transaction_test_db";
    static String userName = "Kostya";
    static String password = "carisma1997";
    static String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=UTC";

    public static void main(String[] args) throws SQLException, InterruptedException {
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM books");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }

            new OtherTransaction().start();
            Thread.sleep(500);

            ResultSet resultSet2 = statement.executeQuery("SELECT COUNT(*) FROM books");
            while (resultSet2.next()) {
                System.out.println(resultSet2.getInt(1));
            }
        }
    }

    static class OtherTransaction extends Thread {
        @Override
        public void run() {
            try (Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                statement.executeUpdate("INSERT INTO books (name) VALUES ('new value2')");
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
