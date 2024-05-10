package commands;

import model.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Remove_by_id extends Command{
    public Remove_by_id(){
        setTitle("remove_by_id");
        setDescription(" {id} - удалить элемент из коллекции по его id");
    }
    public Route execute(Scanner scanner) throws IOException {
        return null;
    }
}
