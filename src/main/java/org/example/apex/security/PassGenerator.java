package org.example.apex.security;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс для генерации паролей различной сложности.
 */
public class PassGenerator {

    // Наборы символов для генерации паролей
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+=-`~[]{}|;':\",./<>?";

    // Генератор криптографически стойких случайных чисел
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Генерация простого пароля (только строчные буквы).
     *
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String genFirstLevelPass(int length) {
        return generatePassword(new String[]{LOWERCASE}, length);
    }

    /**
     * Генерация пароля средней сложности (строчные, прописные буквы и цифры).
     *
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String genSecondLevelPass(int length) {
        return generatePassword(new String[]{LOWERCASE, UPPERCASE, NUMBERS}, length);
    }

    /**
     * Генерация нормального пароля (строчные, прописные буквы, цифры и спецсимволы).
     *
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String genThirdLevelPass(int length) {
        return generatePassword(new String[]{LOWERCASE, UPPERCASE, NUMBERS, SPECIAL_CHARACTERS}, length);
    }

    /**
     * Генерация сильного пароля (все типы символов).
     *
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String genFourthLevelPass(int length) {
        return genThirdLevelPass(length);
    }

    /**
     * Генерация пароля на основе заданного набора символов.
     *
     * @param groups Составляющие пароля.
     * @param length Длина пароля в символах.
     * @return Сгенерированный пароль.
     */
    private static String generatePassword(String[] groups, int length) {
        StringBuilder unmixedValue = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(groups[i % groups.length].length());
            unmixedValue.append(groups[i % groups.length].charAt(randomIndex));
        }

        return mix(unmixedValue.toString());
    }

    /**
     * Перемешивает символы в строке случайным образом.
     *
     * @param pass Строка, которую необходимо перемешать.
     * @return Строка с перемешанными символами или исходная строка, если она null или пустая.
     */
    private static String mix(String pass) {
        if (pass == null || pass.isEmpty()) {
            return pass; // Возвращаем исходную строку, если она пустая или null
        }

        // Преобразуем строку в список символов
        List<Character> characters = pass.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        // Перемешиваем символы
        Collections.shuffle(characters);

        // Собираем символы обратно в строку
        return characters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Оценка надежности пароля.
     *
     * @param password Пароль для оценки.
     * @return Строка, описывающая уровень надежности пароля.
     */
    public static byte calculateReliability(String password) {
        if (password == null || password.isEmpty())
            return 0;

        int length = password.length();
        boolean hasLowercase = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        // Подсчет количества категорий символов
        int categoryCount = 0;
        if (hasLowercase) categoryCount++;
        if (hasUppercase) categoryCount++;
        if (hasNumber) categoryCount++;
        if (hasSpecial) categoryCount++;

        // Определение уровня надежности
        if (length >= 16 && categoryCount == 4)
            return 4;
        else if (length >= 12 && categoryCount >= 3)
            return 3;
        else if (length >= 8 && categoryCount >= 2)
            return 2;
        else
            return 1;
    }
}