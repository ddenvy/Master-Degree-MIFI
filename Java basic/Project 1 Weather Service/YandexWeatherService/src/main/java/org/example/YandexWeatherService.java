package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class YandexWeatherService {

    public static void main(String[] args) {
        String apiKey = "9239bb2e-cf3d-44fe-a06c-b7f53521885d";
        double lat = 23.1291; // Широта Гуанчжоу
        double lon = 113.2644; // Долгота Гуанчжоу
        int limit = 5; // Количество дней для прогноза

        // Создаем HTTP-клиент
        HttpClient client = HttpClient.newHttpClient();

        // Создаем запрос
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon + "&limit=" + limit))
                .header("X-Yandex-Weather-Key", apiKey)
                .build();

        // Отправляем запрос и обрабатываем ответ
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(YandexWeatherService::parseAndDisplayWeatherData)
                .exceptionally(e -> {
                    System.out.println("Error occurred: " + e.getMessage());
                    return null;
                })
                .join();
    }

    // Метод для обработки JSON-ответа и вывода температуры
    public static void parseAndDisplayWeatherData(String responseBody) {
        JSONObject json = new JSONObject(responseBody);

        // Выводим весь JSON-ответ
        System.out.println("Response JSON: " + json.toString(2));

        // Достаем текущую температуру
        int currentTemp = json.getJSONObject("fact").getInt("temp");
        System.out.println("Current temperature: " + currentTemp);

        // Находим и выводим среднюю температуру за указанный период
        double averageTemp = calculateAverageTemperature(json);
        System.out.println("Average temperature: " + averageTemp);
    }

    // Метод для расчета средней температуры
    public static double calculateAverageTemperature(JSONObject json) {
        var forecasts = json.getJSONArray("forecasts");
        double sumTemp = 0;
        int count = forecasts.length();

        for (int i = 0; i < count; i++) {
            sumTemp += forecasts.getJSONObject(i).getJSONObject("parts").getJSONObject("day").getInt("temp_avg");
        }

        return sumTemp / count;
    }
}
