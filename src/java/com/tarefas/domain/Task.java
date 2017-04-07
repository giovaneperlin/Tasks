package com.tarefas.domain;

/**
 *
 * @author Lucas O. Queiroz
 * @author Giovane L. S. Perlin
 */
public class Task {

    private int id;
    private int personId;
    private String name;
    private String length;
    private String date;
    private String field;

    public Task() {

    }

    public Task(int id, int personId, String name, String length, String date, String field) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.length = length;
        this.date = date;
        this.field = field;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
