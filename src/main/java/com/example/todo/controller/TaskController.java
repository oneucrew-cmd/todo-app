package com.example.todo.controller;
// 👉 Пакет (папка), где лежит этот класс. Просто организация проекта

import com.example.todo.model.Task;
// 👉 Подключаем класс Task (модель задачи)

import com.example.todo.service.TaskService;
// 👉 Подключаем сервис — через него идёт вся логика

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// 👉 Подключаем все REST-аннотации (GetMapping, PostMapping и т.д.)

import java.util.List;
// 👉 Нужен для List<Task> (список задач)



@RestController
// 👉 Говорим Spring: "Этот класс — REST API (возвращает JSON)"

@RequestMapping("/tasks")
// 👉 Базовый URL для всех методов → /tasks
// 👉 Все методы внутри будут начинаться с /tasks

public class TaskController {

    private final TaskService taskService;
    // 👉 Создаём переменную сервиса
    // 👉 final = нельзя изменить после создания


    public TaskController(TaskService taskService){
        // 👉 Конструктор

        this.taskService = taskService;
        // 👉 Spring автоматически передаст сюда TaskService
        // 👉 Это называется Dependency Injection (внедрение зависимости)
    }



    @GetMapping
    // 👉 Обрабатывает GET запрос → получить данные
    // 👉 URL: GET /tasks

    public List<Task> GetAllTasks(){
        // 👉 Метод возвращает список задач

        return taskService.getAllTasks();
        // 👉 Вызываем сервис и получаем все задачи
    }

@GetMapping("/{id}")
// обрабатывает GET /tasks/5
// {id} = переменная в URL — любое число
public Task getTaskbyId(@PathVariable Long id){
    // @PathVariable берёт id из URL
    // например /tasks/5 → id = 5
        return taskService.getTaskById(id);
    // идём в сервис → ищем задачу по id
    // если не нашли → TaskNotFoundException → 404
}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // всегда возвращает 201 когда метод выполнился без ошибок
    // 👉 Обрабатывает POST запрос → создать задачу
    // 👉 URL: POST /tasks

    public Task createTask(@Valid @RequestBody Task task) {
        // 👉 @RequestBody берёт JSON из запроса и превращает в объект Task
        // @Valid говорит Spring: "проверь входящий объект по правилам из модели"
        //// без этого @NotBlank и @NotNull в Task.java просто игнорируются
        //// это как охранник — аннотации в модели правила, @Valid их применяет

        return taskService.createTask(task);
        // 👉 Передаём задачу в сервис → сохраняем → возвращаем результат
    }



    @DeleteMapping("/{id}")
    // 👉 DELETE запрос → удалить задачу
    // 👉 URL: DELETE /tasks/5

    public void deleteTask(@PathVariable Long id){
        // 👉 @PathVariable берёт id из URL (например /tasks/5 → id = 5)

        taskService.deleteTask(id);
        // 👉 Передаём id в сервис → удаляем задачу
    }

@PatchMapping("/{id}")
// Этот метод будет вызываться при HTTP PATCH-запросе на адрес /tasks/{id}
// PATCH используется для ЧАСТИЧНОГО обновления данных


public Task patchtask(@PathVariable Long id, @RequestBody Task task){
    // @PathVariable берёт значение из URL
    // Пример: /tasks/5 → id = 5
    // @RequestBody берёт JSON из тела запроса и превращает его в объект Task
    // Например JSON: { "title": "Новая задача" }
    // PATCH = частичное обновление → клиент присылает ТОЛЬКО то что хочет изменить
// например только title, completed не трогает
// если поставить @Valid → Spring потребует completed → ошибка 400
// но клиент специально его не прислал! → поэтому @Valid не ставим

return  taskService.patchTask(id,task);
    // Вызываем метод в сервисе
    // Передаём туда:
    // - id задачи (что обновлять)
    // - task (какие данные обновлять)
    // Сервис выполняет всю логику и возвращает обновлённую задачу

}


    @PutMapping("/{id}")
    // 👉 PUT запрос → обновить задачу
    // 👉 URL: PUT /tasks/5

    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        // 👉 id берём из URL
        // 👉 task берём из JSON
// PUT = полное обновление → клиент присылает ВСЕ поля
// значит все поля обязательны → @Valid нужен

        return taskService.updateTask(id, task);
        // 👉 Передаём в сервис → обновляем → возвращаем обновлённую задачу
    }

}