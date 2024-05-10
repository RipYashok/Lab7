package commands;

import clientChannel.utils.MD2;
import clientChannel.utils.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Route;

import java.io.IOException;
import java.util.Scanner;

public class Register{

    public User execute(Scanner scanner) throws IOException {
        System.out.println("Зарегистраруйтесь или войдите в аккаунт");
        System.out.println("Введите логин:");
        User user = new User();
        user.setName(scanner.nextLine());
        System.out.println("Введите пароль:");
        user.setPassword(MD2.hashing(scanner.nextLine()));
        return user;
    }
}
