package org.example.service;

import org.example.generator.ShortLinkGenerator;
import org.example.model.ShortLink;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Класс LinkShortenerService предоставляет основные функции сервиса сокращения ссылок.
 * Он управляет пользователями, созданием коротких ссылок, их хранением и проверкой срока действия.
 */
public class LinkShortenerService {

    // Хранилище пользователей (ключ - UUID пользователя, значение - объект User)
    private Map<UUID, User> users = new HashMap<>();

    // Хранилище всех коротких ссылок (ключ - короткий URL, значение - объект ShortLink)
    private Map<String, ShortLink> shortLinks = new HashMap<>();

    /**
     * Создает нового пользователя и добавляет его в хранилище.
     *
     * @return Уникальный идентификатор (UUID) созданного пользователя.
     */
    public UUID createUser() {
        User user = new User();
        users.put(user.getUserId(), user);
        return user.getUserId();
    }

    /**
     * Создает короткую ссылку для указанного пользователя.
     *
     * @param userId               Уникальный идентификатор пользователя.
     * @param originalUrl          Оригинальный URL.
     * @param expirationTimeInSeconds Время жизни ссылки в секундах.
     * @param maxClicks            Максимальное количество переходов.
     * @return Короткий URL.
     */
    public String createShortLink(UUID userId, String originalUrl, long expirationTimeInSeconds, int maxClicks) {
        String shortUrl;
        do {
            shortUrl = ShortLinkGenerator.generateShortUrl(); // Генерация короткой ссылки
        } while (shortLinks.containsKey(shortUrl)); // Проверка уникальности

        // Создаем объект ShortLink и настраиваем его
        ShortLink shortLink = new ShortLink(originalUrl, shortUrl, maxClicks);
        shortLink.setExpirationTimeInSeconds(expirationTimeInSeconds);
        shortLinks.put(shortUrl, shortLink); // Добавляем ссылку в хранилище
        return shortUrl;
    }

    /**
     * Возвращает объект ShortLink по короткому URL, если ссылка активна.
     * Также уменьшает количество оставшихся переходов на 1.
     *
     * @param shortUrl Короткий URL.
     * @return Объект ShortLink, если ссылка активна, иначе null.
     */
    public ShortLink getShortLink(String shortUrl) {
        ShortLink shortLink = shortLinks.get(shortUrl);
        if (shortLink != null && !isLinkExpired(shortLink) && shortLink.getRemainingClicks() > 0) {
            shortLink.setRemainingClicks(shortLink.getRemainingClicks() - 1); // Уменьшаем количество переходов
            return shortLink;
        }
        return null;
    }

    /**
     * Проверяет, истек ли срок действия ссылки.
     *
     * @param shortLink Объект ShortLink, представляющий короткую ссылку.
     * @return true, если ссылка истекла, иначе false.
     */
    public boolean isLinkExpired(ShortLink shortLink) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = shortLink.getCreationTime().plusSeconds(shortLink.getExpirationTimeInSeconds());
        return now.isAfter(expirationTime);
    }

    /**
     * Удаляет все истекшие ссылки из хранилища.
     */
    public void checkExpiredLinks() {
        Iterator<Map.Entry<String, ShortLink>> iterator = shortLinks.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ShortLink> entry = iterator.next();
            if (isLinkExpired(entry.getValue())) {
                iterator.remove(); // Удаляем ссылку, если она истекла
            }
        }
    }
}