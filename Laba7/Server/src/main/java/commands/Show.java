package commands;
import sereverChannel.utils.User;
import model.Route;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Класс команды вывода списка элементов коллекции
 */
public class Show extends Command {
    public Show(){
        setTitle("show");
        setDescription(" -  вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        String answer = "";
        for (Route element : collection){
            answer += "Id: " + element.getId() + "\n" +
                    "Название маршрута: " + element.getName() + "\n" +
                    "Координаты: x = " + element.getCoordinates().getX() + "; y = " + element.getCoordinates().getY() + "\n" +
                    "Дата создания: " + element.getCreationDate() + "\n" +
                    "Место отправки:" + element.getFrom().getName() + "\n" +
                    "Название: " + element.getFrom().getName() + "\n" +
                    "Координаты: x = " + element.getFrom().getX() + "; y = " + element.getFrom().getY() + "; z = " + element.getFrom().getZ() + "\n" +
                    "Место назначения:" + "\n" +
                    "Название: " + element.getTo().getName() + "\n" +
                    "Координаты: x = " + element.getTo().getX() + "; y = " + element.getTo().getY() + "; z = " + element.getTo().getZ() + "\n" +
                    "Расстояние: " + element.getDistance() + "\n" +
                    "Пользователь:" + element.getUser_name() + "\n";
        }
        if (collection.isEmpty()){
            answer += "Коллекция пуста.";
        }
        return answer;
    }
}
