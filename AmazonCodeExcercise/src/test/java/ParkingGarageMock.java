import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingGarageMock implements ParkingGarage {

    private final List<Space> spaces;
    private GarageStatusListener garage;

    public ParkingGarageMock(int[] desirabilities) {
        spaces = new ArrayList<>();
        for(int i = 0; i < desirabilities.length; ++i) {
            final int finalI = i;
            spaces.add(new Space() {
                @Override
                public int getID() {
                    return finalI;
                }

                @Override
                public int getDesirability() {
                    return desirabilities[finalI];
                }

                @Override
                public boolean isOccupied() {
                    return false;
                }

                @Override
                public Car getOccupyingCar() {
                    return null;
                }
            });
        }
    }

    @Override
    public void register(GarageStatusListener assigner) {
        garage = assigner;
    }

    public void exitGarage() {
        garage.onGarageExit(new CarMock());
    }

    public void freeSpace(int i) {
        garage.onSpaceFreed(new CarMock(), spaces.get(i));
    }

    public void takeSpace(int i) {
        garage.onSpaceTaken(new CarMock(), spaces.get(i));
    }

    @Override
    public Iterator<Space> getSpaces() {
        return spaces.iterator();
    }
}
