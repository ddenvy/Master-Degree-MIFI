package org.example.service;

import org.example.generator.ShortLinkGenerator;
import org.example.model.ShortLink;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
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
    public String createShortLink(UUID userId, String originalUrl, long expirationTimeInSeconds, int maxClicks) {
        String shortUrl;
        do {
            shortUrl = ShortLinkGenerator.generateShortUrl(); // Генерация короткой ссылки
        } while (shortLinks.containsKey(shortUrl)); // Проверка уникальности

        ShortLink shortLink = new ShortLink(originalUrl, shortUrl, maxClicks);
        shortLink.setExpirationTimeInSeconds(expirationTimeInSeconds);
        shortLinks.put(shortUrl, shortLink);
        return shortUrl;
    }

    // Получение короткой ссылки
    public ShortLink getShortLink(String shortUrl) {
        ShortLink shortLink = shortLinks.get(shortUrl);
        if (shortLink != null && !isLinkExpired(shortLink) && shortLink.getRemainingClicks() > 0) {
            shortLink.setRemainingClicks(shortLink.getRemainingClicks() - 1);
            return shortLink;
        }
        return null;
    }

    // Проверка, истекла ли ссылка
    public boolean isLinkExpired(ShortLink shortLink) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = shortLink.getCreationTime().plusSeconds(shortLink.getExpirationTimeInSeconds());
        return now.isAfter(expirationTime);
    }

    // Удаление истекших ссылок
    public void checkExpiredLinks() {
        Iterator<Map.Entry<String, ShortLink>> iterator = shortLinks.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ShortLink> entry = iterator.next();
            if (isLinkExpired(entry.getValue())) {
                iterator.remove();
            }
        }
    }
}