package DSAQuestions;

import java.util.*;

// Task application

// Entityies
/*
 * Task Manager(singleTon)
 * Task (name, description, status)
 * User(name, email, createTask, editTask, changeStatus)
 * Status(TOOD, PROGRESS, COMPLETED)
 */

public class Todo {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();
        User user = new User("Vivek", "viveks1114@gmail.com");
        taskManager.registerUser(user);

        user.createTask("1", "Task1", "Desc1");
        user.editTask("1", "newDesc");
        user.changeStatus("1", new CompletedStatus());
        user.filteTasks(new CompletedStatus());
        
    }
}

class TaskManager {
    List<User> allUsers;
    protected static TaskManager taskManager;

    private TaskManager() {
        allUsers = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (taskManager == null) {
            taskManager = new TaskManager();
        }

        return taskManager;
    }

    public void registerUser(User user) {
        allUsers.add(user);
    }

}

class User {
    String name;
    String email;
    Map<String, Task> tasks;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        tasks = new HashMap<>();
    }

    public void createTask(String taskId, String name, String description) {
        Task newTask = new Task(taskId, name, description);
        tasks.put(taskId, newTask);
    }

    public boolean editTask(String taskId, String newDes) {
        if (tasks.containsKey(taskId)) {
            Task temp = tasks.get(taskId);
            temp.description = newDes;
            tasks.put(taskId, temp);
            return true;
        }
        return false;
    }

    public boolean changeStatus(String taskId, Status status) {
        if (tasks.containsKey(taskId)) {
            Task temp = tasks.get(taskId);
            temp.status = status;
            tasks.put(taskId, temp);
            return true;
        }
        return false;
    }

    public List<Task> filteTasks(Status status){
        List<Task> res = new ArrayList<>();
        List<Task> allTask = (List<Task>)tasks.values();
        for(int i = 0; i < allTask.size(); i++){
            Task task = allTask.get(i);
            if(task.status.status == status.status){
                res.add(task);
            }
        }
        return res;
    }

}

class Task {
    String taskId;
    String name;
    String description;
    Status status;

    public Task(String taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.status = new TodoStatus();
    }
}

abstract class Status {
    StatusType status;

    protected void changeStatus(StatusType status) {
        this.status = status;
    }
}

class TodoStatus extends Status {

    public void TodoStatus() {
        super.changeStatus(StatusType.TODO);
    }
}

class ProgressStatus extends Status {
    public void ProgressStatus() {
        super.changeStatus(StatusType.PROGRESS);
    }
}

class CompletedStatus extends Status {

    public void CompletedStatus() {
        super.changeStatus(StatusType.COMPLETED);
    }
}

enum StatusType {
    TODO, PROGRESS, COMPLETED
}