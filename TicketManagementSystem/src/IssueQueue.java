import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IssueQueue {
    private Queue<Issue> issueQueue;

    public IssueQueue() {
        this.issueQueue = new ConcurrentLinkedQueue<>();
    }

    public boolean enqueue(Issue issue) {
        return issueQueue.offer(issue);
    }

    public Issue dequeue() {
        return issueQueue.poll();
    }
}
