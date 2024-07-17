package com.lifung.todoitem.controller;

import com.lifung.todoitem.dto.ToDoItemDTO;
import com.lifung.todoitem.exception.TodoItemException;
import com.lifung.todoitem.mapper.ToDoItemMapper;
import com.lifung.todoitem.model.ToDoItem;
import com.lifung.todoitem.service.ToDoItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api", produces="application/json")

public class ToDoItemController {

    @Autowired
    private ToDoItemService toDoService;

    @Autowired
    private ToDoItemMapper toDoItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);

    @PostMapping("/v1/todoitem")
    public ResponseEntity<ToDoItemDTO> createToDoItem(@Valid @RequestBody ToDoItemDTO toDoItemDTO) {
        logger.info("Creating new todoItem for username: {}", toDoItemDTO.getUsername());
        try {
            ToDoItem createdItem = toDoService.createToDoItem(toDoItemMapper.toEntity(toDoItemDTO));
            return ResponseEntity.ok(toDoItemMapper.toDTO(createdItem));
        } catch (TodoItemException e) {
            logger.info("Error while creating todoItem for username: {}", toDoItemDTO.getUsername(), e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/v1/todoitem/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @RequestBody ToDoItem updatedToDoItem) {
        logger.info("Updating todoItem for id: {}", id);
        try {
            ToDoItem toDoItem = toDoService.updateToDoItem(id, updatedToDoItem);
            return ResponseEntity.ok(toDoItem);
        } catch (TodoItemException e) {
            logger.info("Error while updating todoItem for id: {}", id, e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/v1/todoitem/user/{username}")
    public ResponseEntity<List<ToDoItemDTO>> getToDoItemsByUsername(@PathVariable String username) {
        logger.info("Fetching todoItem for username: {}", username);
        try {
            List<ToDoItem> items = toDoService.getToDoItemsByUsername(username);
            List<ToDoItemDTO> responseDTOs = items.stream().map(item -> {
                ToDoItemDTO dto = toDoItemMapper.toDTO(item);
                return dto;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(responseDTOs);
        } catch (TodoItemException e) {
            logger.info("Error while updating todoItem for username: {}", username, e);
            return (ResponseEntity<List<ToDoItemDTO>>) ResponseEntity.notFound();
        }

    }

    @DeleteMapping("/v1/todoitem/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        logger.info("Deleting todoItem for id: {}", id);
        try {
            toDoService.deleteToDoItem(id);
            return (ResponseEntity) ResponseEntity.ok();
        } catch (TodoItemException e) {
            logger.info("Error while deleting todoItem for id: {}", id, e);
            return  ResponseEntity.noContent().build();
        }
    }
}