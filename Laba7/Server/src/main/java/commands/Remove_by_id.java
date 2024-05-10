package commands;

import model.Route;
import sereverChannel.UserManager;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Remove_by_id extends Command{
    public Remove_by_id(){
        setTitle("remove_by_id");
        setDescription(" {id} - удалить элемент из коллекции по его id");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        String answer = "";
        try {
            int idValue = Integer.parseInt(value);
            answer = UserManager.check(user, collection.get(idValue-1));
            if (answer.equals("1")){
                Route foundRoute = collection.stream()
                        .filter(r -> r.getId().equals(idValue))
                        .findFirst()
                        .orElse(null);

                if (foundRoute != null) {
                    collection.remove(foundRoute);
                    answer = "Маршрут с Id = " + value + " удален";
                } else {
                    answer = "Маршрута с Id = " + value + " нет в коллекции";
                }
            }
        } catch (NumberFormatException e) {
            answer = "Id является числом!";
        }
        return answer;
    }
}
