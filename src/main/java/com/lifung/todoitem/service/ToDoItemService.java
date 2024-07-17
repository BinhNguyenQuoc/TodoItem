package com.lifung.todoitem.service;

import com.lifung.todoitem.exception.TodoItemException;
import com.lifung.todoitem.model.ToDoItem;
import com.lifung.todoitem.repository.ToDoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(ToDoItemService.class);

    public ToDoItem createToDoItem(ToDoItem toDoItem) throws TodoItemException {
        if (toDoItem.getDescription() == null || toDoItem.getDescription().isEmpty()) {
            logger.error("Description cannot be null or empty");
            throw new TodoItemException("Description cannot be null or empty", "EMPTY_DESCRIPTION");
        }
        logger.info("Creating ToDo item for username: {}", toDoItem.getUsername());
        return toDoItemRepository.save(toDoItem);
    }

    public List<ToDoItem> getToDoItemsByUsername(String username) throws TodoItemException {
        logger.info("Fetching ToDo items for username: {}", username);
        try {
            List<ToDoItem> items = toDoItemRepository.findByUsername(username);
            return items;
        } catch (Exception e) {
            logger.error("Error fetching ToDo items for username: {}", username, e);
            throw new TodoItemException("Error fetching ToDo items for user: " + username, "FETCH_ERROR", e);
        }
    }

    public ToDoItem updateToDoItem(Long id, ToDoItem updatedToDoItem) throws TodoItemException {
        try {
            Optional<ToDoItem> existingToDoItemOptional = toDoItemRepository.findById(id);
            if (!existingToDoItemOptional.isPresent()) {
                logger.warn("ToDo item not found with id: {}", id);
                throw new TodoItemException("ToDo item not found with id: " + id, "ITEM_NOT_FOUND");
            }
            ToDoItem existingToDoItem = existingToDoItemOptional.get();
            existingToDoItem.setDescription(updatedToDoItem.getDescription());
            existingToDoItem.setStatus(updatedToDoItem.isStatus());

            logger.info("Updating ToDo item with id: {}", id);
            return toDoItemRepository.save(existingToDoItem);
        } catch (Exception e) {
            logger.error("Error updating ToDo item with id: {}", id, e);
            throw new TodoItemException("Error updating ToDo item with id: " + id, "UPDATE_ERROR", e);
        }
    }

    public void deleteToDoItem(Long id) throws TodoItemException {
        try {
            Optional<ToDoItem> existingToDoItemOptional = toDoItemRepository.findById(id);
            if (!existingToDoItemOptional.isPresent()) {
                logger.warn("ToDo item not found with id: {}", id);
                throw new TodoItemException("ToDo item not found with id: " + id, "ITEM_NOT_FOUND");
            }
            logger.info("Deleting ToDo item with id: {}", id);
            toDoItemRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting ToDo item with id: {}", id, e);
            throw new TodoItemException("Error deleting ToDo item with id: " + id, "DELETE_ERROR", e);
        }
    }

}