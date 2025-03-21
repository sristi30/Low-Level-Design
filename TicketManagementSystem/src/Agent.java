import java.time.Instant;
import java.util.List;

public interface Agent {

    public int getId();

    public String getName();

    public List<Issue> getResolvedIssues();

    public boolean assignIssue(Issue issue);

    public List<IssueType> getExpertise();

    public Issue getAssignedIssue();

    public void resolveIssue();

    public Instant getLastResolutionTimeStamp();
}
