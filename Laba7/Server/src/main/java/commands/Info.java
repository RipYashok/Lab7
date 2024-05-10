package commands;

import model.Route;
import sereverChannel.utils.User;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Info extends Command{
    public Info(){
        setTitle("info");
        setDescription(" - вывести в стандартный поток вывода информацию о коллекции");
    }


    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) {
        String answer = "";
        Date initTime = new Date();
        answer = "Тип коллекции: ArrayList" + "\n" +
                "Дата инициализации: " + initTime + "\n" +
                "Количество элементов: " + collection.size();
        return answer;
    }
}
