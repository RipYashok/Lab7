package commands;

import model.Route;

import java.util.Scanner;

public class Info extends Command{
    public Info(){
        setTitle("info");
        setDescription(" - вывести в стандартный поток вывода информацию о коллекции");
    }


    public Route execute(Scanner scanner) {
        return null;
    }
}
