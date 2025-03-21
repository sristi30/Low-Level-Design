import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Issue {
    private final int id;
    private final String title;
    private final String description;
    private IssueType type;
    private String resolution;
    private IssueStatus status;
    private Agent assignedAgent;

    public Issue(int id, String title, String description, IssueType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = IssueStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public IssueType getType() {
        return type;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolution() { return resolution;}

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public String toString() {
        return "Issue [id=" + id + ", title=" + title + ", description=" + description + ", type=" + type + ", status=" + status + ", assignedAgent=" + assignedAgent + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}