/**
 * Created by Zhenyu Zhou on 11/17/2017.
 */
public class Rocket implements SpaceShip {

    int cost;
    int weight;
    int maxWeight;
    double launchExplosion;
    double landingCrash;
    int currentWeight;

    public boolean launch() {
        return true;
    }

    public boolean land() {
        return true;
    }

    public boolean canCarry(Item item) {
        return this.currentWeight + item.weight <= maxWeight;
    }

    public int carry(Item item) {
        this.currentWeight += item.weight;
        return this.currentWeight;
    }
}
