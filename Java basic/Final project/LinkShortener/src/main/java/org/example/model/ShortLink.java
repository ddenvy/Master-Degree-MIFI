package org.example.model;

import java.time.LocalDateTime;

/**
 * Класс ShortLink представляет собой модель короткой ссылки.
 * Он хранит информацию об оригинальном URL, коротком URL, лимите переходов,
 * оставшихся переходах, времени создания и времени жизни ссылки.
 */
public class ShortLink {
    private String originalUrl; // Оригинальный URL
    private String shortUrl; // Короткий URL
    private int maxClicks; // Максимальное количество переходов
    private int remainingClicks; // Оставшиеся переходы
    private LocalDateTime creationTime; // Время создания ссылки
    private long expirationTimeInSeconds; // Время жизни ссылки в секундах

    /**
     * Конструктор для создания объекта ShortLink.
     *
     * @param originalUrl Оригинальный URL.
     * @param shortUrl    Короткий URL.
     * @param maxClicks   Максимальное количество переходов.
     */
    public ShortLink(String originalUrl, String shortUrl, int maxClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.maxClicks = maxClicks;
        this.remainingClicks = maxClicks;
        this.creationTime = LocalDateTime.now();
    }

    // Геттеры

    /**
     * Возвращает оригинальный URL.
     *
     * @return Оригинальный URL.
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * Возвращает короткий URL.
     *
     * @return Короткий URL.
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Возвращает максимальное количество переходов.
     *
     * @return Максимальное количество переходов.
     */
    public int getMaxClicks() {
        return maxClicks;
    }

    /**
     * Возвращает количество оставшихся переходов.
     *
     * @return Количество оставшихся переходов.
     */
    public int getRemainingClicks() {
        return remainingClicks;
    }

    /**
     * Возвращает время создания ссылки.
     *
     * @return Время создания ссылки.
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Возвращает время жизни ссылки в секундах.
     *
     * @return Время жизни ссылки в секундах.
     */
    public long getExpirationTimeInSeconds() {
        return expirationTimeInSeconds;
    }

    // Сеттеры

    /**
     * Устанавливает количество оставшихся переходов.
     *
     * @param remainingClicks Количество оставшихся переходов.
     * @throws IllegalArgumentException Если количество оставшихся переходов отрицательное.
     */
    public void setRemainingClicks(int remainingClicks) {
        if (remainingClicks < 0) {
            throw new IllegalArgumentException("Remaining clicks cannot be negative");
        }
        this.remainingClicks = remainingClicks;
    }

    /**
     * Устанавливает время создания ссылки.
     *
     * @param creationTime Время создания ссылки.
     */
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Устанавливает время жизни ссылки в секундах.
     *
     * @param expirationTimeInSeconds Время жизни ссылки в секундах.
     */
    public void setExpirationTimeInSeconds(long expirationTimeInSeconds) {
        this.expirationTimeInSeconds = expirationTimeInSeconds;
    }
}