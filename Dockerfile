# Берём готовый образ с Java 21
FROM eclipse-temurin:21-jdk-alpine

# Рабочая папка внутри контейнера
WORKDIR /app

# Копируем собранный jar файл
COPY target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]

