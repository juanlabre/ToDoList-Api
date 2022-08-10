package org.example.service;

import org.example.model.ToDoList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Path("/to-do-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoListService {

    private Set<ToDoList> toDoLists = new HashSet<>();

    public ToDoListService() {
        toDoLists.add(new ToDoList("Test1", "desc test1", "COMPLETE"));
        toDoLists.add(new ToDoList("Test2", "desc test2", "INCOMPLETE"));
        toDoLists.add(new ToDoList("Test3", "desc test3", "COMPLETE"));
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
                value.setState(element.getState());
            }
        });
        return toDoLists;
    }

    @PUT
    @Path("/update-state/{title}")
    public Set<ToDoList> updateState(@PathParam("title") String title, String state) {
        toDoLists.forEach(value -> {
            if (value.getTitle().equals(title)) {
                value.setState(state);
            }
        });
        return toDoLists;
    }

    @GET
    @Path("/search/{title}")
    public ToDoList searchByTitle(@PathParam("title") String title) {
        return toDoLists.stream()
                .filter(toDoList -> toDoList.getTitle().equals(title))
                .findAny()
                .orElse(null);
    }
}