package commands;

import model.Route;
import sereverChannel.CreateCommands;
import sereverChannel.utils.User;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class Help extends Command{
    public Help(){
        setTitle("help");
        setDescription(" - вывести справку по доступным командам");
    }

    public String execute(List<Route> collection, String value, Route route, User user, Connection connection) {
        Map<String, Command> commandList = CreateCommands.commands();

        return commandList.values().stream()
                .map(command -> command.getTitle() + command.getDescription())
                .reduce("", (acc, str) -> acc + str + "\n");
    }
}
