import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingGarageMock implements ParkingGarage {

    private final List<Space> spaces;
    private GarageStatusListener listener;

    public ParkingGarageMock(GarageStatusListener listener, int[] desirabilities) {
        register(listener);

        spaces = new ArrayList<>();
        for(int i = 0; i < desirabilities.length; ++i) {
            spaces.add(new SpaceMock(i, desirabilities[i]));
        }
    }

    @Override
    public void register(GarageStatusListener assigner) {
        listener = assigner;
    }

    public void takeSpace(CarMock car, int i) {
        ((SpaceMock)spaces.get(i)).setOccupied(true);
        listener.onSpaceTaken(car, spaces.get(i));
    }

    @Override
    public Iterator<Space> getSpaces() {
        return spaces.iterator();
    }
}
