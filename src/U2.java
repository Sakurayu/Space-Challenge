/**
 * Created by Zhenyu Zhou on 11/17/2017.
 */
public class U2 extends Rocket {



    public U2 (){
        cost = 120;
        weight = 18000;
        maxWeight = 29000;
        launchExplosion = 0.0;
        landingCrash = 0.0;

        currentWeight = weight;
    }

    public boolean launch() {
        int random = (int)(Math.random() * 100 + 1);
        this.launchExplosion = 4.0 * (this.currentWeight - this.weight) / (this.maxWeight - this.weight);

//        System.out.println("rocket weight = " + this.currentWeight + "; maxWeight = " + this.maxWeight
//                + " chance = " + 4.0 * (this.currentWeight - this.weight) / (this.maxWeight - this.weight));
//        System.out.println("launchExplosion = " + this.launchExplosion + "; Random Number = " + random);

        return this.launchExplosion <= random;
    }

    public boolean land() {
        int random = (int)(Math.random() * 100 + 1);
        this.landingCrash = 8.0 * (this.currentWeight - this.weight) / (this.maxWeight - this.weight);

//        System.out.println("rocket weight = " + this.currentWeight + "; maxWeight = " + this.maxWeight
//                + " chance = " + 8.0 * (this.currentWeight - this.weight) / (this.maxWeight - this.weight));
//        System.out.println("landingCrash = " + this.landingCrash + "; Random Number = " + random);

        return this.landingCrash <= random;
    }
}
