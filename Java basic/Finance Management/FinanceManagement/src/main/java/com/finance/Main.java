package com.finance;

import com.finance.manager.FinanceManager;
import com.finance.model.User;
import com.finance.model.Wallet;

import java.util.Map;
import java.util.Scanner;

/**
 * Класс Main является точкой входа в приложение для управления личными финансами.
 * Он предоставляет пользователю консольный интерфейс для регистрации, входа,
 * управления доходами, расходами и бюджетами.
 */
public class Main {
    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager(); // Менеджер для управления пользователями и их данными
        Scanner scanner = new Scanner(System.in); // Сканер для ввода данных от пользователя

        // Основной цикл приложения
        while (true) {
            if (manager.getCurrentUser() == null) {
                // Меню для неавторизованного пользователя
                System.out.println("1. Регистрация\n2. Вход\n3. Выход");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    // Регистрация нового пользователя
                    System.out.print("Введите логин: ");
                    String username = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String password = scanner.nextLine();
                    if (manager.registerUser(username, password)) {
                        System.out.println("Регистрация успешна!");
                    } else {
                        System.out.println("Пользователь уже существует!");
                    }
                } else if (choice.equals("2")) {
                    // Вход пользователя
                    System.out.print("Введите логин: ");
                    String username = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String password = scanner.nextLine();
                    if (manager.login(username, password)) {
                        System.out.println("Вход выполнен!");
                    } else {
                        System.out.println("Неверный логин или пароль!");
                    }
                } else if (choice.equals("3")) {
                    // Выход из приложения
                    System.out.println("Выход выполнен!");
                    break;
                } else {
                    // Неверный выбор
                    System.out.println("Неверный выбор!");
                }
            } else {
                // Меню для авторизованного пользователя
                System.out.println("1. Добавить доход\n2. Добавить расход\n3. Установить бюджет\n4. Показать статистику\n5. Выход");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    // Добавление дохода
                    System.out.print("Введите категорию дохода: ");
                    String category = scanner.nextLine();
                    System.out.print("Введите сумму: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    manager.getCurrentUser().getWallet().addIncome(category, amount);
                    System.out.println("Доход добавлен!");
                } else if (choice.equals("2")) {
                    // Добавление расхода
                    System.out.print("Введите категорию расхода: ");
                    String category = scanner.nextLine();
                    System.out.print("Введите сумму: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    manager.getCurrentUser().getWallet().addExpense(category, amount);
                    System.out.println("Расход добавлен!");
                } else if (choice.equals("3")) {
                    // Установка бюджета
                    System.out.print("Введите категорию: ");
                    String category = scanner.nextLine();
                    System.out.print("Введите бюджет: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    manager.getCurrentUser().getWallet().setBudget(category, amount);
                    System.out.println("Бюджет установлен!");
                } else if (choice.equals("4")) {
                    // Показать статистику
                    Wallet wallet = manager.getCurrentUser().getWallet();
                    System.out.println("Общий доход: " + wallet.getIncomes().values().stream().mapToDouble(Double::doubleValue).sum());
                    System.out.println("Общий расход: " + wallet.getExpenses().values().stream().mapToDouble(Double::doubleValue).sum());
                    System.out.println("Баланс: " + wallet.getBalance());
                    for (Map.Entry<String, Double> entry : wallet.getBudgets().entrySet()) {
                        String category = entry.getKey();
                        double budget = entry.getValue();
                        double remaining = wallet.getRemainingBudget(category);
                        System.out.println(category + ": Бюджет: " + budget + ", Остаток: " + remaining);
                    }
                } else if (choice.equals("5")) {
                    // Выход из системы
                    manager.logout();
                    System.out.println("Выход выполнен!");
                } else {
                    // Неверный выбор
                    System.out.println("Неверный выбор!");
                }
            }
        }

        scanner.close(); // Закрываем сканер после завершения работы
    }
}