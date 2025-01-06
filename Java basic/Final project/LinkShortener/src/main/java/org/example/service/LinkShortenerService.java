package org.example.service;

import org.example.generator.ShortLinkGenerator;
import org.example.model.ShortLink;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkShortenerService {
    private Map<UUID, User> users = new HashMap<>(); // Хранилище пользователей
    private Map<String, ShortLink> shortLinks = new HashMap<>(); // Хранилище всех коротких ссылок

    // Создание нового пользователя
    public UUID createUser() {
        User user = new User();
        users.put(user.getUserId(), user);
        return user.getUserId();
    }

    // Создание короткой ссылки
    public String createShortLink(UUID userId, String originalUrl, int maxClicks) {
        String shortUrl;
        do {
            shortUrl = ShortLinkGenerator.generateShortUrl(); // Генерация короткой ссылки
        } while (shortLinks.containsKey(shortUrl)); // Проверка уникальности

        ShortLink shortLink = new ShortLink(originalUrl, shortUrl, maxClicks);
        shortLinks.put(shortUrl, shortLink);

        // Добавляем ссылку пользователю
        User user = users.get(userId);
        if (user != null) {
            user.addLink(shortUrl, shortLink);
        }

        return shortUrl;
    }

    // Переход по короткой ссылке
    public String redirect(String shortUrl) {
        ShortLink shortLink = shortLinks.get(shortUrl);
        if (shortLink == null) {
            return "Ссылка не найдена!";
        }

        // Проверка лимита переходов
        if (shortLink.getRemainingClicks() <= 0) {
            return "Лимит переходов исчерпан!";
        }

        // Уменьшаем количество оставшихся переходов
        shortLink.setRemainingClicks(shortLink.getRemainingClicks() - 1);
        return shortLink.getOriginalUrl();
    }

    // Проверка и удаление "протухших" ссылок
    public void checkExpiredLinks() {
        LocalDateTime now = LocalDateTime.now();
        shortLinks.entrySet().removeIf(entry -> {
            ShortLink link = entry.getValue();
            // Удаляем ссылки, которые старше 1 дня
            return now.isAfter(link.getCreationTime().plusDays(1));
        });
    }
    public boolean isLinkExpired(ShortLink shortLink) {

        return shortLink.getCreationTime().plusSeconds(shortLink.getExpirationTimeInSeconds()).isBefore(LocalDateTime.now());

    }
    // Получение ShortLink по короткому URL
    public ShortLink getShortLink(String shortUrl) {
        return shortLinks.get(shortUrl);
    }
}