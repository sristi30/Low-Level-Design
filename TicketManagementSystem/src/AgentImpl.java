import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.hash;

class AgentImpl implements Agent {
    private final int id;
    private final String email;
    private final String name;
    private final List<IssueType> expertise;
    private Issue assignedIssue;
    private final List<Issue> resolvedIssues;
    private Instant lastResolutionTimeStamp;
    private static final AtomicInteger  agentId = new AtomicInteger(0);

    public synchronized Issue getAssignedIssue() {
        return assignedIssue;
    }

    public AgentImpl(String email, String name, List<IssueType> expertise) {
        this.id = agentId.incrementAndGet();
        this.email = email;
        this.name = name;
        this.expertise = expertise;
        this.lastResolutionTimeStamp = Instant.now();
        this.resolvedIssues = new CopyOnWriteArrayList<>();
    }

    public List<IssueType> getExpertise() {
        return expertise;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Issue> getResolvedIssues() {
        return resolvedIssues;
    }

    public synchronized Instant getLastResolutionTimeStamp() {
        return lastResolutionTimeStamp;
    }

    public synchronized boolean assignIssue(Issue issue) {
        //Can add checks here
        if (assignedIssue != null) {
            return false;
        }
        issue.setAssignedAgent(this);
        issue.setStatus(IssueStatus. IN_PROGRESS);
        assignedIssue = issue;
        return true;
    }

    public synchronized void resolveIssue() {
        if (assignedIssue != null) {
            throw new IllegalArgumentException("Assigned issue is null");
        }
        resolvedIssues.add(assignedIssue);
        lastResolutionTimeStamp = Instant.now();
        assignedIssue = null;
    }

    public String toString() {
        return "Agent #" + id + ": " + name + ": " + expertise;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AgentImpl other = (AgentImpl) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
