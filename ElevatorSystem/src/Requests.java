import java.util.TreeSet;

public class Requests {
    private TreeSet<Integer> upRequests = new TreeSet<>();
    private TreeSet<Integer> downRequests = new TreeSet<>();






    public void addUpRequest(Integer floor) {
        upRequests.add(floor);
    }

    public void addDownRequest(Integer floor) {
        downRequests.add(floor);
    }


    public Integer getLowestFloorForUpRequest(int floor) {
        return upRequests.ceiling(floor);
    }

    public Integer getHighestFloorForDownRequest(int floor) {
        return downRequests.floor(floor);
    }

    public void removeUpRequest(int floor) {
        upRequests.remove(floor);
    }

    public void removeDownRequests(int floor) {
        downRequests.remove(floor);
    }
}
