import java.util.Scanner;

public abstract class Room {

    protected String name = "room";
    protected Inventory loot = new Inventory();

    // Environmental conditions for room descriptions
    protected boolean onShip = true;
    protected boolean outside = false;
    protected boolean powerOut = true;

    // Different Rooms in Mind: Storage closet, Cafeteria, Med Bay, Quarters,
    // captain's quarters, living room
    public Room () {
        initialize(); //calls child class initialize
    }

    abstract void initialize();

    void tierDisplay() {
        loot.levelPrint();
    }

    void describe(boolean flashLight) {
        if (onShip) {
            System.out.print("We're on the ship.");
            if (powerOut && !flashLight) {
                System.out.println(" Power's out. It's pretty dark in here. May need a flashlight. \n");
            }
        }
    }

    String getName() {
        return this.name;
    }

    // Show all of the items currently available for looting in the room
    int display() {
        return loot.inventoryNumberPrint();
    }

    boolean lootRoom(Human h, Scanner reader) {
        System.out.println("Let's see what this room has to offer...\n");
        int numItems = 0;
        while (!this.loot.isEmpty()) {
            numItems = this.display();
            System.out.println("What do you want? One at a time. ");
            int choice = reader.nextInt();
            reader.nextLine();
            boolean validInput = false;
            if (choice < numItems + 2) {
                validInput = true;
            }
            while (!validInput) {
                System.out.println("Sorry, that's not one of the options. Please select from what's available.\n");
                this.display();
                choice = reader.nextInt();
                reader.nextLine();
                if ((choice < numItems + 2) && (choice >= 0)) {
                    validInput = true;
                }
            }
            if (choice == 0) {
                return true;
            } else if (choice == 1) {
                transfer(loot, h);
            } else {
                transfer(loot.retrieve(choice - 2), h);
            }
        }
        System.out.println("You've stripped the room of all its worth.\n");
        return true;
    }

    // The character loots everything in the room
    private void transfer (Inventory i, Human h) {
        int counter = 0;
        while (i.numItems() > 0) {
            Item item = i.retrieve(counter);
            int quantity = item.getQuantity();
            i.dropAll(item, true);
            // Item newItem = new Item(name, quantity); // to be used later upon optimization
            for (int index = 0; index < quantity; ++index) {
                boolean success = h.acquire(item);
                if (!success) {
                    i.acquire(item);
                    break;
                }
            }
        }
    }

    // Move an item from this room's inventory
    // to another character's inventory
    void transfer (Item i, Human h) {
        boolean hasItem = loot.drop(false, i, false);
        if (hasItem) {
            h.acquire(i);
        } else {
            System.out.println("That item isn't in this room.\n");
        }
    }
}

class Cafeteria extends Room {

    public Cafeteria() {
        //TODO: remember to set environmental conditions, if any
        this.name = "cafeteria";
    }

    @Override
    void initialize() {
        //TODO:
    }

}
class CaptainsQuarters extends Room {

    public CaptainsQuarters() {
        //TODO: remember to set environmental conditions, if any
        this.name = "captain's quarters";
    }

    @Override
    void initialize() {
        //TODO:
    }
}
class LivingQuarters extends Room {

    public LivingQuarters() {
        //TODO: remember to set environmental conditions, if any
        this.name = "living quarters";
    }

    @Override
    void initialize() {
        //TODO:
    }

}
class LivingRoom extends Room {

    public LivingRoom() {
        //TODO: remember to set environmental conditions, if any
        this.name = "living room";
    }

    @Override
    void initialize() {

    }
}
class MedBay extends Room {

    public MedBay() {
        //TODO: remember to set environmental conditions, if any
        this.name = "med bay";
    }

    @Override
    void initialize() {
        loot.acquire(loot.map, true);
        loot.acquire(loot.flashlight, true);
        for (int i = 0; i < 3; ++i) {
            loot.acquire(loot.Stimpak, true);
        }
    }

    @Override
    void describe(boolean flashLight) {
        super.describe(flashLight);
        if (powerOut && flashLight) {
            System.out.println(" Lots of equipment thrown about. Looks like the ship went through a rough patch.");
            System.out.println("The walls are a sterile white. Looks like a regular infirmary for the most part.\n");
        }
    }

}
