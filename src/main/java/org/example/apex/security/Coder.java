package org.example.apex.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Интерфейс для шифрования и дешифрования данных.
 * Предоставляет методы по умолчанию для генерации соли и ключа.
 */
public interface Coder {
    /**
     * Шифрует данные с использованием предоставленного ключа и вектора инициализации (IV).
     *
     * @param key Ключ шифрования.
     * @param iv  Вектор инициализации.
     */
    String encrypt(byte[] key, byte[] iv);

    /**
     * Шифрует данные с использованием внутренних ключа и вектора инициализации (IV).
     */
    String encrypt();

    /**
     * Дешифрует данные с использованием предоставленного ключа и вектора инициализации (IV).
     *
     * @param key Ключ шифрования.
     * @param iv  Вектор инициализации.
     */
    String decrypt(byte[] key, byte[] iv);

    /**
     * Дешифрует данные с использованием внутренних ключа и вектора инициализации (IV).
     */
    String decrypt();

    /**
     * Генерирует случайную соль для использования в криптографических операциях.
     *
     * @return Сгенерированная соль длиной 16 байт (128 бит).
     */
    default byte[] generateSalt() {
        byte[] salt = new byte[16]; // 16 байт (128 бит)
        new SecureRandom().nextBytes(salt); // Заполняем массив случайными байтами
        return salt;
    }

    /**
     * Генерирует ключ на основе пароля и соли с использованием алгоритма PBKDF2.
     *
     * @param password Пароль для генерации ключа.
     * @param salt     Соль для увеличения безопасности ключа.
     * @return Сгенерированный ключ длиной 256 бит.
     * @throws RuntimeException Если произошла ошибка при генерации ключа.
     */
    default byte[] generateKey(String password, byte[] salt) {
        try {
            // Параметры для PBKDF2
            int iterationCount = 65536; // Количество итераций
            int keyLength = 256;       // Длина ключа (256 бит)

            // Создаём KeySpec на основе пароля, соли и параметров
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);

            // Генерируем ключ с использованием алгоритма PBKDF2WithHmacSHA256
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Ошибка при генерации ключа", e);
        }
    }
}