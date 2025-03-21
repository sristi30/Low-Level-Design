import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryAgentRepository implements AgentRepository {

    private final ConcurrentMap<Integer, Agent> agents;

    public InMemoryAgentRepository() {
        agents = new ConcurrentHashMap<>();
    }

    public List<Agent> getAgents() {
        return new ArrayList<>(agents.values());
    }

    public Agent getAgent(int id) {
        return agents.get(id);
    }
    public void addAgent(Agent agent) {
        agents.put(agent.getId(), agent);
    }
    public void removeAgent(Agent agent) {
        agents.remove(agent.getId());
    }
}
