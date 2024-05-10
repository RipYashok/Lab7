package commands;

import model.Route;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Min_by_coordinates extends Command{
    public Min_by_coordinates(){
        setTitle("min_by_coordinates");
        setDescription(" - вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }


    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        String answer = "";
        Show show = new Show();

        Route minRoute = collection.stream()
                .min((r1, r2) -> {
                    double sumCoor1 = r1.getCoordinates().getX() + r1.getCoordinates().getY();
                    double sumCoor2 = r2.getCoordinates().getX() + r2.getCoordinates().getY();
                    return Double.compare(sumCoor1, sumCoor2);
                })
                .orElse(null);

        if (minRoute == null) {
            answer = "Коллекция пуста.";
            }
        else {
            ArrayList<Route> minRouteList = new ArrayList<>();
            minRouteList.add(minRoute);
            answer = show.execute(minRouteList, value, route, user, connection);
        }

        ArrayList<Route> minRouteList = new ArrayList<>();
        minRouteList.add(minRoute);

        return answer;
    }
}
