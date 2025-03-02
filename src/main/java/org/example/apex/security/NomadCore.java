package org.example.apex.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Класс для шифрования и дешифрования данных с использованием AES и соли.
 * Реализует интерфейс Coder.
 */
public class NomadCore implements Coder {
    private String password; // Пароль для шифрования/дешифрования
    private byte[] key;      // Ключ шифрования
    private byte[] iv;       // Вектор инициализации (IV)

    /**
     * Конструктор для создания объекта с новым ключом и IV.
     *
     * @param password Пароль для шифрования.
     */
    public NomadCore(String password) {
        byte[] salt = generateSalt(); // Генерация соли
        this.iv = generateIv();       // Генерация случайного IV
        this.key = generateKey(password, salt); // Генерация ключа на основе пароля и соли
        this.password = password;
    }

    /**
     * Конструктор для создания объекта с существующим ключом и IV.
     *
     * @param password Пароль для шифрования/дешифрования.
     * @param key      Ключ шифрования.
     * @param iv       Вектор инициализации.
     */
    public NomadCore(String password, byte[] key, byte[] iv) {
        this.password = password;
        this.key = key;
        this.iv = iv;
    }

    /**
     * Шифрует пароль с использованием AES.
     */
    @Override
    public void encrypt() {
        try {
            // Создаём ключ AES
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            // Инициализируем шифр в режиме шифрования
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Шифруем данные
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            // Кодируем результат в Base64
            password = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при шифровании данных", e);
        }
    }

    /**
     * Дешифрует пароль с использованием AES.
     *
     * @param key Ключ шифрования.
     * @param iv  Вектор инициализации.
     */
    @Override
    public void decrypt(byte[] key, byte[] iv) {
        try {
            // Создаём ключ AES
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            // Инициализируем шифр в режиме дешифрования
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Декодируем Base64 и расшифровываем данные
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(password));
            password = new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при дешифровании данных", e);
        }
    }

    /**
     * Генерирует случайный вектор инициализации (IV).
     *
     * @return Сгенерированный IV.
     */
    private byte[] generateIv() {
        byte[] iv = new byte[16]; // 16 байт для AES
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    // Геттеры и сеттеры
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getKey() {
        return key;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public byte[] getIv() {
        return iv;
    }
}