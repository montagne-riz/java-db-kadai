package com.example.springkadaitodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springkadaitodo.service.ToDoService;

@Controller

public class ToDoController{ 
		private final ToDoService toDoService;
	
		public ToDoController(ToDoService toDoService) {
	        this.toDoService = toDoService;
	    }
	
		 @GetMapping("/todos")
		    public String todoList(Model model) {
		        // データベースから取得
		        model.addAttribute("todos", toDoService.getToDoList());
		        return "todoView"; // Thymeleafのテンプレート名
		    }
		

           @GetMapping("/")
             public String todo() {
                return "todoView";
	
	
	}
}
