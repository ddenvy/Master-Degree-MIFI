# Используем базовый образ Java 17
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файлы проекта в рабочую директорию
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Открываем порт 8080 для доступа к приложению
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "demo.jar"]