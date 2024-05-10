package commands;

import model.Route;

import java.io.IOException;
import java.util.Scanner;

public class Min_by_coordinates extends Command{
    public Min_by_coordinates(){
        setTitle("min_by_coordinates");
        setDescription(" - вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    public Route execute(Scanner scanner) throws IOException {
        return null;
    }
}
