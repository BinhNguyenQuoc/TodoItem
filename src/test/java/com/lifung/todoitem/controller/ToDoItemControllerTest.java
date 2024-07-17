package com.lifung.todoitem.controller;

import com.lifung.todoitem.dto.ToDoItemDTO;
import com.lifung.todoitem.exception.GlobalExceptionHandler;
import com.lifung.todoitem.exception.TodoItemException;
import com.lifung.todoitem.mockdata.utils;
import com.lifung.todoitem.model.ToDoItem;
import com.lifung.todoitem.service.ToDoItemService;
import com.lifung.todoitem.mapper.ToDoItemMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ToDoItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ToDoItemService toDoItemService;

    @Mock
    private ToDoItemMapper toDoItemMapper;

    @InjectMocks
    private ToDoItemController toDoItemController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(toDoItemController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Optional: if you have a global exception handler
                .build();
    }

    @Test
    public void testCreateToDoItem_Success() throws Exception {
        // Mock data
        ToDoItemDTO requestDto = utils.mockTodoItem();

        ToDoItem mockCreatedItem = new ToDoItem();
        mockCreatedItem.setId(1L);
        mockCreatedItem.setUsername("testUser");
        mockCreatedItem.setDescription("Test ToDo Item");
        // Assuming your mapper converts DTO to entity correctly
        when(toDoItemMapper.toEntity(any(ToDoItemDTO.class))).thenReturn(mockCreatedItem);
        when(toDoItemMapper.toDTO(any(ToDoItem.class))).thenReturn(requestDto);

        when(toDoItemService.createToDoItem(any(ToDoItem.class))).thenReturn(mockCreatedItem);

        // Prepare request
        RequestBuilder requestBuilder = post("/api/v1/todoitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto));

        // Perform the request
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // Verify the response
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testCreateToDoItem_ValidationFailure() throws Exception {
        // Mock data with missing required fields
        ToDoItemDTO requestDto = new ToDoItemDTO();

        when(toDoItemMapper.toEntity(any(ToDoItemDTO.class))).thenReturn(new ToDoItem());
        when(toDoItemService.createToDoItem(any(ToDoItem.class))).thenThrow(new TodoItemException("Description cannot be null or empty", "EMPTY_DESCRIPTION"));
        // Prepare request
        RequestBuilder requestBuilder = post("/api/v1/todoitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto));

        // Perform the request
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // Verify the response
        resultActions.andExpect(status().isBadRequest());
    }

    // Helper method to convert object to JSON string
    private String asJsonString(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }
}
