package com.example.todo.repository;
// 📌 указываем, что этот класс находится в пакете repository (слой работы с БД)

import com.example.todo.model.Task;
// 📌 подключаем твою Entity Task (таблица из базы данных)

import org.springframework.data.jpa.repository.JpaRepository;
// 📌 импортируем готовый Spring-интерфейс с методами для работы с БД

public interface TaskRepository extends JpaRepository<Task, Long> {
// 📌 создаём Repository
// 📌 interface = контракт (мы НЕ пишем реализацию)
// 📌 extends JpaRepository = берём готовые методы Spring
// 📌 <Task, Long>:
//    Task = с какой таблицей работаем
//    Long = тип ID в таблице

}
