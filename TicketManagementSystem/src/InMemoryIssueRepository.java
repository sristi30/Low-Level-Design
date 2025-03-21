import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

class InMemoryIssueRepository implements IssueRepository {
    private final ConcurrentMap<Integer, Issue> issueMap;

    public InMemoryIssueRepository() {
        this.issueMap = new ConcurrentHashMap<>();
    }

    public List<Issue> getIssues() {
        return new ArrayList<>(issueMap.values());
    }

    @Override
    public void saveIssue(Issue issue) {
        issueMap.put(issue.getId(), issue);
    }

    @Override
    public Issue findIssueById(int id) {
        return issueMap.get(id);
    }

    @Override
    public Optional<Issue> listIssuesByAgent(Agent agent) {
        for (Issue issue : issueMap.values()) {
            if (issue.getAssignedAgent() != null &&
                    issue.getAssignedAgent().getId() == agent.getId()) {
                return Optional.of(issue);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Issue> findIssuesByCustomerEmail(String email) {
        return List.of();
    }
}