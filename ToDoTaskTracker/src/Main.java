/**todo lists
 * task (deadline, tags)
 * add, modify, remove tasks
 * remove completed tasks
 * tasks can be made to appear at a future date
 * activity log
 * stats of how many tasks were added completed and spilled over the deadline during
 * a particular period of time
 * **/

import java.time.Instant;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskStore taskStore = new TaskStore();
        ActivityLogger activityLogger = new ActivityLogger();
        TaskTracker taskTracker = new TaskTracker(taskStore, activityLogger);
        taskTracker.addTask(new Task("TaskId1", "Task1", Instant.now(), Instant.now(), null));
        taskTracker.addTask(new Task("TaskId2", "Task2", Instant.now(), Instant.MAX, null));
        System.out.println(taskTracker.getTask("TaskId2"));
        taskTracker.modifyTask(new Task("TaskId1", "Task1.1", Instant.now(), Instant.MAX, null));
        System.out.println(taskTracker.listTasks(null, null));
        taskTracker.removeTask("TaskId2");
        System.out.println(taskTracker.listTasks(null, null));
        taskTracker.addTask(new Task("TaskId2", "Task2", Instant.now(), Instant.MAX, null));
        Thread.sleep(100);

        Instant time1 = Instant.now();
        taskTracker.markComplete("TaskId1");
        taskTracker.addTask(new Task("TaskId3", "Task3", Instant.now(), Instant.now(), null));
        System.out.println(taskTracker.listTasks(      task -> !Objects.equals(task.getTaskId(), "TaskId1"),
                Comparator.comparing(Task::getDeadLine)));

        Instant time2 = Instant.now();
        System.out.println("Time1: " + time1 + " Time2: " + time2);
        taskTracker.getStatistics(Optional.of(time1), Optional.of(time2));
        System.out.println(taskTracker.getActivityLog());//TODO: add time period
    }
}