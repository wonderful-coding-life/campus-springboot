import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        var main = new Main();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "myuser", "mypass");
        main.selectAll(connection);
        var member = main.selectMemberById(connection, 1L);
        log.info("회원 {}", member);
        member = main.selectMemberByIdUsingPreparedStatement(connection, 2L);
        log.info("회원 {}", member);
        connection.close();
    }

    private void selectAll(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM member");
        while (resultSet.next()) {
            var member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            log.info("회원 {}", member);
        }
    }

    private Member selectMemberById(Connection connection, Long id) throws SQLException {
        var query = "SELECT * FROM member WHERE id=" + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
        } else {
            return null;
        }
    }

    private Member selectMemberByIdUsingPreparedStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member WHERE id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
        } else {
            return null;
        }
    }
}
