import java.util.HashMap;
import java.util.List;

public interface AgentRepository {
    public List<Agent> getAgents();

    public Agent getAgent(int id);

    public void addAgent(Agent agent);

    public void removeAgent(Agent agent);

}
