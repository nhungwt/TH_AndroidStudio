package com.example.th_4_13.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private String name, content, status;
    private Date deadline;
    private Boolean colaboration;

    public Task(int id, String name, String content, String status, String deadline, Boolean colaboration) throws ParseException {
        this.id = id;
        this.name = name;
        this.content = content;
        this.status = status;
        this.deadline = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);
        this.colaboration = colaboration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return new SimpleDateFormat("dd/MM/yyyy").format(deadline);
    }

    public void setDeadline(String deadline) throws ParseException {
        this.deadline = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);
    }

    public Boolean getColaboration() {
        return colaboration;
    }

    public void setColaboration(Boolean colaboration) {
        this.colaboration = colaboration;
    }
}
