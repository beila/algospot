import java.util.*;

/**
 * The SpaceAssigner is responsible for assigning a space for an incoming
 * car to park in. This is done by calling the assignSpace() API.
 *
 * The SpaceAssigner responds to changes in space availability by
 * implementing the GarageStatusListener interface.
 */
public class SpaceAssigner implements GarageStatusListener
{

    // Space implementation is assumed to implement equals()/hashcode() contract
    private SortedSet<Space> spaces = new TreeSet<>((s1, s2) -> {
        // Note: this comparator imposes orderings that are inconsistent with equals.
        if (s1.equals(s2)) return 0;
        if (s1.isOccupied() && s2.isOccupied()) return 0;
        if (s1.isOccupied()) return -1;
        if (s2.isOccupied()) return 1;
        // neither one is occupied.
        return s1.getDesirability() - s2.getDesirability();
    });
    // Car implementation is assumed to implement equals()/hashcode() contract
    private Map<Car, Space> assignedSpaces = new HashMap<>();

  /**
   * Initiates the SpaceAssigner. This method is called only once per
   * app start-up.
   * @param garage The parking garage for which you are vending spaces.
   *
   * It would be safe to assume this method is called only once a day in the morning.
   *
   * Time complexity is O(n log n) since we add n elements and adding an element takes O(log n) time for TreeSet.
   * Memory complexity is O(n) since TreeSet is linked tree.
   */
  public void initialize(ParkingGarage garage)
  {
      garage.getSpaces().forEachRemaining(spaces::add);
  }

  /**
   * Assigns a space to an incoming car and returns that space.
   *
   * @param car The incoming car that needs a space.
   * @return The space reserved for the incoming car.
   *
   * TreeSet.last()/remove() takes O(log n) each.
   * HashMap.put() takes O(1) on average.
   * Hence, the time complexity is O(log n).
   * This method takes no additional memory without constant MapEntry overhead.
   *
   */
  public Space assignSpace(Car car)
  {
      try {
          Space space = spaces.last();
          spaces.remove(space);
          assignedSpaces.put(car, space);
          return space;
      } catch (NoSuchElementException e) {
          return null;
      }
  }

  /**
   * {@inheritDoc}
   *
   * TreeSet.remove()/add() takes O(log n) each.
   * HashMap.remove() takes O(1) on average.
   * Hence, the time complexity is O(log n).
   * This method takes no additional memory without constant TreeSet entry overhead.
   */
  public void onSpaceTaken(Car car, Space space)
  {
      // re-position this space in the TreeSet
      spaces.remove(space);
      spaces.add(space);
      spaces.add(assignedSpaces.remove(car));
  }

  /**
   * {@inheritDoc}
   *
   * TreeSet.remove()/add() takes O(log n) each.
   * Hence, the time complexity is O(log n).
   * This method takes no additional memory.
   */
  public void onSpaceFreed(Car car, Space space)
  {
      // re-position this space in the TreeSet
      spaces.remove(space);
      spaces.add(space);
  }

  /**
   * {@inheritDoc}
   *
   * We don't need this callback method.
   */
  public void onGarageExit(Car car)
  {
  }
}
