package ToDoApp;

public class Task {
    private String task;
    private boolean status;

    public Task() {
        task = "";
        status = false;
    }

    public Task(String task) {
        this.task = task;
        status = false;
    }

    public Task(String task, boolean status) {
        this.task = task;
        this.status = status;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public boolean getStatus() {
        return status;
    }

    public void update() {
        status = true;
    }

    public String toString() {
        return String.format("%s | completed: %B\n", task, status);
    }
}
