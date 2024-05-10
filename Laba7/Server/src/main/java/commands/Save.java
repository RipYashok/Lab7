package commands;

import model.Route;


import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Save extends Command{

    public Save(){
        setTitle("save");
        setDescription(" - сохранить коллекцию в файл");
    }

    public void execute(List<Route> collection, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE routes RESTART IDENTITY");
        statement.close();
        for (Route route : collection) {
            String sql = "INSERT INTO routes (name, CorX, CorY, creationDate, fromName, fromX, fromY, fromZ, toName, toX, toY, toZ, distance, user_name, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, route.getName());
                preparedStatement.setDouble(2, route.getCoordinates().getX());
                preparedStatement.setLong(3, route.getCoordinates().getY());
                preparedStatement.setDate(4, Date.valueOf(route.getCreationDate()));
                preparedStatement.setString(5, route.getFrom().getName());
                preparedStatement.setLong(6, route.getFrom().getX());
                preparedStatement.setDouble(7, route.getFrom().getY());
                preparedStatement.setInt(8, route.getFrom().getZ());
                preparedStatement.setString(9, route.getTo().getName());
                preparedStatement.setLong(10, route.getTo().getX());
                preparedStatement.setDouble(11, route.getTo().getY());
                preparedStatement.setInt(12, route.getTo().getZ());
                preparedStatement.setLong(13, route.getDistance());
                preparedStatement.setString(14, route.getUser_name());
                preparedStatement.setString(15, route.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("Изменения в коллекции сохранены.");
    }
}
