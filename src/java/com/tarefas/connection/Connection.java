package com.tarefas.connection;

import com.tarefas.domain.*;
import java.sql.*;
import java.util.ArrayList;

public class Connection {

    private static java.sql.Connection conn;
    private static PreparedStatement stm;

    private static void open(String sql) {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/tasks",
                    "root",
                    "barril135");
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
            }

            close();

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

            if (loadData) {
                person.setTasks(selectTasks(person, done));
            }

            close();

            return person;

        } catch (SQLException e) {

            return null;

        }
    }

    //done: 0= task NOT completed, 1= task completed, 2=all tasks
    public static ArrayList<Task> selectTasks(Person person, int done) {
        try {

            String sql = null;

            if (done != 2) {

                sql = "SELECT * FROM tasks WHERE `person_id`=? AND `done`=?";
                open(sql);
                stm.setInt(1, person.getId());

                if (done == 0) {

                    stm.setBoolean(2, false);

                } else {

                    stm.setBoolean(2, true);

                }
            } else {
                sql = "SELECT * FROM tasks WHERE `person_id`=?";
                open(sql);
            }

            ResultSet resultSet = stm.executeQuery();

            ArrayList<Task> tasks = new ArrayList<Task>();

            while (resultSet.next()) {
                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setLength(resultSet.getString("legth"));
                task.setDate(resultSet.getString("date"));
                task.setField(resultSet.getString("field"));
                task.setDone(resultSet.getBoolean("done"));
                task.setId(resultSet.getInt("id"));
                task.setPersonId(resultSet.getInt("person_id"));

                tasks.add(task);
            }

            close();

            return tasks;

        } catch (SQLException e) {

            return null;

        }
    }

    public static boolean insertTask(Task task) {
        try {
            String sql = "INSERT INTO `tasks`(`name`, `length`, `date`, `field`, `done`, `person_id`) VALUES (?, ?, ?, ?, ?, ?)";

            open(sql);

            stm.setString(1, task.getName());
            stm.setString(2, task.getLength());
            stm.setString(3, task.getDate());
            stm.setString(4, task.getField());
            stm.setBoolean(5, task.isDone());
            stm.setInt(6, task.getPersonId());
            stm.execute();
            close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
