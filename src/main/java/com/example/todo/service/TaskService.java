package com.example.todo.service;

import com.example.todo.exception.TaskNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service // 👉 Говорим Spring: "Создай этот класс как объект и управляй им"
//  💡 Без этого Spring не будет знать про класс



public class TaskService {  // 👉 Обычный Java-класс просто название: "сервис для задач"
    private final TaskRepository taskRepository; // 👉 Перевод на простой язык:
//    TaskRepository → тип (инструмент для работы с базой)
//    taskRepository → переменная (наш инструмент)
//    private → только внутри класса
//    final → нельзя изменить после создания
//
//💡 То есть:
//
//            "у меня есть инструмент для работы с базой, и он не будет меняться"
//

    public TaskService(TaskRepository taskRepository){ //👉 Конструктор
//        "когда создаётся объект — мне нужен repository"

        this.taskRepository=taskRepository; // 👉 Самая важная строка
//        "положи переданный repository внутрь класса"



    }
    public Task createTask(Task task){
        // 📌 Метод создания задачи
        return taskRepository.save(task);
        // 👉 сохраняем задачу в базу
        // 👉 и возвращаем её обратно


    }
    public Task getTaskById(Long id){
        // Метод для получения одной задачи по id

        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        // Ищем задачу в базе по id
        // findById возвращает Optional
        // если задача есть → вернётся Task
        // если нет → выбросится ошибка
    }

    public Task updateTask( Long id,  Task updatedTask) {
        // 📌 Метод обновления задачи
        Task existingTask = taskRepository.findById(id)
                // 👉 ищем задачу по id

                .orElseThrow(() -> new TaskNotFoundException(id));
        // 👉 если не нашли → ошибка // 👉 если нашли → получаем Task

        existingTask.setTitle(updatedTask.getTitle());
        // 👉 обновляем название

        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.getCompleted());
        // 👉 обновляем статус

        return taskRepository.save(existingTask);
        // 👉 сохраняем обновлённую задачу
    }

public Task patchTask(Long id,Task updatedTask){
    // Метод для частичного обновления задачи (PATCH)
    // id — какую задачу обновляем
    // updatedTask — данные, которые пришли от клиента

        Task existingTask=taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    // Ищем задачу в базе по id
    // Если не нашли → выбрасываем ошибку (важно, чтобы не работать с null)

                if(updatedTask.getTitle()  !=null) {
                    existingTask.setTitle(updatedTask.getTitle());
                    // Если клиент передал title → обновляем
                    // Если НЕ передал → оставляем старое значение

                }
                if(updatedTask.getDescription() !=null) {
                    existingTask.setDescription(updatedTask.getDescription());
                    // То же самое для description

                }
                if(updatedTask.getCompleted() !=null) {
                    existingTask.setCompleted(updatedTask.getCompleted());
                }
                   // Обновляем completed ТОЛЬКО если поле пришло
                   // Это ключевая логика PATCH

                return taskRepository.save(existingTask);
    // Сохраняем обновлённую задачу в базе
    // И возвращаем её обратно клиенту
}


    public List<Task> getAllTasks(){ // 👉 Метод:
//        public → можно вызвать снаружи
//        List<Task> → вернёт список задач
//        getAllTasks → "получить все задачи"


        return taskRepository.findAll();

        // 👉 "сходи в базу и верни все задачи"

    }






    public void  deleteTask(Long id){
        //  Метод удаления:
        //принимает id
        //void → ничего не возвращает

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
// Пытаемся найти задачу в базе по id
// findById возвращает Optional (либо есть значение, либо нет)
// если задача найдена → кладём её в existingTask
// если НЕ найдена → выбрасываем ошибку "Task not found"

        taskRepository.delete(existingTask);
// Удаляем найденную задачу из базы
// delete принимает сам объект Task
// сюда мы передаём уже найденную задачу existingTask



        // получить → List<Task>
        //сохранить → Task
        //удалить → void
    }
}

