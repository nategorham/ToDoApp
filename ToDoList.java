package ToDoApp;

import java.util.ArrayList;

public class ToDoList {
    
    private ArrayList<Task> todoList;

    public ToDoList() {
        todoList = new ArrayList<>();
    }

    //add
    public void add(String task) {
        todoList.add(new Task(task));
    }

    //remove
    public void remove(int idx) {
        todoList.remove(idx - 1);
    }

    //complete
    public void complete(int idx) {
        todoList.get(idx - 1).update();
    }

    //print
    public void print() {
        System.out.println("To-Do:");

        for (Task task : todoList) {
            System.out.print(task.toString());
        }
    }
}
