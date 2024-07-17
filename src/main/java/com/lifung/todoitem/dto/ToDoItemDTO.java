package com.lifung.todoitem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class ToDoItemDTO {

    @NotBlank(message = "Description is mandatory")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 4000 characters")
    private String description;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 150 characters")
    private String name;

    @NotBlank(message = "StartDate is mandatory")
    public Date startDate;

    @NotBlank(message = "Username is mandatory")
    private String username;

    private boolean status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}