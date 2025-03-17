import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskStore {
    private Map<String, Task> tasksMap;

    public TaskStore() {
        tasksMap = new HashMap<>();
    }

    void addTask(Task task) {
        tasksMap.put(task.getTaskId(), task);
    }

    void modifyTask(Task task) {
        tasksMap.put(task.getTaskId(), task);
    }

    void removeTask(String taskId) {
        tasksMap.remove(taskId);
    }

    Task getTask(String taskId) {
        return tasksMap.get(taskId);
    }

    List<Task> listTasks () {
        return new ArrayList<>(tasksMap.values());
    }
}
