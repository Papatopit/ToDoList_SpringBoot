package main;

import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @RequestMapping(value = "/toDoList/", method = RequestMethod.GET)
    public List<ToDoList> list() {
        Iterable<ToDoList> toDoListIterable = toDoListRepository.findAll();
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        for (ToDoList toDoList : toDoListIterable) {
            toDoLists.add(toDoList);
        }
        return toDoLists;
    }

    @RequestMapping(value = "/toDoList/", method = RequestMethod.POST)
    public int add(@RequestBody ToDoList toDoList) {
        ToDoList newToDo = toDoListRepository.save(toDoList);
        return newToDo.getId();
    }

    @GetMapping("/toDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);

        if (!optionalToDoList.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalToDoList.get(), HttpStatus.OK);
    }

    @PutMapping("/toDoList/")
    public ResponseEntity<String> edit(int id, ToDoList newToDo) {

        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);

        if (optionalToDoList.isPresent()){
            newToDo.setId(id);
            toDoListRepository.save(newToDo);
            return new ResponseEntity<>("ToDoList update id:"+id,HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/toDoList/{id}")
    public int removeToDo(@PathVariable int id) {
        toDoListRepository.deleteById(id);
        return id;
    }

    @DeleteMapping("/toDoList/")
    public ResponseEntity<String> delete() {
        toDoListRepository.deleteAll();
        return new ResponseEntity("ToDoList is Clear",HttpStatus.OK);
    }
}
