public class SpaceMock implements Space {
    private int id;
    private int desirability;
    private boolean occupied = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpaceMock spaceMock = (SpaceMock) o;

        if (id != spaceMock.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public SpaceMock(int id, int desirability) {
        this.id = id;
        this.desirability = desirability;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getDesirability() {
        return desirability;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public Car getOccupyingCar() {
        return null;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
