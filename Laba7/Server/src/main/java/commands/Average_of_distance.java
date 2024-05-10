package commands;

import model.Route;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Average_of_distance extends Command{
    public Average_of_distance(){
        setTitle("average_of_distance");
        setDescription(" - вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        double avg = collection.stream()
                .mapToDouble(Route::getDistance)
                .average()
                .orElse(0); // Если коллекция пустая, возвращаем 0
        return "Среднее значение дистанции в коллекции: " + avg;
    }
}
