package main;

import org.springframework.http.ResponseEntity;
import main.model.ToDoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer,ToDoList> toDoLists = new HashMap<>();

    public static List<ToDoList> getAllToDoLists(){
        ArrayList<ToDoList> toDoList = new ArrayList<>();
        toDoList.addAll(toDoLists.values());
        return toDoList;
    }

    public static int addToDoList(ToDoList toDoList){
        int id = currentId;
        toDoList.setId(id);
        toDoLists.put(id,toDoList);
        currentId++;
        return id;
    }
    public static ToDoList getToDo(int toDoId){
        if (toDoLists.containsKey(toDoId)){
            return toDoLists.get(toDoId);
        }
        return null;
    }
    public static ToDoList editToDoList(ToDoList oneDeal){
        int id = oneDeal.getId();
        toDoLists.replace(id,oneDeal);
        return oneDeal;
    }

    public static ResponseEntity<Integer> removeToDo(int toDoId){
        if (!toDoLists.containsKey(toDoId)){
           return ResponseEntity.notFound().build();
        }
        toDoLists.remove(toDoId);
        return ResponseEntity.ok(toDoId);
    }
    public static List<ToDoList> deleteAllToDoLists(ToDoList ToDoList){
        ArrayList<ToDoList> deleteToDoList = new ArrayList<>();
        deleteToDoList.removeAll(toDoLists.values());
        return deleteToDoList;
    }
}
