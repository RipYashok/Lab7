package commands;

import model.Route;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Count_less_then_distance extends Command{
    public Count_less_then_distance(){
        setTitle("count_less_then_distance");
        setDescription("{distance} - вывести количество элементов, значение поля distance которых меньше заданного");
    }
    public String execute(List<Route> collection, String value, Route route_value, User user, Connection connection) throws IOException {
        String answer = "";
        try {
            long distanceValue = Long.parseLong(value);

            long count = collection.stream()
                    .filter(route -> route.getDistance() < distanceValue)
                    .count();
            answer = "Количество элементов, у которых значение поля distance меньше " + value + ": " + count;
        }catch (NumberFormatException e){
            answer = "Нужно ввести дистанцию. Дистанция является целым числом!";
        }
        return answer;
    }
}
