import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class IssueService {
    private final IssueRepository issueRepo;
    private final AgentRepository agentRepo;
    private final IssueQueue issueQueue;

    public IssueService(IssueRepository repo, AgentRepository aRepo, IssueQueue issueQueue) {
        this.issueRepo = repo;
        this.agentRepo = aRepo;
        this.issueQueue = issueQueue;
    }

    public Issue createIssue(int id, String title, String description, IssueType type) {
        Issue newIssue = new Issue(id, title, description, type);
        issueRepo.saveIssue(newIssue);
        return newIssue;
    }

    public List<Issue> getIssues(Predicate<Issue> predicate) {
        List<Issue> issues = issueRepo.getIssues();
        return issues.stream().filter(predicate).collect(Collectors.toList());
    }

    public void addAgent(String agentEmail, String agentName, List<IssueType> expertiseList) {
        Agent agent = new AgentImpl(agentEmail, agentName, expertiseList);
        agentRepo.addAgent(agent);
    }

    public boolean assignIssue(int issueId) {
        Issue issue = issueRepo.findIssueById(issueId);
        if (issue == null) {
            throw new IllegalArgumentException("Issue not found.");
        }
        if (issue.getAssignedAgent() != null) {
            throw new IllegalArgumentException("The issue already has an agent assigned");
        }
        List<Agent> agents = agentRepo.getAgents();
        Instant oldestResolutionTimeStamp = Instant.now();
        Agent agentToBeAssigned = null;
        for (Agent agent : agents) {
            if (Objects.equals(agent.getAssignedIssue(), null) && agent.getExpertise().contains(issue.getType())) {
                if (agent.getLastResolutionTimeStamp().isBefore(oldestResolutionTimeStamp)) {
                    agentToBeAssigned = agent;
                    oldestResolutionTimeStamp = agent.getLastResolutionTimeStamp();
                }
            }
        }
        if (agentToBeAssigned == null) {
            issue.setStatus(IssueStatus.WAITING);
            issueQueue.enqueue(issue);
            return false;
        }
        agentToBeAssigned.assignIssue(issue);
        return true;
    }

    public boolean assignIssue() {
        Issue issue = issueQueue.dequeue();
        if (issue == null) {
            System.out.println("There is no issue waiting to be assigned");
            return false;
        }
        return assignIssue(issue.getId());
    }


    public void updateIssue(int issueId, IssueStatus status, String resolution) {
        if (status == IssueStatus.RESOLVED) {
            resolveIssue(issueId, resolution);
            return;
        }
        Issue issue = issueRepo.findIssueById(issueId);
        issue.setStatus(status);
    }

    public void resolveIssue(int issueId, String resolution) {
        Issue issue = issueRepo.findIssueById(issueId);
        if (issue.getAssignedAgent() == null) {
            throw new IllegalArgumentException("Issue is not yet assigned, hence it can't be marked as resolved.");
        }
        issue.setStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);
        Agent assignedAgent = issue.getAssignedAgent();
        issue.setAssignedAgent(null);
        assignedAgent.resolveIssue();
    }

    public void viewAgentWorkHistory() {
        List<Agent> agents = agentRepo.getAgents();
        for (Agent agent : agents) {
            System.out.println("Work history for agent: " + agent.getId());
            agent.getResolvedIssues().forEach(System.out::println);
        }
    }
}
