package com.lifung.todoitem.mockdata;

import com.lifung.todoitem.dto.ToDoItemDTO;

import java.util.Date;

public final class utils {
    public static ToDoItemDTO mockTodoItem() {
        ToDoItemDTO requestDto = new ToDoItemDTO();
        requestDto.setUsername("testUser");
        requestDto.setDescription("Test ToDo Item");
        requestDto.setName("Test ToDo Name");
        requestDto.setStartDate(new Date());
        return requestDto;
    }
}
