package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
// говорит Spring: когда это исключение выбрасывается → верни 404

public class TaskNotFoundException extends RuntimeException {
// создаём свой класс ошибки
// extends RuntimeException → наследуемся от стандартного класса ошибок Java

    public TaskNotFoundException(Long id) {
        // конструктор — принимает id задачи которую не нашли

        super("Task not found with id: " + id);
        // вызываем конструктор RuntimeException
        // передаём ему текст ошибки который увидит клиент
        // например: "Task not found with id: 5"
    }
}