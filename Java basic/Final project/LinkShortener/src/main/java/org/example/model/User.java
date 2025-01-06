package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private UUID userId;
    private Map<String, ShortLink> links; // Список ссылок пользователя

    public User() {
        this.userId = UUID.randomUUID(); // Генерация уникального ID
        this.links = new HashMap<>();
    }

    // Геттеры
    public UUID getUserId() {
        return userId;
    }

    public Map<String, ShortLink> getLinks() {
        return links;
    }

    // Добавление новой ссылки
    public void addLink(String shortUrl, ShortLink shortLink) {
        links.put(shortUrl, shortLink);
    }
}