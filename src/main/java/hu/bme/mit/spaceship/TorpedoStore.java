package hu.bme.mit.spaceship;

import java.util.Random;

public class TorpedoStore {

  private double FAILURE_RATE = 0.0; //NOSONAR

  private int torpedoCount = 0;

  private Random generator = new Random();

  public TorpedoStore(int numberOfTorpedos){
    this.torpedoCount = numberOfTorpedos;

    String failureEnv = System.getenv("IVT_RATE");
    if (failureEnv != null){
      try {
        FAILURE_RATE = Double.parseDouble(failureEnv);
      } catch (NumberFormatException nfe) {
        FAILURE_RATE = 0.0;
      }
    }
  }
//Neptun: IJVWMZ
  public boolean fire(int numberOfTorpedos){
    if(numberOfTorpedos < 1 || numberOfTorpedos > this.torpedoCount){
      throw new IllegalArgumentException("numberOfTorpedos");
    }

    boolean success = false;

    double r = this.generator.nextDouble();

    if (r >= FAILURE_RATE) {
      this.torpedoCount -= numberOfTorpedos;
      success = true;
    } else {
      success = false;
    }

    return success;
  }

  public boolean isEmpty(){
    return this.torpedoCount <= 0;
  }

  public int getTorpedoCount() {
    return this.torpedoCount;
  }
}