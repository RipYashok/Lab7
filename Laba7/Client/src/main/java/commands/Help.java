package commands;

import model.Route;

import java.util.Scanner;

public class Help extends Command{
    public Help(){
        setTitle("help");
        setDescription(" - вывести справку по доступным командам");
    }

    public Route execute(Scanner scanner) {
        return null;
    }
}
