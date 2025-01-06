package org.example.generator;

import java.util.Random;

public class ShortLinkGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8; // Длина короткой ссылки
    private static final Random random = new Random();

    public static String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortUrl.append(CHARACTERS.charAt(index));
        }
        return "clck.ru/" + shortUrl.toString(); // Добавляем префикс для удобства
    }
}