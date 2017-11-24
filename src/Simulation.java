import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by Zhenyu Zhou on 11/17/2017.
 */
public class Simulation {

    public Simulation() {

    }

    //This method loads all items from a text file and returns an ArrayList of Items
    ArrayList<Item> loadItems(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        ArrayList<Item> items = new ArrayList<>();

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] oneItem = line.split("=");
            items.add(new Item(oneItem[0], Integer.valueOf(oneItem[1])));
        }

        // To print out phase.text
        System.out.println(fileName + " contains " + items.size() + " items");
//        for (Item i : items) {
//            System.out.println(i.name + ": " + i.weight);
//        }
//        System.out.println();

        return items;
    }


    /* This method takes the ArrayList of Items returned from loadItems and starts creating U1 rockets.
     * It first tries to fill up 1 rocket with as many items as possible before creating a new rocket object
     * and filling that one until all items are loaded.
     * The method then returns the ArrayList of those U1 rockets that are fully loaded.
     */
    ArrayList<Rocket> loadU1(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U1();

        int itemCounter = 1;
        int rocketCounter = 1;

        System.out.println("\nU1 Rocket weight = " + r.weight + "; maxWeight = " + r.maxWeight);

        for (Item i : list) {

            while (!r.canCarry(i)) {
//                System.out.println(" Adding next Item " + itemCounter + ": " + i.name + " = " + i.weight
//                        + " failed -> No." + rocketCounter + " U1 full");

                rocketCounter++;
                fleet.add(r);
                r = new U1();
            }
            r.carry(i);

//            System.out.println("Item " + itemCounter + ": " + i.name + " = " + i.weight + "; Rocket weight = "
//                    + r.currentWeight + "; Item added");

            itemCounter++;
        }
        fleet.add(r);
        System.out.println("U1 fleet contains " + fleet.size() + " rockets");
        return fleet;
    }


    /* This method takes the ArrayList of Items and starts creating U2 rockets and filling them with those items
     * until all items are loaded. The method then returns the ArrayList of those U2 rockets that are fully loaded.
     */
    ArrayList<Rocket> loadU2(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U2();

        int itemCounter = 1;
        int rocketCounter = 1;

        System.out.println("U2 Rocket weight = " + r.weight + "; maxWeight = " + r.maxWeight);

        for (Item i : list) {

            while (!r.canCarry(i)) {
//                System.out.println(" Adding next Item " + itemCounter + ": " + i.name + " = " + i.weight
//                        + " failed -> No." + rocketCounter + " U2 full");
                rocketCounter++;
                fleet.add(r);
                r = new U2();
            }
            r.carry(i);
//            System.out.println("Item " + itemCounter + ": " + i.name + " = " + i.weight + "; Rocket weight = "
//                    + r.currentWeight + "; Item added");
            itemCounter++;
        }
        fleet.add(r);
        System.out.println("U2 fleet contains " + fleet.size() + " rockets\n");
        return fleet;
    }


    /* This method takes an ArrayList of Rockets and calls launch and land methods for each of the rockets in the
     * ArrayList. Every time a rocket explodes or crashes (i.e if launch or land return false), it will have to send
     * that rocket again. All while keeping track of the total budget required to send each rocket safely to Mars.
     * runSimulation then returns the total budget required to send all rockets (including the crashed ones).
     */
    int runSimulation(ArrayList<Rocket> list) {
        int num = 0; //failed trials of launch/land
        int indexSuccess = 1;
        for (Rocket r : list) {

            while (!r.launch()) {
                r.launch();
                num++;
                //System.out.println("Extra trials: " + num);
            }
            //System.out.println("No." + indexSuccess + " rocket launched");

            while (!r.land()) {
                r.land();
                num++;
                //System.out.println("Extra trials: " + num);
            }
            //System.out.println("No." + indexSuccess + " rocket landed\n");
            indexSuccess++;
        }
        int budget = list.get(0).cost * (list.size() + num);
        System.out.println(list.size() + " rockets and " + num + " extra trials = "
                + (list.size() + num) + " in total");
        return budget;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // Create a Simulation object
        Simulation sim = new Simulation();

        // Load Items for Phase-1 and Phase-2
        ArrayList<Item> phase1 = sim.loadItems("phase-1.txt");
        ArrayList<Item> phase2 = sim.loadItems("phase-2.txt");


        // Load a fleet of U1 rockets for Phase-1 and then for Phase-2
        ArrayList<Rocket> u1FleetPhase1 = sim.loadU1(phase1);
        ArrayList<Rocket> u1FleetPhase2 = sim.loadU1(phase2);


        // Run the simulation using the fleet of U1 rockets and display the total budget required
        System.out.println("\nU1 rocket cost = 100");
        int budgetU1phase1 = sim.runSimulation(u1FleetPhase1);
        System.out.println("Budget of U1 fleet for phase 1 = " + budgetU1phase1 + " (millions)");

        int budgetU1phase2 = sim.runSimulation(u1FleetPhase2);
        System.out.println("Budget of U1 fleet for phase 2 = " + budgetU1phase2 + " (millions)");

        System.out.println("Total budget of U1 fleet = " + (budgetU1phase1 + budgetU1phase2) + " (millions)\n");


        // Repeat the same for U2 rockets and display the total budget for that
        ArrayList<Rocket> u2FleetPhase1 = sim.loadU2(phase1);
        ArrayList<Rocket> u2FleetPhase2 = sim.loadU2(phase2);
        System.out.println("\nU2 rocket cost = 120");
        int budgetU2phase1 = sim.runSimulation(u2FleetPhase1);
        System.out.println("Budget of U2 fleet for phase 1 = " + budgetU2phase1 + " (millions)");

        int budgetU2phase2 = sim.runSimulation(u2FleetPhase2);
        System.out.println("Budget of U2 fleet for phase 2 = " + budgetU2phase2 + " (millions)");

        System.out.println("Total budget of U1 fleet = " + (budgetU2phase1 + budgetU2phase2) + " (millions)\n");

    }
}
