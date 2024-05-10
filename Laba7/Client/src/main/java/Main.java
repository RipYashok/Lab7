import clientChannel.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        client.Client(scanner);
    }

}