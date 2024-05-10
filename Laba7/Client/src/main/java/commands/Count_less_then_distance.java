package commands;

import model.Route;

import java.io.IOException;
import java.util.Scanner;

public class Count_less_then_distance extends Command{
    public Count_less_then_distance(){
        setTitle("count_less_then_distance");
        setDescription("{distance} - вывести количество элементов, значение поля distance которых меньше заданного");
    }

    public Route execute(Scanner scanner) throws IOException {
        return null;
    }
}
