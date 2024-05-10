package commands;

import model.Route;

import java.io.IOException;
import java.util.Scanner;

public class Average_of_distance extends Command{
    public Average_of_distance(){
        setTitle("average_of_distance");
        setDescription(" - вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    public Route execute(Scanner scanner) throws IOException {
        return null;
    }
}
