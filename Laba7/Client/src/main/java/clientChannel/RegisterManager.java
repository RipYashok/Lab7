package clientChannel;

import clientChannel.utils.*;
import commands.Command;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Scanner;

public class RegisterManager {

    boolean reg = false;
    User user;

    public Packet register(Scanner scanner){
        System.out.println("Зарегистраруйтесь или войдите в аккаунт");
        System.out.println("Введите логин:");
        User user = new User();
        user.setName(scanner.nextLine());
        System.out.println("Введите пароль:");
        user.setPassword(MD2.hashing(scanner.nextLine()));
        Packet packet = new Packet();
        packet.setCommand(new Command());
        packet.setRoute(null);
        packet.setValue(null);
        packet.setUser(user);
        this.user = user;
        return packet;
    }

    public void requestUser(Scanner scanner, ByteBuffer buffer, DatagramChannel clientChannel, String SERVER_ADDRESS, int PORT) throws Exception {
        Object packet = register(scanner);
        byte[] jsonBytes = JsonSerializer.serialize(packet);
        buffer.clear();
        buffer.put(jsonBytes);
        buffer.flip();

        clientChannel.send(buffer, new InetSocketAddress(SERVER_ADDRESS, PORT));
        System.out.println("Отправлен запрос.");
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
                    this.reg = true;
                    keyIterator.remove();
                }
            }
        } else {
            System.out.println("Ответа нет.");
        }
    }

    public boolean isReg() {
        return reg;
    }

    public void setReg(boolean reg) {
        this.reg = reg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
