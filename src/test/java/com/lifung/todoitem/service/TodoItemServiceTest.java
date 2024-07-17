package com.lifung.todoitem.service;

import com.lifung.todoitem.exception.TodoItemException;
import com.lifung.todoitem.model.ToDoItem;
import com.lifung.todoitem.repository.ToDoItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoItemServiceTest {

    @Mock
    private ToDoItemRepository toDoItemRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ToDoItemService toDoItemService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateToDoItem_Success() throws TodoItemException {
        // Prepare data
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setUsername("testUser");
        toDoItem.setDescription("Test ToDo");

        // Mock repository behavior
        when(toDoItemRepository.save(any(ToDoItem.class))).thenReturn(toDoItem);

        // Call the service method
        ToDoItem createdItem = toDoItemService.createToDoItem(toDoItem);

        // Verify the result
        assertNotNull(createdItem);
        assertEquals("testUser", createdItem.getUsername());
        assertEquals("Test ToDo", createdItem.getDescription());

        // Verify repository interaction
        verify(toDoItemRepository).save(toDoItem);
    }

    @Test
    public void testCreateToDoItem_EmptyDescription() {
        // Prepare data with empty description
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setUsername("testUser");
        toDoItem.setDescription("");

        // Call the service method and expect TodoItemException
        TodoItemException exception = assertThrows(TodoItemException.class, () -> toDoItemService.createToDoItem(toDoItem));

        // Verify exception message and error code
        assertEquals("Description cannot be null or empty", exception.getMessage());
        assertEquals("EMPTY_DESCRIPTION", exception.getErrorCode());

        // Verify that repository save method was not called
        verify(toDoItemRepository, never()).save(any(ToDoItem.class));
    }
}
