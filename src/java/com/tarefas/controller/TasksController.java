package com.tarefas.controller;

import com.tarefas.connection.Connection;
import com.tarefas.domain.Person;
import com.tarefas.domain.Task;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lucas O. Queiroz
 * @author Giovane L. S. Perlin
 */
@Controller
public class TasksController {

    @RequestMapping("/")
    public String index() {

        return "createPerson";

    }

    @RequestMapping("/createPerson")
    public String createPerson() {

        return "createPerson";

    }

    @RequestMapping("/createdPerson")
    public String createdPerson(Model model, Person person) {
        if (person != null) {
            if (Connection.insertPerson(person)) {
                model.addAttribute("created", true);
            } else {
                model.addAttribute("created", false);
            }
        }
        return "createPerson";
    }

    @RequestMapping("/createTask")
    public String createTask(Model model) {
        ArrayList<Person> persons = Connection.selectPersons(false, 2);
        model.addAttribute("persons", persons);

        return "createTask";
    }

    @RequestMapping("/createdTask")
    public String createdTask(Model model, Task task) {
        ArrayList<Person> persons = Connection.selectPersons(false, 2);
        model.addAttribute("persons", persons);
        if (task != null) {
            if (Connection.insertTask(task)) {
                model.addAttribute("created", true);
            } else {
                model.addAttribute("created", false);
            }
        }
        return "createTask";
    }

    @RequestMapping("/listAllTasks")
    public String listAllTasks(Model model) {
        ArrayList<Person> persons = Connection.selectPersons(true, 2);
        model.addAttribute("persons", persons);
        return "listAllTasks";

    }

    @RequestMapping("/listClosedTasks")
    public String listClosedTasks(Model model) {
        ArrayList<Person> persons = Connection.selectPersons(true, 1);
        model.addAttribute("persons", persons);
        return "listClosedTasks";

    }

    @RequestMapping("/listOpenTasks")
    public String listOpenTasks(Model model) {
        ArrayList<Person> persons = Connection.selectPersons(true, 0);
        model.addAttribute("persons", persons);
        return "listOpenTasks";

    }

    @RequestMapping("/searchTasksByPerson")
    public String searchTasksByPerson() {

        return "searchTasksByPerson";

    }

    @RequestMapping("/searchedTasksByPerson")
    public String searchedTasksByPerson(Model model, String name) {
        if (name != null) {
            Person person = Connection.selectPerson(name, true, 2);
            model.addAttribute("person", person);
        }
        return "searchTasksByPerson";

    }

    @RequestMapping("/searchTasksByDate")
    public String searchTasksByDate() {

        return "searchTasksByDate";

    }

    @RequestMapping("/searchedTasksByDate")
    public String searchedTasksByDate(Model model, String date) {
        if (date != null) {
            ArrayList<Person> persons = Connection.selectTasksByDate(date);
            model.addAttribute("persons", persons);
        }
        return "searchTasksByDate";

    }
}
