package clientChannel;

import clientChannel.utils.MD2;
import clientChannel.utils.Packet;
import clientChannel.utils.User;
import commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ClientManeger {

    public static Object run(Scanner scanner, Packet packet){
        while (true) {
            try {
                String string = scanner.nextLine().trim();
                List<String> input = new ArrayList<>(Arrays.asList(string.split(" ")));
                if (input.size() == 1) {
                    input.add("null");
                }
                packet.setCommand((Command) CreateCommands.commands().get(input.get(0)));
                packet.setRoute(((Command) CreateCommands.commands().get(input.get(0))).execute(scanner));
                packet.setValue(input.get(1));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchElementException e) {
                scanner.close();
                System.out.println("Ошибка ввода: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Данной команды не существует. Введите help, чтобы увидеть перечень существующих команд.");
            }
        }
        return packet;
    }
}
