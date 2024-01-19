public class Main {
    public static void main(String[] args) {
        Elevator elevator = new Elevator(0);
        Requests requests = new Requests();
        ElevatorSystem elevatorSystem = new ElevatorSystem(elevator, requests);
        requests.addUpRequest(7);
        requests.addDownRequest(1);
        requests.addUpRequest(3);
        requests.addDownRequest(4);
        requests.addUpRequest(0);
        elevatorSystem.moveElevator();
    }
}