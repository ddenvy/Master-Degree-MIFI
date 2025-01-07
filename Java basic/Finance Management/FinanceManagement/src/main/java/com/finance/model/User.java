package com.finance.model;

/**
 * Класс User представляет пользователя системы.
 * Он хранит информацию о логине, пароле и кошельке пользователя.
 */
public class User {
    private String username; // Логин пользователя
    private String password; // Пароль пользователя
    private Wallet wallet;   // Кошелек пользователя

    /**
     * Конструктор класса User.
     *
     * @param username Логин пользователя.
     * @param password Пароль пользователя.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet(); // Создаем новый кошелек для пользователя
    }

    /**
     * Возвращает логин пользователя.
     *
     * @return Логин пользователя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return Пароль пользователя.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Возвращает кошелек пользователя.
     *
     * @return Кошелек пользователя.
     */
    public Wallet getWallet() {
        return wallet;
    }
}