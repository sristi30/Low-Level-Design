import java.util.ArrayList;
import java.util.List;

public class ActivityLogger {
    private final List<Activity> activities;

    public ActivityLogger() {
        activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
