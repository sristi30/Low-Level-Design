import java.util.Objects;

public class ElevatorSystem {
    private static final int MAX_FLOOR = 10;
    private static final int MIN_FLOOR = 0;
    private Elevator elevator;
    private Requests requests;

    public ElevatorSystem(Elevator elevator, Requests requests) {
        this.elevator = elevator;
        this.requests = requests;
    }


    public void moveElevator() {
        switch (elevator.getDirection()) {
            case HALT:
            case UP:
                processUpRequests();
                processDownRequests();
                elevator.setDirection(Direction.HALT);
                System.out.println("The elevator is halted.");
                break;
            case DOWN:
                processDownRequests();
                processUpRequests();
                elevator.setDirection(Direction.HALT);
                System.out.println("The elevator is halted.");
                break;
        }
    }

    private void processDownRequests() {
        Integer highestFloorForDownRequest = requests.getHighestFloorForDownRequest(elevator.getFloor());
        if (!Objects.equals(highestFloorForDownRequest, null)) {
            elevator.setDirection(Direction.DOWN);
        }
        while (!Objects.equals(highestFloorForDownRequest, null)) {
            moveDown(highestFloorForDownRequest);
            requests.removeDownRequests(highestFloorForDownRequest);
            highestFloorForDownRequest = requests.getHighestFloorForDownRequest(elevator.getFloor());
        }
    }

    private void processUpRequests() {
        Integer lowestFloorForUpRequest = requests.getLowestFloorForUpRequest(elevator.getFloor());
        if (!Objects.equals(lowestFloorForUpRequest, null)) {
            elevator.setDirection(Direction.UP);
        }
        while (!Objects.equals(lowestFloorForUpRequest, null)) {
            moveUp(lowestFloorForUpRequest);
            requests.removeUpRequest(lowestFloorForUpRequest);
            lowestFloorForUpRequest = requests.getLowestFloorForUpRequest(elevator.getFloor());
        }
    }

    private void moveUp(int floor){
        for (int i = elevator.getFloor() + 1; i<=floor; i++) {
            elevator.setFloor(i);
            System.out.println("Moved up to floor " + i + ". Elevator Direction: " + elevator.getDirection());
        }
        openDoor();
    }

    private void moveDown(int floor){
        for (int i = elevator.getFloor()-1; i>=floor; i--) {
            elevator.setFloor(i);
            System.out.println ("Moved down to floor " + i + ". Elevator Direction: " + elevator.getDirection());
        }
        openDoor();
    }

    private void openDoor() {
        System.out.println("The door is opened");
    }
}
