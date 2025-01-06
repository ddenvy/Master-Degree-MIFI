package org.example;

import org.example.service.LinkShortenerService;
import org.example.model.ShortLink;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        LinkShortenerService service = new LinkShortenerService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать короткую ссылку");
            System.out.println("2. Проверить короткую ссылку");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Введите оригинальный URL:");
                    String longUrl = scanner.nextLine();
                    System.out.println("Введите максимальное количество кликов:");
                    int maxClicks = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    UUID user = service.createUser();
                    String shortUrl = service.createShortLink(user, longUrl, 3600, maxClicks); // 1 hour expiration
                    System.out.println("Короткая ссылка: " + shortUrl);
                    break;

                case 2:
                    System.out.println("Введите короткую ссылку:");
                    String shortUrlToCheck = scanner.nextLine();
                    ShortLink shortLink = service.getShortLink(shortUrlToCheck);
                    if (shortLink != null) {
                        System.out.println("Оригинальный URL: " + shortLink.getOriginalUrl());
                        System.out.println("Оставшиеся клики: " + shortLink.getRemainingClicks());
                    } else {
                        System.out.println("Ссылка не найдена или истек срок действия.");
                    }
                    break;

                case 3:
                    System.out.println("Выход...");
                    return; // Завершение работы приложения

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}