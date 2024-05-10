package commands;

import model.Route;

import java.util.Scanner;

public class Remove_last extends Command{
    public Remove_last(){
        setTitle("remove_last");
        setDescription(" - удалить последний элемент из коллекции");
    }

    public Route execute(Scanner scanner) {
        return null;
    }
}
