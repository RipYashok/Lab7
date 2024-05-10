package commands;
import sereverChannel.utils.User;
import model.Route;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class Shuffle extends Command{
    public Shuffle(){
        setTitle("shuffle");
        setDescription(" - перемешать элементы коллекции в случайном порядке");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        Collections.shuffle(collection);
        return "Коллекция перемешана.";
    }
}
