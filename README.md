# Todo REST API

REST-сервис для управления задачами на Spring Boot + PostgreSQL.

## Технологии
- Java 21
- Spring Boot
- PostgreSQL
- Docker

## Запуск через Docker

```bash
mvnw.cmd clean package -DskipTests
docker-compose up --build
```

Приложение будет доступно на http://localhost:8080

## Эндпоинты

| Метод | URL | Описание |
|-------|-----|----------|
| GET | /tasks | Получить все задачи |
| GET | /tasks/{id} | Получить задачу по id |
| POST | /tasks | Создать задачу |
| PUT | /tasks/{id} | Обновить задачу |
| PATCH | /tasks/{id} | Частично обновить |
| DELETE | /tasks/{id} | Удалить задачу |

## Пример запроса

POST /tasks
{
"title": "Купить продукты",
"description": "Молоко, хлеб",
"completed": false
}