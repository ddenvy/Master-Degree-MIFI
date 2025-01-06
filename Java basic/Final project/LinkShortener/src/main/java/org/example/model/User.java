package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Класс User представляет собой модель пользователя.
 * Каждый пользователь идентифицируется по уникальному UUID и имеет список своих коротких ссылок.
 */
public class User {

    private UUID userId; // Уникальный идентификатор пользователя
    private Map<String, ShortLink> links; // Список коротких ссылок пользователя

    /**
     * Конструктор для создания объекта User.
     * При создании пользователя генерируется уникальный UUID и инициализируется пустой список ссылок.
     */
    public User() {
        this.userId = UUID.randomUUID(); // Генерация уникального ID
        this.links = new HashMap<>();
    }

    // Геттеры

    /**
     * Возвращает уникальный идентификатор пользователя.
     *
     * @return Уникальный идентификатор пользователя (UUID).
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Возвращает список коротких ссылок пользователя.
     *
     * @return Список коротких ссылок (Map<String, ShortLink>).
     */
    public Map<String, ShortLink> getLinks() {
        return links;
    }

    // Методы

    /**
     * Добавляет новую короткую ссылку в список ссылок пользователя.
     *
     * @param shortUrl  Короткий URL.
     * @param shortLink Объект ShortLink, представляющий короткую ссылку.
     */
    public void addLink(String shortUrl, ShortLink shortLink) {
        links.put(shortUrl, shortLink);
    }
}