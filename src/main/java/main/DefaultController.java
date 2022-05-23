package main;

import main.model.ToDoList;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
@Controller
public class DefaultController
{
    @Autowired
    private ToDoListRepository toDoListRepository;
    @Value("${someParametr}")
    private Integer someParametr;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<ToDoList> toDoListIterable = toDoListRepository.findAll();
        ArrayList<ToDoList> toDoListArrayList = new ArrayList<>();
        for (ToDoList toDoList: toDoListIterable){
            toDoListArrayList.add(toDoList);
        }
        model.addAttribute("ToDoLists",toDoListArrayList);
        model.addAttribute("ToDoListCount",toDoListArrayList.size());
        model.addAttribute("someParametr",someParametr);

        return "index";
    }
}
