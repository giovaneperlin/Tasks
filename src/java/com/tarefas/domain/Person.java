package com.tarefas.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Lucas O. Queiroz
 * @author Giovane L. S. Perlin
 */
public class Person implements Serializable {
    
    private int id;
    private String name;
    private int age;
    private String role;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Person() {

    }

    public Person(int id, String name, int age, String role, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
        this.tasks = tasks;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    
}
