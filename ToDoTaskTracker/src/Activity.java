import java.time.Instant;

public class Activity {
    private Action action;
    private String taskId;
    private Instant activityTime;

    public Activity(Action action, String taskId, Instant activityTime) {
        this.action = action;
        this.taskId = taskId;
        this.activityTime = activityTime;
    }

    public Instant getActivityTime() {
        return activityTime;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String toString() {
        return "Activity: action=" + action + ", taskId=" + taskId + ", activityTime=" + activityTime;
    }
}
