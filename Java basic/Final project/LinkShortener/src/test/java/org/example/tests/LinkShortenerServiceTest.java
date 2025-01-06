package org.example.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import org.example.service.LinkShortenerService;
import org.example.model.ShortLink;
import java.time.LocalDateTime;

class LinkShortenerServiceTest {

    @Test
    void testUniqueShortLinksForDifferentUsers() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user1 = service.createUser();
        UUID user2 = service.createUser();

        String longUrl = "https://example.com";

        String shortUrl1 = service.createShortLink(user1, longUrl, 5);
        String shortUrl2 = service.createShortLink(user2, longUrl, 5);

        assertNotEquals(shortUrl1, shortUrl2, "Ссылки должны быть уникальными!");
    }

    @Test
    void testLinkExpiration() {
        LinkShortenerService service = new LinkShortenerService();
        UUID user = service.createUser();

        String longUrl = "https://example.com";
        String shortUrl = service.createShortLink(user, longUrl, 5);

        // Получаем объект ShortLink
        ShortLink shortLink = service.getShortLink(shortUrl);
        assertNotNull(shortLink, "Ссылка должна существовать!");

        // Устанавливаем время создания в прошлом (например, 1 секунду назад)
        shortLink.setCreationTime(LocalDateTime.now().minusSeconds(1));
        assertTrue(service.isLinkExpired(shortLink), "Ссылка должна быть просрочена!");
    }
}