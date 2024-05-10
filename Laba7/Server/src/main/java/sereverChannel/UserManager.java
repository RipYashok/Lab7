package sereverChannel;

import model.Route;
import sereverChannel.utils.User;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {

    public static String register(User user, Connection connection) {
        String answer = "";
        try {
            // SQL запрос для выбора всех элементов таблицы, сравнение которых производится с comparisonValue
            String sql = "SELECT * FROM users WHERE user_name = ? and password = ?";

            // Подготовка SQL запроса
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            // Выполнение запроса
            ResultSet resultSet = statement.executeQuery();

            // Обработка результатов запроса
            if (resultSet.next()) {
                answer = "Пользователь авторизован.";
            } else {
                String query = "INSERT INTO USERS (user_name, password) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();
                answer = "Пользователь зарегистрирован и авторизован.";
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    public static String check(User user, Route route){
        String flag = "Недостаточно прав.";
        if (user.getName().equals(route.getUser_name()) && user.getPassword().equals(route.getPassword())){
            flag = "1";
        }
        return flag;
    }
}
