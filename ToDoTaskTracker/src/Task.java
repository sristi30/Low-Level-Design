import java.time.Instant;
import java.util.List;

public class Task {

    private String taskId;
    private String taskName;
    private final TaskState state;
    private Instant activationTime;
    private Instant completionTime;
    private Instant deadLine;
    private List<Tag> tags;

    public Task(String taskId, String taskName, Instant activationTime, Instant deadLine, List<Tag> tags) {
       this(taskId, taskName, activationTime, deadLine, TaskState.ACTIVE, tags);
    }

    public Task(String taskId, String taskName, Instant activationTime, Instant deadLine, TaskState state, List<Tag> tags) {
       this(taskId, taskName, activationTime, null, deadLine, state, tags);
    }

    public Task(String taskId, String taskName, Instant activationTime, Instant completionTime, Instant deadLine, TaskState state, List<Tag> tags) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.activationTime = activationTime;
        this.completionTime = completionTime;
        this.deadLine = deadLine;
        this.state = state;
        this.tags = tags;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Instant getActivationTime() {
        return activationTime;
    }

    public Instant getCompletionTime() {
        return completionTime;
    }

    public void setActivationTime(Instant activationTime) {
        this.activationTime = activationTime;
    }

    public Instant getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Instant deadLine) {
        this.deadLine = deadLine;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public TaskState getState() {
        return state;
    }

    public String toString() {
        return "TaskImpl [taskId=" + taskId + ", taskName=" + taskName + "]";
    }
}
