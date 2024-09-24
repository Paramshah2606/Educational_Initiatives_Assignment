import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private boolean completed;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void editTask(String newDescription, String newStartTime, String newEndTime, String newPriority) {
        this.description = newDescription;
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        this.priority = newPriority;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description + " [" + priority + "]" + (completed ? " (Completed)" : "");
    }
}

class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        if (validateTimeFormat(startTime) && validateTimeFormat(endTime)) {
            if (startTime.compareTo(endTime) < 0) {
                return new Task(description, startTime, endTime, priority);
            } else {
                System.out.println("Error: End time must be after start time.");
            }
        } else {
            System.out.println("Error: Invalid time format. Please use HH:mm format.");
        }
        return null;
    }

    private static boolean validateTimeFormat(String time) {
        return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }
}

class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private TaskConflictObserver observer;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observer = new TaskConflictObserver();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        if (task != null && !checkForConflicts(task)) {
            tasks.add(task);
            System.out.println("Task added successfully. No conflicts.");
        } else {
            observer.notifyConflict(task);
        }
    }

    public void removeTask(String description) {
        boolean removed = tasks.removeIf(task -> task.getDescription().equalsIgnoreCase(description));
        if (removed) {
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void editTask(String description, String newDescription, String newStartTime, String newEndTime, String newPriority) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                task.editTask(newDescription, newStartTime, newEndTime, newPriority);
                System.out.println("Task updated successfully.");
                return;
            }
        }
        System.out.println("Error: Task not found.");
    }

    public void markTaskCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                task.markCompleted();
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Error: Task not found.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            tasks.sort(Comparator.comparing(Task::getStartTime));
            tasks.forEach(System.out::println);
        }
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with priority: " + priority);
        }
    }

    private boolean checkForConflicts(Task newTask) {
        for (Task task : tasks) {
            if (overlaps(task.getStartTime(), task.getEndTime(), newTask.getStartTime(), newTask.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    private boolean overlaps(String start1, String end1, String start2, String end2) {
        return (start1.compareTo(end2) < 0 && start2.compareTo(end1) < 0);
    }
}

class TaskConflictObserver {
    public void notifyConflict(Task task) {
        System.out.println("Error: Task conflicts with an existing task.");
    }
}

public class AstronautScheduleOrganizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScheduleManager manager = ScheduleManager.getInstance();

        while (true) {
            System.out.println("\nAstronaut Schedule Organizer:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter start time (HH:mm): ");
                    String startTime = sc.nextLine();
                    System.out.print("Enter end time (HH:mm): ");
                    String endTime = sc.nextLine();
                    System.out.print("Enter priority (High, Medium, Low): ");
                    String priority = sc.nextLine();
                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    manager.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter task description to remove: ");
                    String taskToRemove = sc.nextLine();
                    manager.removeTask(taskToRemove);
                    break;
                case 3:
                    manager.viewTasks();
                    break;
                case 4:
                    System.out.print("Enter task description to edit: ");
                    String taskToEdit = sc.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDescription = sc.nextLine();
                    System.out.print("Enter new start time (HH:mm): ");
                    String newStartTime = sc.nextLine();
                    System.out.print("Enter new end time (HH:mm): ");
                    String newEndTime = sc.nextLine();
                    System.out.print("Enter new priority (High, Medium, Low): ");
                    String newPriority = sc.nextLine();
                    manager.editTask(taskToEdit, newDescription, newStartTime, newEndTime, newPriority);
                    break;
                case 5:
                    System.out.print("Enter task description to mark as completed: ");
                    String completedTask = sc.nextLine();
                    manager.markTaskCompleted(completedTask);
                    break;
                case 6:
                    System.out.print("Enter priority (High, Medium, Low): ");
                    String viewPriority = sc.nextLine();
                    manager.viewTasksByPriority(viewPriority);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

