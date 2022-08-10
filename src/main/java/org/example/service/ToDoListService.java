package org.example.service;

import org.example.model.ToDoList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Path("/to-do-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoListService {

    private Set<ToDoList> toDoLists = new HashSet<>();

    public ToDoListService() {
        toDoLists.add(new ToDoList("Test1", "desc test1"));
        toDoLists.add(new ToDoList("Test2", "desc test2"));
        toDoLists.add(new ToDoList("Test3", "desc test3"));
    }

    @GET
    public Set<ToDoList> list() {
        return toDoLists;
    }

    @POST
    public Set<ToDoList> add(ToDoList element) {
        toDoLists.add(element);
        return toDoLists;
    }

    @DELETE
    public Set<ToDoList> delete(ToDoList element) {
        toDoLists.removeIf(value -> value.getTitle().contentEquals(element.getTitle()));
        return toDoLists;
    }

    @PUT
    public Set<ToDoList> update(ToDoList element) {
        toDoLists.forEach(value -> {
            if (value.getTitle().equals(element.getTitle())) {
                value.setDescription(element.getDescription());
            }
        });
        return toDoLists;
    }
}