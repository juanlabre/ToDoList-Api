package org.example.service;

import org.example.model.ToDoList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Path("/to-do-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToDoListService {
    Logger logger = Logger.getLogger("Log");
    FileHandler fileHandler;
    private final Set<ToDoList> toDoLists = new HashSet<>();

    public ToDoListService() {
        try {
            fileHandler = new FileHandler("src/main/resources/LogToDoListService.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.info("Init log");

            toDoLists.add(new ToDoList("Test1", "desc test1", "COMPLETE"));
            toDoLists.add(new ToDoList("Test2", "desc test2", "INCOMPLETE"));
            toDoLists.add(new ToDoList("Test3", "desc test3", "COMPLETE"));

        } catch (SecurityException e) {
            logger.info("Exception:" + e.getMessage());
        } catch (IOException e) {
            logger.info("IO Exception:" + e.getMessage());
        }
    }

    @GET
    public Set<ToDoList> list() {
        logger.info(
                "GET /to-do-list\n"+
                "\tResponse: "+toDoLists);

        return toDoLists;
    }

    @POST
    public Set<ToDoList> add(ToDoList element) {
        toDoLists.add(element);

        logger.info(
                "POST /to-do-list\n"+
                "\tRequest: "+element+"\n"+
                "\tResponse: "+toDoLists
        );

        return toDoLists;
    }

    @DELETE
    public Set<ToDoList> delete(ToDoList element) {
        toDoLists.removeIf(value -> value.getTitle().contentEquals(element.getTitle()));

        logger.info(
                "DELETE /to-do-list\n"+
                "\tRequest: "+element+"\n"+
                "\tResponse: "+toDoLists
        );

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

        logger.info(
                "PUT /to-do-list\n"+
                "\tRequest: "+element+"\n"+
                "\tResponse: "+toDoLists
        );

        return toDoLists;
    }

    @PUT
    @Path("/manage-state/{title}")
    public Set<ToDoList> manageState(@PathParam("title") String title) {
        toDoLists.forEach(value -> {
            if (value.getTitle().equals(title)) {
                value.setState(value.getState().equals("COMPLETE")?"INCOMPLETE":"COMPLETE");
            }
        });

        logger.info(
                "PUT /to-do-list/update-state/"+title+"\n"+
                "\tResponse: "+toDoLists
        );

        return toDoLists;
    }

    @GET
    @Path("/search/{title}")
    public ToDoList searchByTitle(@PathParam("title") String title) {
        ToDoList toDoListSearched = null;
        for (ToDoList toDoList : toDoLists) {
            if(toDoList.getTitle().equals(title)) {
                toDoListSearched = toDoList;
                break;
            }
        }

        logger.info("GET /to-do-list/search/"+title+"\n"+
                "\tResponse: "+toDoListSearched
        );

        return toDoListSearched;
    }
}