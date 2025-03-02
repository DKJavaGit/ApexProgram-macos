package org.example.apex.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Класс для шифрования и дешифрования данных с использованием алгоритма PBE (Password-Based Encryption).
 * Реализует интерфейс {@link Coder}.
 */
public class PBECore implements Coder {
    private String password; // Пароль для шифрования/дешифрования
    private byte[] key;      // Ключ шифрования
    private byte[] iv;       // Вектор инициализации (IV)

    /**
     * Конструктор для создания объекта с новым ключом и IV.
     *
     * @param password Пароль для шифрования.
     */
    public PBECore(String password) {
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
    public PBECore(String password, byte[] key, byte[] iv) {
        this.password = password;
        this.key = key;
        this.iv = iv;
    }

    /**
     * Шифрует данные с использованием предоставленного ключа и вектора инициализации (IV).
     *
     * @param key Ключ шифрования.
     * @param iv  Вектор инициализации.
     * @return Зашифрованные данные в виде строки Base64.
     */
    @Override
    public String encrypt(byte[] key, byte[] iv) {
        try {
            // Создаём ключ AES
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            // Инициализируем шифр в режиме шифрования
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Шифруем данные
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            // Кодируем результат в Base64 и возвращаем
            password = Base64.getEncoder().encodeToString(encryptedBytes);
            return password;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при шифровании данных", e);
        }
    }

    /**
     * Шифрует данные с использованием внутренних ключа и вектора инициализации (IV).
     *
     * @return Зашифрованные данные в виде строки Base64.
     */
    @Override
    public String encrypt() {
        return encrypt(this.key, this.iv);
    }

    /**
     * Дешифрует данные с использованием предоставленного ключа и вектора инициализации (IV).
     *
     * @param key Ключ шифрования.
     * @param iv  Вектор инициализации.
     * @return Расшифрованные данные в виде строки.
     */
    @Override
    public String decrypt(byte[] key, byte[] iv) {
        try {
            // Создаём ключ AES
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            // Инициализируем шифр в режиме дешифрования
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Декодируем Base64 и расшифровываем данные
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(password));
            password = new String(decryptedBytes);
            return password;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при дешифровании данных", e);
        }
    }

    /**
     * Дешифрует данные с использованием внутренних ключа и вектора инициализации (IV).
     *
     * @return Расшифрованные данные в виде строки.
     */
    @Override
    public String decrypt() {
        return decrypt(this.key, this.iv);
    }

    /**
     * Генерирует случайный вектор инициализации (IV) для AES.
     *
     * @return Сгенерированный IV длиной 16 байт.
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