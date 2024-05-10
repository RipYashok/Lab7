
package sereverChannel;

import commands.Command;
import commands.Exit;
import commands.Save;
import model.Route;
import sereverChannel.utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    // Размер пула потоков для отправки ответов
    private static final int SEND_POOL_SIZE = 5;
    private static final int PORT = 666;

    // Фиксированный пул потоков для отправки ответов
    private final ExecutorService sendExecutor = Executors.newFixedThreadPool(SEND_POOL_SIZE);

    public void Server(List<Route> collection, Connection connection) throws IOException {
        // Защищенная коллекция
        List<Route> synchronizedCollection = Collections.synchronizedList(collection);

        ByteBuffer buffer = ByteBuffer.allocate(1048576);
        DatagramChannel serverChannel = DatagramChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(PORT));
        Log.logger("Сервер запущен на порту " + PORT);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        ExecutorService requestExecutor = Executors.newCachedThreadPool();

        // Многопоточное чтение запросов
        requestExecutor.submit(() -> {
            while (true) {
                try {
                    SocketAddress clientAddress = serverChannel.receive(buffer);
                    if (clientAddress != null) {
                        buffer.flip();
                        byte[] receivedBytes = new byte[buffer.remaining()];
                        buffer.get(receivedBytes);
                        buffer.clear();

                        Packet packet = JsonDeserializer.deserialize(receivedBytes, Packet.class);
                        Runnable requestHandler = () -> handleRequest(buffer, packet, synchronizedCollection, serverChannel, clientAddress, connection);

                        // Запускаем обработку каждого запроса в отдельном потоке
                        new Thread(requestHandler).start();
                    } else {
                        Log.logger("Сервер ожидает запрос.");
                        Thread.sleep(5000);
                    }

                    // Обработка консольных команд
                    if (consoleReader.ready()) {
                        readTerminate(consoleReader, synchronizedCollection, connection);
                    }
                } catch (IOException | InterruptedException | SQLException e) {
                    Log.logger("Ошибка: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleRequest(ByteBuffer buffer, Packet packet, List<Route> collection, DatagramChannel serverChannel, SocketAddress clientAddress, Connection connection) {
        try {
            Answer answer = new Answer();
            answer.setAnswer(((Command) CreateCommands.commands().get(packet.getCommand().getTitle())).execute(collection, packet.getValue(), packet.getRoute(), packet.getUser(), connection));
            byte[] jsonBytes = JsonSerializer.serialize(answer);

// Добавляем отправку ответа в фиксированный пул потоков
            sendExecutor.submit(() -> {
                synchronized (buffer) {
                    try {
                        buffer.put(jsonBytes);
                        buffer.flip();
                        serverChannel.send(buffer, clientAddress);
                        buffer.clear();
                    } catch (IOException e) {
                        Log.logger("Ошибка отправки ответа: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                answer.clear();
            });
        } catch (Exception e) {
            Log.logger("Ошибка обработки запроса: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readTerminate(BufferedReader consoleReader, List<Route> collection, Connection connection) throws IOException, SQLException {
        Save save = new Save();
        Exit exit = new Exit();
        String consoleInput = consoleReader.readLine().trim();
        List<String> input = new ArrayList<>(Arrays.asList(consoleInput.split(" ")));

        if (input.get(0).equals(save.getTitle())) {
            save.execute(collection, connection);
        } else if (input.get(0).equals(exit.getTitle())) {
            save.execute(collection, connection);
            System.out.println("Завершение работы.");
            System.exit(0);
        }
    }
}