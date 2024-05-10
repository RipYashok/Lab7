package commands;

import model.Route;
import sereverChannel.utils.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Класс команды завершения программы
 */
public class Exit extends Command{

    public Exit(){
        setTitle("exit");
        setDescription(" - завершить программу (без сохранения в файл)");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) throws IOException {
        return null;
    }
}
