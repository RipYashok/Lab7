package commands;

import model.Route;

import java.util.Scanner;

public class Clear extends Command{
    public Clear(){
        setTitle("clear");
        setDescription(" - очистить коллекцию");
    }

    public Route execute(Scanner scanner) {
        return null;
    }
}
