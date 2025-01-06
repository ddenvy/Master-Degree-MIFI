package org.example;

import org.example.service.LinkShortenerService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        LinkShortenerService service = new LinkShortenerService();
        try (Scanner scanner = new Scanner(System.in)) {
            UUID userId = service.createUser(); // Создаем пользователя

            while (true) {
            System.out.println("1. Создать короткую ссылку");
            System.out.println("2. Перейти по короткой ссылке");
            System.out.println("3. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите длинный URL: ");
                    String originalUrl = scanner.nextLine();
                    System.out.print("Введите лимит переходов: ");
                    int maxClicks = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    String shortUrl = service.createShortLink(userId, originalUrl, maxClicks);
                    System.out.println("Короткая ссылка: " + shortUrl);
                    break;

                case 2:
                    System.out.print("Введите короткую ссылку: ");
                    String shortUrlInput = scanner.nextLine();
                    String result = service.redirect(shortUrlInput);
                    System.out.println(result);
                    break;

                case 3:
                    System.out.println("Выход...");
                    break;

                default:
                    System.out.println("Неверный выбор!");
                }
            }
        }
    }
}