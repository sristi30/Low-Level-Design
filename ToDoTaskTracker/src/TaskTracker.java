import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TODO: check all method parameters

public class TaskTracker {
    private final TaskStore taskStore;
    private ActivityLogger activityLogger;

    public TaskTracker(TaskStore taskStore, ActivityLogger activityLogger) {
        this.taskStore = taskStore;
        this.activityLogger = activityLogger;
    }

    void addTask(Task task) {
        taskStore.addTask(task);
        activityLogger.addActivity(new Activity(Action.ADD, task.getTaskId(), Instant.now()));
    }

    void modifyTask(Task task) {
        taskStore.modifyTask(task);
        activityLogger.addActivity(new Activity(Action.UPDATE, task.getTaskId(), Instant.now()));
    }

    void removeTask(String taskId) {
        taskStore.removeTask(taskId);
        activityLogger.addActivity(new Activity(Action.DELETE, taskId, Instant.now()));
    }

    Task getTask(String taskId) {
        return taskStore.getTask(taskId);
    }

    List<Task> listTasks (Predicate<Task> filter, Comparator<Task> sortCriteria) {
        return taskStore.listTasks().stream()
                .filter(task -> task.getState() != TaskState.COMPLETED)
                .filter(filter != null ? filter : task -> true)
                .sorted(sortCriteria != null ? sortCriteria : Comparator.comparing(Task::getTaskId))
                .collect(Collectors.toList());
    }

    void markComplete(String taskId) {
        Task task = taskStore.getTask(taskId);
        Task modifiedTask = new Task(task.getTaskId(), task.getTaskName(), task.getActivationTime(), Instant.now(), task.getDeadLine(), TaskState.COMPLETED, task.getTags());
        taskStore.modifyTask(modifiedTask);
    }

    void getStatistics(Optional<Instant> startTime, Optional<Instant> endTime) {
        List<Task> tasks = taskStore.listTasks();
        long numCreatedTasks = tasks.stream()
              //  .peek(task -> System.out.println("peek 1st taskId: " + task.getTaskId() + " activationTime: " + task.getActivationTime()))
                .filter(task -> isWithinTimeFrame(startTime, endTime, task.getActivationTime()))
             //   .peek(task -> System.out.println("peek 2nd taskId: " + task.getTaskId() + " activationTime: " + task.getActivationTime()))
                .count();
        long numCompletedTasks = tasks.stream().filter(task -> task.getState() == TaskState.COMPLETED)
                .filter(task -> isWithinTimeFrame(startTime, endTime, task.getCompletionTime()))
                .count();
        long numSpilledTasks = tasks.stream().filter(task -> isWithinTimeFrame(Optional.empty(), endTime, task.getDeadLine()))
                .filter(task -> Objects.equals(task.getCompletionTime(), null) || endTime.map(end -> task.getCompletionTime().isAfter(end)).orElse(true))
                .count();
        System.out.println("Num created tasks: " + numCreatedTasks);
        System.out.println("Num completed tasks: " + numCompletedTasks);
        System.out.println("Num spilled tasks: " + numSpilledTasks);
    }

    private boolean isWithinTimeFrame(Optional<Instant> startTime, Optional<Instant> endTime, Instant taskInstant) {
        return Objects.equals(taskInstant, null) ? false : startTime.map(start -> taskInstant.isAfter(start) || taskInstant.equals(start)).orElse(true) &&
                endTime.map(end -> taskInstant.isBefore(end) || taskInstant.equals(end)).orElse(true);
    }



    List<Activity> getActivityLog() {
        return activityLogger.getActivities();
    }
}
