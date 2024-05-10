package commands;

import model.Route;

import java.util.Scanner;

/**
 * Класс команды вывода списка элементов коллекции
 */
public class Show extends Command {
    public Show(){
        setTitle("show");
        setDescription(" -  вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    public Route execute(Scanner scanner) {
        return null;
    }
}
