package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

import jakarta.transaction.Transactional;

@Service
public class ToDoService {
	private final ToDoRepository todoRepository;
	
	//依存性の注入
	public ToDoService(ToDoRepository todoRepository) {
this.todoRepository = todoRepository;
	}
	
//todoリストのエンティティを作成してデータベースに保存
	
    @Transactional
    public ToDo createToDo(String title, ToDo.Priority priority, ToDo.Status status) {
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setPriority(priority);
        todo.setStatus(status);
        return todoRepository.save(todo); // データベースに保存

    }
public List<ToDo> getToDoList(){
return todoRepository.findAll();	
}
}
