/**
 * The SpaceAssigner is responsible for assigning a space for an incoming
 * car to park in. This is done by calling the assignSpace() API.
 *
 * The SpaceAssigner responds to changes in space availability by
 * implementing the GarageStatusListener interface.
 */
public class SpaceAssigner implements GarageStatusListener
{
  /**
   * Initiates the SpaceAssigner. This method is called only once per
   * app start-up.
   * @param garage The parking garage for which you are vending spaces.
   *
   * <<insert runtime and memory analysis here>>
   */
  public void initialize(ParkingGarage garage)
  {
    // insert code here
  }

  /**
   * Assigns a space to an incoming car and returns that space.
   *
   * @param car The incoming car that needs a space.
   * @returns The space reserved for the incoming car.
   *
   * <<insert runtime and memory analysis here>>
   */
  public Space assignSpace(Car car)
  {
    // insert code here
      return null;
  }

  /**
   * {@inheritDoc}
   *
   * <<insert runtime and memory analysis here>>
   */
  public void onSpaceTaken(Car car, Space space)
  {
    // insert code here
  }

  /**
   * {@inheritDoc}
   *
   * <<insert runtime and memory analysis here>>
   */
  public void onSpaceFreed(Car car, Space space)
  {
    // insert code here
  }

  /**
   * {@inheritDoc}
   *
   * <<insert runtime and memory analysis here>>
   */
  public void onGarageExit(Car car)
  {
    // insert code here
  }
}
