package com.restfulapi.restfulapi.Service;

import com.restfulapi.restfulapi.Entity.Todo;
import com.restfulapi.restfulapi.Repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public Todo addToDo(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo getToDoById(long id){
        return findOrElseThrow(id);
    }

    public void deleteTodo(long id){
        Todo oldToDo = findOrElseThrow(id);
        todoRepository.delete(oldToDo);
    }

    public Todo updateTodo(Todo todo){
        Todo oldToDo = findOrElseThrow(todo.getId());
        oldToDo.setTitle(todo.getTitle() == null ? oldToDo.getTitle() : todo.getTitle());
        oldToDo.setCompleted(todo.isCompleted());
        return todoRepository.save(oldToDo);
    }

    public Todo setCompleteTodo(Long id){
        Todo oldToDo = findOrElseThrow(id);
        oldToDo.setCompleted(Boolean.TRUE);
        return todoRepository.save(oldToDo);
    }

    public Todo setUncompleteTodo(Long id){
        Todo oldToDo = findOrElseThrow(id);
        oldToDo.setCompleted(Boolean.FALSE);
        return todoRepository.save(oldToDo);
    }


    public Todo findOrElseThrow(long id){
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found with id: "+id));
    }

}
