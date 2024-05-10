package commands;

import model.Route;

import java.util.Scanner;

/**
 * Класс команды завершения программы
 */
public class Exit extends Command{

    public Exit(){
        setTitle("exit");
        setDescription(" - завершить программу (без сохранения в файл)");
    }


    public Route execute(Scanner scanner) {
        System.out.println("Завершение работы");
        System.exit(0);
        return null;
    }
}
