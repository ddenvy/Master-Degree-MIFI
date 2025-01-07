package com.finance.manager;

import com.finance.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс FinanceManager управляет пользователями и их кошельками.
 * Он отвечает за регистрацию, авторизацию, выход пользователей,
 * а также за сохранение и загрузку данных.
 */
public class FinanceManager {
    private Map<String, User> users; // Хранит всех зарегистрированных пользователей
    private User currentUser; // Текущий авторизованный пользователь

    /**
     * Конструктор класса FinanceManager.
     * Инициализирует пустой список пользователей и устанавливает текущего пользователя в null.
     */
    public FinanceManager() {
        this.users = new HashMap<>();
        this.currentUser = null;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param username Логин пользователя.
     * @param password Пароль пользователя.
     * @return true, если регистрация прошла успешно, false, если пользователь уже существует.
     */
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Пользователь уже существует
        }
        users.put(username, new User(username, password)); // Добавляем нового пользователя
        return true;
    }

    /**
     * Авторизует пользователя.
     *
     * @param username Логин пользователя.
     * @param password Пароль пользователя.
     * @return true, если авторизация прошла успешно, false, если логин или пароль неверны.
     */
    public boolean login(String username, String password) {
        User user = users.get(username); // Получаем пользователя по логину
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user; // Устанавливаем текущего пользователя
            return true;
        }
        return false; // Логин или пароль неверны
    }

    /**
     * Выход текущего пользователя из системы.
     * Устанавливает текущего пользователя в null.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Возвращает текущего авторизованного пользователя.
     *
     * @return Текущий пользователь или null, если пользователь не авторизован.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Сохраняет данные всех пользователей в файл.
     * Используется сериализация для сохранения данных в файл "data.ser".
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(users); // Сериализация данных
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок ввода-вывода
        }
    }

    /**
     * Загружает данные всех пользователей из файла.
     * Используется де сериализация для загрузки данных из файла "data.ser".
     * Если файл не существует, метод просто продолжает работу без ошибок.
     */
    @SuppressWarnings("unchecked")
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            users = (Map<String, User>) ois.readObject(); // Де сериализация данных
        } catch (IOException | ClassNotFoundException e) {
            // Если файла нет или произошла ошибка, просто продолжаем
        }
    }
}