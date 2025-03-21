import java.util.List;
import java.util.Optional;

interface IssueRepository {
    void saveIssue(Issue issue);
    Issue findIssueById(int id);
    Optional<Issue> listIssuesByAgent(Agent agent);
    List<Issue> findIssuesByCustomerEmail(String email);
    public List<Issue> getIssues();
}