package commands;

import model.Route;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Clear extends Command{
    public Clear(){
        setTitle("clear");
        setDescription(" - очистить коллекцию");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        collection.clear();
        return "Коллекция очищена.";
    }
}
