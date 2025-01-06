package org.example.tests;

import org.example.model.ShortLink;
import org.example.service.LinkShortenerService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс LinkShortenerServiceTest содержит unit-тесты для проверки функциональности сервиса LinkShortenerService.
 */
class LinkShortenerServiceTest {

    /**
     * Тест проверяет, что одна и та же ссылка, сокращенная разными пользователями, генерирует уникальные короткие ссылки.
     */
    @Test
    void testUniqueShortLinksForDifferentUsers() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user1 = service.createUser();
        UUID user2 = service.createUser();

        String longUrl = "https://example.com";

        // Создаем короткие ссылки для двух разных пользователей
        String shortUrl1 = service.createShortLink(user1, longUrl, 3600, 5);
        String shortUrl2 = service.createShortLink(user2, longUrl, 3600, 5);

        // Проверяем, что короткие ссылки уникальны
        assertNotEquals(shortUrl1, shortUrl2, "Ссылки должны быть уникальными!");
    }

    /**
     * Тест проверяет, что ссылка становится недоступной после истечения срока жизни.
     */
    @Test
    void testLinkExpiration() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user = service.createUser();

        String longUrl = "https://example.com";
        // Создаем ссылку с временем жизни 1 секунда
        String shortUrl = service.createShortLink(user, longUrl, 1, 5);

        // Получаем объект ShortLink
        ShortLink shortLink = service.getShortLink(shortUrl);
        assertNotNull(shortLink, "Ссылка должна существовать!");

        // Ждем 2 секунды, чтобы ссылка истекла
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что ссылка просрочена
        assertTrue(service.isLinkExpired(shortLink), "Ссылка должна быть просрочена!");
    }

    /**
     * Тест проверяет, что истекшие ссылки удаляются из хранилища.
     */
    @Test
    void testExpiredLinkDeletion() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user = service.createUser();

        String longUrl = "https://example.com";
        // Создаем ссылку с временем жизни 1 секунда
        String shortUrl = service.createShortLink(user, longUrl, 1, 5);

        // Получаем объект ShortLink
        ShortLink shortLink = service.getShortLink(shortUrl);
        assertNotNull(shortLink, "Ссылка должна существовать!");

        // Ждем 2 секунды, чтобы ссылка истекла
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что ссылка удаляется
        service.checkExpiredLinks();
        assertNull(service.getShortLink(shortUrl), "Ссылка должна быть удалена после истечения срока жизни!");
    }
}