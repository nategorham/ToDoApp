package ToDoApp;

import java.util.Scanner;

public class ToDo 
{
    public static void main(String[] args) {
        ToDoList todo = new ToDoList();
        Scanner kbd = new Scanner(System.in);

        todo.start(kbd);

        kbd.close();
    }
}