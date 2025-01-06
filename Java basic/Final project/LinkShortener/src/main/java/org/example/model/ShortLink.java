package org.example.model;

import java.time.LocalDateTime;

public class ShortLink {
    private String originalUrl;
    private String shortUrl;
    private int maxClicks;
    private int remainingClicks;
    private LocalDateTime creationTime;
    private long expirationTimeInSeconds; // Add this field

    // Конструктор
    public ShortLink(String originalUrl, String shortUrl, int maxClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.maxClicks = maxClicks;
        this.remainingClicks = maxClicks;
        this.creationTime = LocalDateTime.now();
    }

    // Геттеры
    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public int getMaxClicks() {
        return maxClicks;
    }

    public int getRemainingClicks() {
        return remainingClicks;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public long getExpirationTimeInSeconds() { // Add this getter
        return expirationTimeInSeconds;
    }

    // Сеттеры
    public void setRemainingClicks(int remainingClicks) {
        if (remainingClicks < 0) {
            throw new IllegalArgumentException("Remaining clicks cannot be negative");
        }
        this.remainingClicks = remainingClicks;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setExpirationTimeInSeconds(long expirationTimeInSeconds) { // Add this setter
        this.expirationTimeInSeconds = expirationTimeInSeconds;
    }
}