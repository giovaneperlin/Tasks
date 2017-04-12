package com.tarefas.connection;

import com.tarefas.domain.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Connection {

    private static java.sql.Connection conn;
    private static PreparedStatement stm;

    private static void open(String sql) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/tasks",
                    "root",
                    "");
            stm = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Não foi possivel connectar ao banco.\n" + e);
            System.exit(1);
        }
    }

    private static void close() throws SQLException {
        stm.close();
        conn.close();
        conn = null;
        stm = null;
    }

    public static boolean insertPerson(Person person) {
        try {
            String sql = "INSERT INTO persons (`name`, `age`, `role`) VALUES (?, ?, ?)";

            open(sql);

            stm.setString(1, person.getName());
            stm.setInt(2, person.getAge());
            stm.setString(3, person.getRole());
            stm.execute();
            close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //done: 0= task NOT completed, 1= task completed, 2=all tasks
    //loadData: true=load person's tasks, false= don´t load person's tasks
    public static ArrayList<Person> selectPersons(boolean loadData, int done) {
        try {
            String sql = "SELECT * FROM persons";

            open(sql);

            ResultSet resultSet = stm.executeQuery();

            ArrayList<Person> persons = new ArrayList<Person>();

            while (resultSet.next()) {
                Person person = new Person();
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setRole(resultSet.getString("role"));
                person.setId(resultSet.getInt("id"));

                persons.add(person);
            }

            if (loadData) {
                for (Person person : persons) {
                    person.setTasks(selectTasks(person, done));
                }
            } else {
                close();
            }

            return persons;

        } catch (SQLException e) {

            return null;

        }
    }

    //done: 0= task NOT completed, 1= task completed, 2=all tasks
    //loadData: true=load person's tasks, false= don´t load person's tasks
    public static Person selectPerson(String name, boolean loadData, int done) {
        try {
            String sql = "SELECT * FROM persons WHERE `name`=?";

            open(sql);

            stm.setString(1, name);

            ResultSet resultSet = stm.executeQuery();

            Person person = null;

            while (resultSet.next()) {
                person = new Person();
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setRole(resultSet.getString("role"));
                person.setId(resultSet.getInt("id"));
            }

            if (loadData && person != null) {
                person.setTasks(selectTasks(person, done));
            } else {
                close();
            }

            return person;

        } catch (SQLException e) {

            return null;

        }
    }

    //done: 0= task NOT completed, 1= task completed, 2=all tasks
    //loadData: true=load person's tasks, false= don´t load person's tasks
    public static Person selectPersonById(int id) {
        try {
            String sql = "SELECT * FROM persons WHERE `id`=?";

            open(sql);

            stm.setInt(1, id);

            ResultSet resultSet = stm.executeQuery();

            Person person = null;

            while (resultSet.next()) {
                person = new Person();
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setRole(resultSet.getString("role"));
                person.setId(resultSet.getInt("id"));
            }

            close();

            return person;

        } catch (SQLException e) {

            return null;

        }
    }

    //done: 0= tasks NOT completed, 1= tasks completed, 2=all tasks
    public static ArrayList<Task> selectTasks(Person person, int done) {
        try {

            String sql = null;

            sql = "SELECT * FROM tasks WHERE `person_id`=?";
            open(sql);
            stm.setInt(1, person.getId());

            ResultSet resultSet = stm.executeQuery();

            ArrayList<Task> tasksNotCompleted = new ArrayList<Task>();
            ArrayList<Task> tasksCompleted = new ArrayList<Task>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (resultSet.next()) {

                String date = resultSet.getString("date");
                int length = resultSet.getInt("length");

                LocalDate taskDate = java.time.LocalDate.parse(date);
                taskDate = taskDate.plusDays(length);

                LocalDate actualDate = java.time.LocalDate.now();

                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setLength(length);
                task.setDate(dtf.format(java.time.LocalDate.parse(date)));
                task.setField(resultSet.getString("field"));
                task.setId(resultSet.getInt("id"));
                task.setPersonId(resultSet.getInt("person_id"));

                if (taskDate.isBefore(actualDate) || taskDate.isEqual(actualDate)) {
                    task.setDone(true);
                    tasksCompleted.add(task);
                } else {
                    task.setDone(false);
                    tasksNotCompleted.add(task);
                }
            }

            close();

            if (done == 2) {
                tasksCompleted.addAll(tasksNotCompleted);
                return tasksCompleted;
            }
            if (done == 1) {
                return tasksCompleted;
            }
            if (done == 0) {
                return tasksNotCompleted;
            }
            return null;

        } catch (SQLException e) {

            return null;

        }
    }

    public static boolean insertTask(Task task) {
        try {
            String sql = "INSERT INTO `tasks`(`name`, `length`, `date`, `field`, `person_id`) VALUES (?, ?, ?, ?, ?)";

            open(sql);
            stm.setString(1, task.getName());
            stm.setInt(2, task.getLength());
            stm.setDate(3, java.sql.Date.valueOf(task.getDate()));
            stm.setString(4, task.getField());
            stm.setInt(5, task.getPersonId());
            stm.execute();
            close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //done: 0= task NOT completed, 1= task completed, 2=all tasks
    public static ArrayList<Person> selectTasksByDate(String date) {
        try {

            String sql = "SELECT * FROM tasks WHERE `date`=?";

            open(sql);

            stm.setDate(1, java.sql.Date.valueOf(date));

            ResultSet resultSet = stm.executeQuery();

            ArrayList<Person> persons = new ArrayList<Person>();

            while (resultSet.next()) {
                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setLength(resultSet.getInt("length"));
                task.setDate(resultSet.getDate("date").toString());
                task.setField(resultSet.getString("field"));
                task.setId(resultSet.getInt("id"));
                task.setPersonId(resultSet.getInt("person_id"));

                Person person = selectPersonById(task.getPersonId());
                person.getTasks().add(task);

                persons.add(person);
            }

            return persons;

        } catch (SQLException e) {

            return null;

        }
    }
}
