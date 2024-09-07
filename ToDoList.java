package ToDoApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
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

    public void add(String task, boolean status) {
        todoList.add(new Task(task, status));
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
            System.out.println("What would you like to do?\n" +
                "1) Add tasks\n" +
                "2) Remove task\n" +
                "3) Mark task as completed\n" +
                "4) Print list\n"+
                "5) Import list\n" +
                "6) Quit");

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
                    importToDoList(kbd);
                    break;
                case 6:
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

    private void importToDoList(Scanner kbd) {

        // Showing txt files in this folder

        String path = System.getProperty("user.dir") + "/ToDoApp";

        File dir = new File(path);

        String[] textFiles = dir.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".txt");
            }
        });

        System.out.println("Select a to-do to import:");

        int i = 1;

        for (String string : textFiles) {
            System.out.printf("%d) %s\n", i++, string);
        }

        // select file

        System.out.println("Which list would you like to import? Enter the number associated with it.");

        String fileName = textFiles[kbd.nextInt() - 1];

        File importee = new File(path + "/" + fileName);

        Scanner importer;

        try {
            importer = new Scanner(importee);

            // parse data

            String taskLine = "";
            String curTask = "";
            boolean curStat = false;
            String[] parts = null;

            while (importer.hasNext()) {
                taskLine = importer.nextLine();

                parts = taskLine.split("\\|", 2);

                curTask = parts[0].trim();

                curStat = parts[1].endsWith("TRUE");


                add(curTask, curStat);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            importer = null;
        }

        importer.close();
    }
}
