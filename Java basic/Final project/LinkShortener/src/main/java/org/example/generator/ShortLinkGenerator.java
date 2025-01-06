package org.example.generator;

import java.util.Random;

/**
 * Класс ShortLinkGenerator отвечает за генерацию уникальных коротких ссылок.
 * Короткая ссылка создается из случайных символов и имеет фиксированную длину.
 */
public class ShortLinkGenerator {

    // Набор символов, из которых генерируется короткая ссылка
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // Длина короткой ссылки (без учета префикса)
    private static final int SHORT_URL_LENGTH = 8;

    // Генератор случайных чисел для выбора символов
    private static final Random random = new Random();

    /**
     * Генерирует уникальную короткую ссылку.
     * Ссылка состоит из префикса "clck.ru/" и случайной последовательности символов.
     *
     * @return Сгенерированная короткая ссылка.
     */
    public static String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            // Выбираем случайный символ из набора CHARACTERS
            int index = random.nextInt(CHARACTERS.length());
            shortUrl.append(CHARACTERS.charAt(index));
        }
        // Добавляем префикс "clck.ru/" для удобства
        return "clck.ru/" + shortUrl.toString();
    }
}