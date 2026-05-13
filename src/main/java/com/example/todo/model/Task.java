package com.example.todo.model; // Говорит где находится файл как адрес квартиры

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


// 📌 Говорим Spring: // "Создай таблицу в базе данных на основе этого класса"
@Entity
public class Task{
    @Id  // 📌 Это поле — уникальный идентификатор (PRIMARY KEY)
    @GeneratedValue // 📌 Значение id будет создаваться автоматически (1, 2, 3...)
    private Long id; // 🔒 private — доступ к полю только внутри класса (инкапсуляция)

    @NotBlank(message = "Title cannot be empty")
    // 👉 NotBlank = не пустая строка (null, "" и "   " — всё запрещено)
    private String title; // 📌 Поле для названия задачи

private String description;

    @NotNull(message = "Completed status is required")
    // 👉 NotNull = просто не null (для Boolean используем NotNull, не NotBlank)
    private Boolean completed;
    // 📌 Статус задачи: выполнена (true) или нет (false)

    public String getTitle() {
        // 📤 Getter — метод, который ВОЗВРАЩАЕТ значение title
        return title;
        // возвращаем текущее значение поля title


    }
    public void setTitle(String title){
        // 📥 Setter — метод, который УСТАНАВЛИВАЕТ значение title
        // (String title) — параметр, который мы передаём внутрь метода
        this.title=title;
        // this.title — поле класса
        // title — значение, которое пришло в метод

    }

    public Long getId(){
        // 📤 Getter — получить значение id
        return id;
        // возвращаем id
    }
    public void setId(Long id){
        // 📥 Setter — установить id
        this.id=id;
        // обычно не используется, но нужен для работы JPA
    }

    public String getDescription(){
        return  description;
    }
    public void  setDescription(String description){
        this.description=description;
    }


    public Boolean getCompleted(){
        // 📤 Getter для Boolean // для boolean используется "is", а не "get"
         // а для Boolean используется "get"
        return  completed;
        // возвращаем текущее состояние задачи

    }
    public void setCompleted(Boolean completed){
        // 📥 Setter — изменить статус задачи
        this.completed=completed;
        // записываем новое значение (true/false)

    }


}