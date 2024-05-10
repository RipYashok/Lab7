package sereverChannel.utils;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBScan {
    public static ArrayList<Route> scan(Connection connection) throws SQLException {
        ArrayList<Route> collection = new ArrayList<>();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM routes";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Coordinates coordinates = new Coordinates();
            coordinates.setX(resultSet.getFloat("CorX"));
            coordinates.setY(resultSet.getLong("CorY"));

            Location from = new Location();
            from.setName(resultSet.getString("fromName"));
            from.setX(resultSet.getLong("fromX"));
            from.setY(resultSet.getDouble("fromY"));
            from.setZ(resultSet.getInt("fromZ"));

            Location to = new Location();
            to.setName(resultSet.getString("toName"));
            to.setX(resultSet.getLong("toX"));
            to.setY(resultSet.getDouble("toY"));
            to.setZ(resultSet.getInt("toZ"));

            Route route = new Route();
            route.setId(resultSet.getInt("id"));
            route.setName(resultSet.getString("name"));
            route.setCoordinates(coordinates);
            route.setCreationDate((resultSet.getDate("creationDate")).toLocalDate());
            route.setFrom(from);
            route.setTo(to);
            route.setDistance(resultSet.getLong("distance"));
            route.setUser_name(resultSet.getString("user_name"));
            route.setPassword(resultSet.getString("password"));

            collection.add(route);
        }
        resultSet.close();
        statement.close();
        return collection;
    }
}