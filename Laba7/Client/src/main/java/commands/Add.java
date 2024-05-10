package commands;

import model.Coordinates;
import model.Location;
import model.Route;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Scanner;

public class Add extends Command {

    public Add() {
        setTitle("add");
        setDescription(" - добавить новый элемент в коллекцию");
    }

    public void chooseName(Route route, Scanner scanner) {

        System.out.println("Введите название маршрута:");
        String name = scanner.nextLine();
        while (StringUtils.isBlank(name)) {
            System.out.println("Некорректное название маршрута. Введите название повторно:");
            name = scanner.nextLine();
        }
        route.setName(name);
    }

    public void chooseCoordinates(Route route, Scanner scaner) {
        Coordinates coordinates = new Coordinates();
        System.out.println("Введите координату X для маршрута, она должна быть больше -580:");
        while (true) {
            try {
                String x = scaner.nextLine();
                while (StringUtils.isBlank(x) || Float.parseFloat(x) <= -580) {
                    System.out.println("Некорректна введена координата. Введите повторно:");
                    x = scaner.nextLine();
                }
                coordinates.setX(Float.parseFloat(x));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Координата X является числом!");
            }
        }
        System.out.println("Введите координату Y для маршрута, она должна быть больше -339:");
        while (true) {
            try {
                String y = scaner.nextLine();
                while (StringUtils.isBlank(y) || Long.parseLong(y) <= -339) {
                    System.out.println("Некорректна введена координата. Введите повторно:");
                    y = scaner.nextLine();
                }
                coordinates.setY(Long.parseLong(y));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Координата Y является целым числом!");
            }
        }
        route.setCoordinates(coordinates);
    }

    public void chooseLocation(Route route, Scanner scanner) throws IOException {
        String direction;
        for (int i = 0; i <= 1; i++) {
            Location location = new Location();
            if (i == 0) {
                direction = "отправления";
            } else {
                direction = "назначения";
            }
            System.out.println("Введите название локации " + direction + ":");
            String name = scanner.nextLine();
            if (StringUtils.isBlank(name)) {
                location.setName("");
                location.setX(0L);
                location.setY(0.0);
                location.setZ(0);
                continue;
            }
            location.setName(name);
            System.out.println("Введите координату X локации " + direction + ":");
            while (true) {
                try {
                    String x = scanner.nextLine();
                    while (StringUtils.isBlank(x)) {
                        System.out.println("Некорректна введена координата. Введите повторно:");
                        x = scanner.nextLine();
                    }
                    location.setX(Long.parseLong(x));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Координата X является целым числом!");
                }
            }
            System.out.println("Введите координату Y локации " + direction + ":");
            while (true) {
                try {
                    String y = scanner.nextLine();
                    while (StringUtils.isBlank(y)) {
                        System.out.println("Некорректна введена координата. Введите повторно:");
                        y = scanner.nextLine();
                    }
                    location.setY(Double.parseDouble(y));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Координата Y является числом!");
                }
            }
            System.out.println("Введите координату Z локации " + direction + ":");
            while (true) {
                try {
                    String z = scanner.nextLine();
                    while (StringUtils.isBlank(z)) {
                        System.out.println("Некорректна введена координата. Введите повторно:");
                        z = scanner.nextLine();
                    }
                    location.setZ(Integer.parseInt(z));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Координата Z является целым числом!");
                }
            }
            if (i == 0) {
                route.setFrom(location);

            } else {
                route.setTo(location);
            }
        }
    }

    public void chooseDistance(Route route, Scanner scanner) {
        System.out.println("Введите длину маршрута, она должна быть больше 1:");
        while (true) {
            try {
                String distance = scanner.nextLine();
                while (StringUtils.isBlank(distance) || Long.parseLong(distance) <= 1) {
                    System.out.println("Некорректна введена дистанция. Введите повторно:");
                    distance = scanner.nextLine();
                }
                route.setDistance(Long.parseLong(distance));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Дистанция является целым числом!");
            }
        }
    }

    @Override
    public Route execute(Scanner scanner) throws IOException {
        Route route = new Route();
        chooseName(route, scanner);
        chooseCoordinates(route, scanner);
        chooseLocation(route, scanner);
        chooseDistance(route, scanner);
        return route;
    }
}
