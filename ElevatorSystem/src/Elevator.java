public class Elevator {
    private Direction direction;
    private int floor;

    public Elevator(int floor) {
        this.direction = Direction.HALT;
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }


}
