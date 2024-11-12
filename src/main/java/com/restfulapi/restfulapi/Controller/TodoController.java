package com.restfulapi.restfulapi.Controller;

import com.restfulapi.restfulapi.Entity.Todo;
import com.restfulapi.restfulapi.Service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/")
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.addToDo(todo);
    }

    @GetMapping("/all")
    public List<Todo> getALlTodos(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") Long id){
        return todoService.getToDoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return "Todo deleted successfully";
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable("id") Long id, @RequestBody Todo todo){
        todo.setId(id);
        return todoService.updateTodo(todo);
    }

    @PatchMapping("/{id}/completed")
    public Todo completedToDo(@PathVariable("id") Long id){
        return todoService.setCompleteTodo(id);
    }

    @PatchMapping("/{id}/uncompleted")
    public Todo uncompletedToDo(@PathVariable("id") Long id){
        return todoService.setUncompleteTodo(id);
    }

}
