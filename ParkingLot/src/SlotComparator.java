import java.util.Comparator;

public class SlotComparator implements Comparator<Slot> {

    @Override
    public int compare(Slot slot1, Slot slot2) {
        if (slot1.getSlotNo() < slot2.getSlotNo()) {
            return -1;
        } else if (slot1.getSlotNo() > slot2.getSlotNo()) {
            return 1;
        }
        return 0;
    }
}
