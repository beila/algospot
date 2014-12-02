import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceAssignerTest {
    private SpaceAssigner assigner;

    @Before
    public void setUp() throws Exception {
        assigner = new SpaceAssigner();
    }

    @Test
    public void testNoSpace() {
        assigner.initialize(new ParkingGarageMock(assigner, new int[]{}));
        Assert.assertNull(assigner.assignSpace(new CarMock()));
    }

    @Test
    public void testOneAvailableSpace() throws Exception {
        assigner.initialize(new ParkingGarageMock(assigner, new int[]{1}));
        Assert.assertEquals(0, assigner.assignSpace(new CarMock()).getID());
    }

    @Test
    public void testTwoAvailableSpace() throws Exception {
        ParkingGarageMock garage = new ParkingGarageMock(assigner, new int[]{1, 2});
        assigner.initialize(garage);
        CarMock firstCar = new CarMock();
        Assert.assertEquals(1, assigner.assignSpace(firstCar).getID());
        garage.takeSpace(firstCar, 1);
        Assert.assertEquals(0, assigner.assignSpace(new CarMock()).getID());
    }

    @Test
    public void testBadCar() throws Exception {
        ParkingGarageMock garage = new ParkingGarageMock(assigner, new int[]{1, 2});
        assigner.initialize(garage);
        CarMock firstCar = new CarMock();
        Assert.assertEquals(1, assigner.assignSpace(firstCar).getID());
        garage.takeSpace(firstCar, 0);
        Assert.assertEquals(1, assigner.assignSpace(new CarMock()).getID());
    }

    @Test
    public void testTwoCars() throws Exception {
        ParkingGarageMock garage = new ParkingGarageMock(assigner, new int[]{1, 2});
        assigner.initialize(garage);
        Assert.assertEquals(1, assigner.assignSpace(new CarMock()).getID());
        Assert.assertEquals(0, assigner.assignSpace(new CarMock()).getID());
    }
}
