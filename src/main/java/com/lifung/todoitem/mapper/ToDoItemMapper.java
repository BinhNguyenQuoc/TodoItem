package com.lifung.todoitem.mapper;

import com.lifung.todoitem.dto.ToDoItemDTO;
import com.lifung.todoitem.model.ToDoItem;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemMapper {

    public ToDoItemDTO toDTO(ToDoItem toDoItem) {
        if (toDoItem == null) {
            return null;
        }

        ToDoItemDTO toDoItemDTO = new ToDoItemDTO();
        toDoItemDTO.setName(toDoItem.getName());
        toDoItemDTO.setDescription(toDoItem.getDescription());
        toDoItemDTO.setUsername(toDoItem.getUsername());
        toDoItemDTO.setStatus(toDoItem.isStatus());
        toDoItemDTO.setStartDate(toDoItem.getStartDate());
        return toDoItemDTO;
    }

    public ToDoItem toEntity(ToDoItemDTO toDoItemDTO) {
        if (toDoItemDTO == null) {
            return null;
        }

        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(toDoItemDTO.getName());
        toDoItem.setDescription(toDoItemDTO.getDescription());
        toDoItem.setUsername(toDoItemDTO.getUsername());
        toDoItem.setStatus(toDoItemDTO.isStatus());
        toDoItem.setStartDate(toDoItemDTO.getStartDate());

        return toDoItem;
    }
}
