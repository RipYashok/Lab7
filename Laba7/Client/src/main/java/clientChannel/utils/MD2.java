package clientChannel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD2 {

    public static String hashing(String string){
        String password = null;
        try {
            // Создаем экземпляр объекта MessageDigest с указанием алгоритма MD2
            MessageDigest md = MessageDigest.getInstance("MD2");

            // Вычисляем хэш для входной строки
            byte[] hashBytes = md.digest(string.getBytes());

            // Преобразуем байтовый массив в строку шестнадцатеричного представления
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Выводим хэш в шестнадцатеричном формате
            password =  hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD2 algorithm not available.");
        }
        return password;
    }
}
