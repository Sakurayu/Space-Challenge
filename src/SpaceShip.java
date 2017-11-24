/**
 * This  interface includes the definitions of these methods:
 * Created by Zhenyu Zhou on 11/16/2017.
 */
public interface SpaceShip {
    boolean launch(); //returns if the launch was successful or if the rocket has crashed

    boolean land(); //returns either true or false based on the success of the landing

    boolean canCarry(Item item); // returns whether rocket can carry such item or will exceed the weight limit

    int carry(Item item); //updates the current weight of the rocket

}
