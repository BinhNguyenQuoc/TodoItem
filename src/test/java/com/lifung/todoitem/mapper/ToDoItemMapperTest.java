package com.lifung.todoitem.mapper;

import com.lifung.todoitem.dto.ToDoItemDTO;
import com.lifung.todoitem.model.ToDoItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ToDoItemMapperTest {

    @InjectMocks
    private ToDoItemMapper toDoItemMapper;

    Date today = new Date(2024, 7, 17);

    @Test
    public void testToDTOSuccess() {
        // Prepare a ToDoItem
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName("Test ToDo");
        toDoItem.setDescription("Sample description");
        toDoItem.setUsername("testUser");
        toDoItem.setStatus(true);
        toDoItem.setStartDate(today);

        // Call toDTO method
        ToDoItemDTO toDoItemDTO = toDoItemMapper.toDTO(toDoItem);

        // Verify conversion
        assertEquals("Test ToDo", toDoItemDTO.getName());
        assertEquals("Sample description", toDoItemDTO.getDescription());
        assertEquals("testUser", toDoItemDTO.getUsername());
        assertTrue(toDoItemDTO.isStatus());
        assertEquals(today, toDoItemDTO.getStartDate());
    }

    @Test
    public void testToDTONullInput() {
        // Call toDTO method with null input
        ToDoItemDTO toDoItemDTO = toDoItemMapper.toDTO(null);

        // Verify conversion
        assertNull(toDoItemDTO);
    }

    @Test
    public void testToEntitySuccess() {
        // Prepare a ToDoItemDTO
        ToDoItemDTO toDoItemDTO = new ToDoItemDTO();
        toDoItemDTO.setName("Test ToDo");
        toDoItemDTO.setDescription("Sample description");
        toDoItemDTO.setUsername("testUser");
        toDoItemDTO.setStatus(true);
        toDoItemDTO.setStartDate(today);

        // Call toEntity method
        ToDoItem toDoItem = toDoItemMapper.toEntity(toDoItemDTO);

        // Verify conversion
        assertEquals("Test ToDo", toDoItem.getName());
        assertEquals("Sample description", toDoItem.getDescription());
        assertEquals("testUser", toDoItem.getUsername());
        assertTrue(toDoItem.isStatus());
        assertEquals(today, toDoItem.getStartDate());
    }

    @Test
    public void testToEntityNullInput() {
        // Call toEntity method with null input
        ToDoItem toDoItem = toDoItemMapper.toEntity(null);

        // Verify conversion
        assertNull(toDoItem);
    }
}