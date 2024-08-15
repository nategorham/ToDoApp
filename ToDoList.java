package ToDoApp;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void start(Scanner kbd) {
        System.out.println("Welcome to To-Do List Command Line!\n");

        boolean cont = true;
        int input = 0;

        do {
            System.out.println("What would do?\n" +
                "1) Add tasks\n" +
                "2) Remove tasks\n" +
                "3) Mark tasks as completed\n" +
                "4) Quit");

            switch (input) {
                case 1: 
                    addTask(kbd);
                    break;
                case 2: 
                    removeTask(kbd);
                    break;
                case 3:
                    completeTask(kbd);
                    break;
                case 4:
                    cont = false;
                    System.out.println("Shutting down To-Do List Command Line...\n" +
                        "Goodbye!");
                    break;
                default:
                    System.out.println("Please try again with a valid input!");
            }

        } while (cont);
    }

    private void addTask(Scanner kbd) {
        System.out.print("Enter a task: ");
        add(kbd.nextLine());
    }

    private void removeTask(Scanner kbd) {
        print();
        System.out.println("Which task would you like to remove? Enter the number associated with it.");
        remove(kbd.nextInt());
    }

    private void completeTask(Scanner kbd) {
        print();
        System.out.println("Which task would you like to mark as completed? Enter the number associated with it.");
        complete(kbd.nextInt());
    }

    //print
    public void print() {
        int i = 1;

        System.out.println("To-Do:");

        for (Task task : todoList) {
            System.out.printf("%d) %s", i++, task.toString());
        }
    }
}
