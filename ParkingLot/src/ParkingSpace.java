import java.util.PriorityQueue;

public abstract class ParkingSpace {
    private final int lotSize;
    private final Slot[] slots;
    private final PriorityQueue<Slot> emptySlotsQueue;

    protected ParkingSpace(int lotSize) {
        emptySlotsQueue = new PriorityQueue<>(new SlotComparator());
        this.lotSize = lotSize;
        slots = new Slot[lotSize];
        initializeSlotsAndQueue();
    }

    private void initializeSlotsAndQueue() {
        for (int i = 0; i < lotSize; i++) {
            Slot slot = new Slot(i, null);
            slots[i] = slot;
            emptySlotsQueue.add(slot);
        }
    }

    public PriorityQueue<Slot> getEmptySlotsQueue() {
        return emptySlotsQueue;
    }

    public Slot[] getSlots() {
        return slots;
    }
}
