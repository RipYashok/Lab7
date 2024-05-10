package clientChannel;

import clientChannel.utils.Answer;
import clientChannel.utils.JsonDeserializer;
import clientChannel.utils.JsonSerializer;
import clientChannel.utils.Packet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {

    public void Client(Scanner scanner){
        try {
            final String SERVER_ADDRESS = "localhost";
            final int PORT = 666;
            DatagramChannel clientChannel = DatagramChannel.open();
            clientChannel.configureBlocking(false);
            System.out.println("Клиент запущен");
            ByteBuffer buffer = ByteBuffer.allocate(1048576);
            RegisterManager registerManager = new RegisterManager();
            Packet pack = new Packet();
            registerManager.requestUser(scanner, buffer, clientChannel, SERVER_ADDRESS, PORT);
            pack.setUser(registerManager.getUser());
            while (true){
                // Подготовка сообщения для отправки
                Object packet = ClientManeger.run(scanner, pack);
                byte[] jsonBytes = JsonSerializer.serialize(packet);
                buffer.clear();
                buffer.put(jsonBytes);
                buffer.flip();

                clientChannel.send(buffer, new InetSocketAddress(SERVER_ADDRESS, PORT));
                System.out.println("Отправлено сообщение серверу");
                buffer.clear();


                Selector selector = Selector.open();
                clientChannel.register(selector, SelectionKey.OP_READ);
                if (selector.select(30000) > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isReadable()) {
                            SocketAddress serverAddr = clientChannel.receive(buffer);
                            buffer.flip();
                            byte[] responseData = new byte[buffer.remaining()];
                            buffer.get(responseData);
                            buffer.clear();
                            JsonDeserializer.deserialize(responseData, Answer.class);
                            System.out.println("Ответ сервера: ");
                            System.out.println(JsonDeserializer.deserialize(responseData, Answer.class).getAnswer());
                            keyIterator.remove();
                        }
                    }
                } else {
                    System.out.println("Ответа нет.");
                }
                selector.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BufferOverflowException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
