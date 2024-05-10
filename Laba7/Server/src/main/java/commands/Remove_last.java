package commands;

import model.Route;
import sereverChannel.UserManager;
import sereverChannel.utils.User;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Remove_last extends Command{
    public Remove_last(){
        setTitle("remove_last");
        setDescription(" - удалить последний элемент из коллекции");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        String answer = UserManager.check(user, collection.get(collection.size() - 1));
        if (answer.equals("1")){
            collection.remove(collection.size()-1);
            answer = "Удален последний элемент коллекции.";
        }
        return answer;
    }
}
