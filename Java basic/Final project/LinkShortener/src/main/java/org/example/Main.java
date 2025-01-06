package org.example;

import org.example.service.LinkShortenerService;
import org.example.model.ShortLink;
import java.util.Scanner;
import java.util.UUID;

/**
 * Класс Main представляет собой точку входа в приложение.
 * Он предоставляет пользователю консольный интерфейс для взаимодействия с сервисом сокращения ссылок.
 */
public class Main {

    /**
     * Основной метод, запускающий приложение.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        // Создаем экземпляр сервиса для работы с короткими ссылками
        LinkShortenerService service = new LinkShortenerService();
        // Создаем Scanner для чтения ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Основной цикл приложения
        while (true) {
            // Выводим меню с доступными действиями
            System.out.println("Выберите действие:");
            System.out.println("1. Создать короткую ссылку");
            System.out.println("2. Проверить короткую ссылку");
            System.out.println("3. Выйти");

            // Читаем выбор пользователя
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            // Обрабатываем выбор пользователя
            switch (choice) {
                case 1:
                    // Создание короткой ссылки
                    System.out.println("Введите оригинальный URL:");
                    String longUrl = scanner.nextLine();
                    System.out.println("Введите максимальное количество кликов:");
                    int maxClicks = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера после ввода числа

                    // Создаем пользователя и генерируем короткую ссылку
                    UUID user = service.createUser();
                    String shortUrl = service.createShortLink(user, longUrl, 3600, maxClicks); // Срок жизни: 1 час
                    System.out.println("Короткая ссылка: " + shortUrl);
                    break;

                case 2:
                    // Проверка короткой ссылки
                    System.out.println("Введите короткую ссылку:");
                    String shortUrlToCheck = scanner.nextLine();
                    ShortLink shortLink = service.getShortLink(shortUrlToCheck);

                    // Выводим информацию о ссылке, если она активна
                    if (shortLink != null) {
                        System.out.println("Оригинальный URL: " + shortLink.getOriginalUrl());
                        System.out.println("Оставшиеся клики: " + shortLink.getRemainingClicks());
                    } else {
                        System.out.println("Ссылка не найдена или истек срок действия.");
                    }
                    break;

                case 3:
                    // Завершение работы приложения
                    System.out.println("Выход...");
                    return;

                default:
                    // Обработка неверного выбора
                    System.out.println("Неверный выбор!");
            }
        }
    }
}