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
                "2) Remove task\n" +
                "3) Mark task as completed\n" +
                "4) Print list\n"+
                "5) Quit");

            input = kbd.nextInt();
            kbd.nextLine();

            switch (input) {
                case 1: 
                    addTasks(kbd);
                    break;
                case 2: 
                    if (todoList.isEmpty()) {
                        System.out.println("There is nothing to remove!");
                    } else {
                        removeTask(kbd);
                    }
                    break;
                case 3:
                    if (todoList.isEmpty()) {
                        System.out.println("There is nothing to complete!");
                    } else {
                        completeTask(kbd);
                    }
                    break;
                case 4:
                    print();
                    break;
                case 5:
                    cont = false;
                    System.out.println("Shutting down To-Do List Command Line...\n" +
                        "Goodbye!");
                    break;
                default:
                    System.out.println("Please try again with a valid input!");
            }

        } while (cont);
    }

    private void addTasks(Scanner kbd) {
        String yn = "";

        do {
            addTask(kbd);

            System.out.println("would you like to add another task? (y/n)");
            yn = kbd.nextLine();
        } while (yn.toLowerCase().equals("y"));
    }

    private void addTask(Scanner kbd) {
        System.out.print("Enter a task: ");
        add(kbd.nextLine());
    }

    private void removeTask(Scanner kbd) {
        print();
        System.out.println("Which task would you like to remove? Enter the number associated with it.");
        remove(kbd.nextInt());
        kbd.nextLine();
    }

    private void completeTask(Scanner kbd) {
        print();
        System.out.println("Which task would you like to mark as completed? Enter the number associated with it.");
        complete(kbd.nextInt());
        kbd.nextLine();
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
