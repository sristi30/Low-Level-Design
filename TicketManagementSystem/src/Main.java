import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IssueRepository issueRepository = new InMemoryIssueRepository();
        AgentRepository agentRepository = new InMemoryAgentRepository();
        IssueQueue issueQueue = new IssueQueue();
        IssueService issueService = new IssueService(issueRepository, agentRepository, issueQueue);

        System.out.println(issueService.createIssue(123, "Network lag", "There is a network lag", IssueType.PAYMENT));
        issueService.getIssues(issue -> true).forEach(System.out::println);
        List<IssueType> expertiseList = new ArrayList<>();
        expertiseList.add(IssueType.PAYMENT);
        expertiseList.add(IssueType.GOLD);
        issueService.addAgent("abc@gmail.com", "Abc", expertiseList);
        System.out.println(agentRepository.getAgents());
        issueService.assignIssue(123);
        issueService.getIssues(issue -> true).forEach(System.out::println);
        System.out.println(issueService.createIssue(456, "Availability", "There is a downtime", IssueType.GOLD));
        issueService.getIssues(issue -> issue.getType().equals(IssueType.GOLD)).forEach(System.out::println);
        issueService.updateIssue(123, IssueStatus.WAITING, "Code fix");
        issueService.getIssues(issue -> true).forEach(System.out::println);
        //issueService.resolveIssue(123, "Code fix");
        issueService.getIssues(issue -> true).forEach(System.out::println);
        issueService.viewAgentWorkHistory();
        System.out.println(issueService.assignIssue(456));
        System.out.println(issueService.assignIssue(123));
        issueService.resolveIssue(123, "Code fix");
        System.out.println(issueService.assignIssue());
        issueService.getIssues(issue -> true).forEach(System.out::println);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {issueService.assignIssue(123);});
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}