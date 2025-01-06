package org.example.tests;

import org.example.model.ShortLink;
import org.example.service.LinkShortenerService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LinkShortenerServiceTest {

    @Test
    void testUniqueShortLinksForDifferentUsers() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user1 = service.createUser();
        UUID user2 = service.createUser();

        String longUrl = "https://example.com";

        String shortUrl1 = service.createShortLink(user1, longUrl, 3600, 5);
        String shortUrl2 = service.createShortLink(user2, longUrl, 3600, 5);

        assertNotEquals(shortUrl1, shortUrl2, "Ссылки должны быть уникальными!");
    }

    @Test
    void testLinkExpiration() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user = service.createUser();

        String longUrl = "https://example.com";
        String shortUrl = service.createShortLink(user, longUrl, 1, 5); // Срок жизни: 1 секунда

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

    @Test
    void testExpiredLinkDeletion() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user = service.createUser();

        String longUrl = "https://example.com";
        String shortUrl = service.createShortLink(user, longUrl, 1, 5); // Срок жизни: 1 секунда

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