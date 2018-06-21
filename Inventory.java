//Inventory mechanic. Holds all ya items

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory{
  public static void main(String[] args){}
  private ArrayList<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>(5);
  private String[] labels = {"Reg Items: ", "Healing Items: ", "Shootable Weapons: ",
                             "Melee Weapons: ", "Ranged Weapons: "};
  /* |0| - [ items ]
     |1| - [ healing items ]
     |2| - [ shooting weapons ]
     |3| - [ melee weapons ]
     |4| - [ ranged weapons ] */

  public Item map = new Item("Map");
  public Item flashlight = new Item("Flashlight");
  public Item stimpak = new Item("Stimpak");
  public Shootable gun = new Shootable("Gun");
  public Shootable crossbow = new Shootable("Crossbow");
  public Melee fist = new Melee("Fist");
  private int maxWeight = 25;
  private int curWeight = 0;

  public Inventory() {
    for (int i = 0; i < 5; ++i) {
      inventory.add(new ArrayList<Item>());
    }
  }

  // The number of stimpaks in the inventory
  int numStimpaks() {
    int ns = 0;
    int counter = 0;
    while (counter < this.inventory.get(1).size()) {
      Item curItem = this.inventory.get(1).get(counter);
      if (curItem.getName().toLowerCase().equals("stimpak")) {
        ns = curItem.getQuantity();
        break;
      }
      ++counter;
    }
    return ns;
  }

  // Gets the current inventory utilization in percent form
  double getUsagePercentage() {
    return (curWeight / maxWeight) * 100;
  }

  // Equivalent to ArrayList get(). Used for looting rooms
  Item retrieve(int index) {
    ArrayList<Item> items = inventory.get(0);
    if (index > items.size()) {
      if (!items.isEmpty()) {
        return items.get(items.size() - 1);
      }
    }
    return items.get(index);
  }

  // Gets the current inventory utilization so the character
  // can see how the item in question compares.
  int getUsage() {
    return curWeight;
  }

  boolean isEmpty() {
    return numItems() == 0;
  }

  // Total including multiples of one item
  int numItems() {
    int index = 0;
    int numItems = 0;
    for (int counter = 0; counter < inventory.size(); ++counter) {
      ArrayList<Item> items = inventory.get(counter);
      index = 0;
      while (index < items.size()) {
        numItems += items.get(index).getQuantity();
        ++index;
      }
    }
    return numItems;
  }

  // Total number of individual items
  int individualItems() {
    int numItems = 0;
    for (int counter = 0; counter < inventory.size(); ++counter) {
      numItems += inventory.get(counter).size();
    }
    return numItems;
  }

  Item chooseItem(int category, Scanner reader, boolean drop) {
    ArrayList<Item> items = inventory.get(category);
    boolean validInput = false;
    int choice = -1;
    int counter = 1;
    while (!validInput) {
      System.out.println("0. Back.");
      for (Item i : items) {
        if (drop) {
          if (!i.getName().equals("Fist")) {
            System.out.println(counter + ". " + i.getName());
          }
        } else {
          System.out.println(counter + ". " + i.getName());
        }

      }
      System.out.print("Which item would you like");
      if (drop) {
        System.out.print(" to drop");
      }
      System.out.println("?\n");
      choice = reader.nextInt();
      reader.nextLine();
      if (drop && category == 3) {
        if (choice >= 0 && choice <= items.size() - 1) {
          validInput = true;
        }
      } else {
        if (choice >= 0 && choice <= items.size()) {
          validInput = true;
        }
      }

    }
    if (choice == 0) {
      return null;
    } else {
      return items.get(choice - 1);
    }
  }

  void displayShootables() {
    ArrayList<Item> shootables = inventory.get(2);
    for (Item i : shootables) {
      if (i instanceof Shootable) {
        Shootable s = (Shootable) i;
        int ammo = s.getAmmo() * s.getQuantity();
        System.out.print(s.getName() + " - " + ammo + " Ammo.");
      }
    }
    System.out.println();
  }

  void displayMelee() {
    ArrayList<Item> melee = inventory.get(3);
    for (Item i : melee) {
      if (i instanceof Melee) {
        Melee m = (Melee) i;
        System.out.print(m.getName() + " - ");
        if (m.getHealth() == Integer.MAX_VALUE) {
          System.out.print("Infinite Health.");
        } else {
          System.out.print(m.getHealth() + " Health. ");
        }
      }
    }
    System.out.println();
  }

  void displayRanged() {
    ArrayList<Item> ranged = inventory.get(4);
    for (Item i : ranged) {
      if (i instanceof Ranged) {
        Ranged s = (Ranged) i;
        System.out.print(s.getName() + " - " + s.getQuantity() + ". ");
      }
    }
    System.out.println();
  }

  // Quick inventory printout for party view
  String inventorySimplePrint() {
    if (numItems() == 0) {
      return "nothing";
    } else {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < inventory.size(); ++i) {
        ArrayList<Item> items = inventory.get(i);
        for (Item item : items) {
          sb.append(item.getQuantity() + " " + item.getName());
          if (item.getQuantity() > 1) {
            sb.append("s ");
          } else {
            sb.append(" ");
          }
        }
      }
      return sb.toString();
    }
  }

  // Used for looting rooms
  int inventoryNumberPrint() {
    int counter = 2;
    if (numItems() == 0) {
      System.out.println("nothing.\n");
    } else {
      System.out.println("0. Done.");
      System.out.println("1. ALL.");
      for (ArrayList<Item> list : inventory) {
        for (Item item : list) {
          System.out.println(counter + ". " + item.getName() + " : " + item.getQuantity());
          ++counter;
        }
      }
      System.out.println();
    }
    return counter - 2;
  }

  void listInventory() {
    for (int counter = 0; counter < inventory.size(); ++counter) {
      System.out.print(counter + ". " + labels[counter]);
      ArrayList<Item> items = inventory.get(counter);
      for (Item i : items) {
        System.out.print(i.getQuantity() + " " + i.getName());
        if (i.getQuantity() > 1) {
          System.out.print("s ");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  // More detailed inventory printout for combat
  void showInventory() {
    if (numItems() == 0 && numStimpaks() == 0) {
      System.out.println("You have nothing.\n");
    } else if (numItems() == 1 && numStimpaks() == 0) {
      System.out.print("You have ");
      String curItem = inventory.get(0).get(0).getName();
      if (isVowel(curItem.charAt(0))) { // Grammar stuff
        System.out.println("an " + curItem + ".\n");
      } else {
        System.out.println("a " + curItem + ".\n");
      }
    } else if (individualItems() == 1 && numStimpaks () > 0) { // Grammar stuffs
      System.out.print("You have " + numStimpaks() + " stimpaks.\n");
    } else {
      listInventory();
    }
  }

  boolean itemStack(ArrayList<Item> items, Item i) {
    int counter = 0;
    boolean itemFound = false;
    while (!itemFound && counter < items.size()) {
      if (items.get(counter).getName().equals(i.getName())) {
        items.get(counter).stack();
        itemFound = true;
        break;
      }
      ++counter;
    }
    if (!itemFound) {
      items.add(i);
    }
    return true;
  }

  boolean acquire(Item item, boolean room) {
    boolean itemFound = false;
    ArrayList<Item> items = inventory.get(0);
    int counter = 0;
    while (counter < items.size() && !itemFound) {
      if (items.get(counter).getName().equals(item.getName())) {
        items.get(counter).stack();
        itemFound = true;
        break;
      }
      ++counter;
    }
    if (!itemFound)
      items.add(item);
    return itemFound;
  }

  void levelPrint() {
    for (ArrayList<Item> list : inventory) {
      for (Item i : list) {
        System.out.print(i.getName() + " ");
      }
      System.out.println();
    }
  }

  // Add an item to the inventory
  boolean acquire(Item item) {
    boolean itemFound = false;
    if (maxWeight < curWeight + item.getWeight()) { // Over capacity
      return false;
    } else if (item instanceof Weapon) {
      if (item instanceof Shootable) {
        ArrayList<Item> shootables = inventory.get(2);
        itemFound = itemStack(shootables, item);
      } else if (item instanceof Melee) {
        ArrayList<Item> melee = inventory.get(3);
        itemFound = itemStack(melee, item);
      } else { // Ranged
        ArrayList<Item> ranged = inventory.get(4);
        itemFound = itemStack(ranged, item);
      }
    } else if (item.isRegenerative()) {
      ArrayList<Item> healing = inventory.get(1);
      itemFound = itemStack(healing, item);
    } else { // Regular Item
      ArrayList<Item> items = inventory.get(0);
      itemFound = itemStack(items, item);
    }
    curWeight += item.getWeight();
    return itemFound;
  }

  boolean itemRemove(ArrayList<Item> items, Item i, boolean all) {
    boolean itemFound = false;
    int counter = 0;
    while (!itemFound && counter < items.size()) {
      if (items.get(counter).getName().equals(i.getName())) {
        Item curItem = items.get(counter);
        if (all) {
          int quantity = curItem.getQuantity();
          int totalWeight = quantity * curItem.getWeight();
          items.remove(curItem);
          this.curWeight -= totalWeight;
        } else {
          curWeight -= curItem.getWeight();
          curItem.drop();                         // Decrease quantity of item in question
          int quantity = curItem.getQuantity();
          if (quantity < 1) {
            items.remove(curItem);            // Remove AFTER drop so the quantity count
          }                                       // is accurate.
        }
        itemFound = true;
        break;
      }
      ++counter;
    }
    return itemFound;
  }

  // Used for looting rooms. Or death if I implemented it.
  boolean dropAll(Item item, boolean room) {
    boolean itemFound = drop(true, item, true);
    return itemFound;
  }

  boolean drop(boolean room, Item item, boolean all) {
    boolean itemFound = false;
    ArrayList<Item> items = inventory.get(0);
    if (!all) {
      itemFound = itemRemove(items, item, false);
    } else {
      itemFound = itemRemove(items, item, true);
    }
    return itemFound;
  }

  // Remove an item from the inventory
  boolean drop(Item item, boolean all) {
    boolean itemFound = false;
    if (item instanceof Weapon) {
      if (item instanceof Shootable) {
        ArrayList<Item> shootables = inventory.get(2);
        itemFound = itemRemove(shootables, item, all);
      } else if (item instanceof Melee) {
        ArrayList<Item> melee = inventory.get(3);
        itemFound = itemRemove(melee, item, all);
      } else { // Ranged
        ArrayList<Item> ranged = inventory.get(4);
        itemFound = itemRemove(ranged, item, all);
      }
    } else if (item.isRegenerative()) {
      ArrayList<Item> healing = inventory.get(1);
      itemFound = itemRemove(healing, item, all);
    } else { // Regular Item
      ArrayList<Item> items = inventory.get(0);
      itemFound = itemRemove(items, item, all);
    }
    return itemFound;
  }

  // You may choose to upgrade your inventory. Pretty sweet imo.
  void upgrade() {
    this.maxWeight += 25;
  }

  // Used for grammar stuff.
  boolean isVowel (char c) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    for (char v : vowels) {
      if (c == v) {
          return true;
      }
    }
    return false;
  }
}
