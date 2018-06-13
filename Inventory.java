//Inventory mechanic. Holds all ya items

import java.util.ArrayList;

public class Inventory{
  public static void main(String[] args){}

  private ArrayList<Item> inventory = new ArrayList<Item>(); // Holds items.
  private int maxWeight = 25;
  private int curWeight = 0;

  public Inventory() {}

  // The number of stimpaks in the inventory
  int numStimpaks() {
    int ns = 0;
    int counter = 0;
    while (counter < inventory.size()) {
      Item curItem = inventory.get(counter);
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

  Item retrieve(int index) {
    if (index > inventory.size()) {
      if (!inventory.isEmpty()) {
        return inventory.get(inventory.size() - 1);
      }
    }
    return inventory.get(index);
  }

  // Gets the current inventory utilization so the character
  // can see how the item in question compares.
  int getUsage() {
    return curWeight;
  }

  boolean isEmpty() {
    return numItems() == 0;
  }

  int numItems() {
    int counter = 0;
    int items = 0;
    while (counter < inventory.size()) {
      items += inventory.get(counter).getQuantity();
      ++counter;
    }
    return items;
  }

  // Quick inventory printout for party view
  String inventorySimplePrint() {
    if (inventory.size() == 0) {
      return "nothing";
    } else {
      StringBuilder sb = new StringBuilder();
      for (Item item : inventory) {
        sb.append(item.getQuantity() + " " + item.getName());
        if (item.getQuantity() > 1) {
          sb.append("s ");
        } else {
          sb.append(" ");
        }
      }
      return sb.toString();
    }
  }

  boolean inventoryNumberPrint() {
    if (inventory.size() == 0) {
      System.out.println("nothing.");
      return false;
    } else {
      System.out.println("0. Done.");
      System.out.println("1. ALL.");
      int counter = 2;
      for (Item item : inventory) {
        System.out.println(counter + ". " + item.getName() + " : " + item.getQuantity());
        ++counter;
      }
      return true;
    }
  }

  // More detailed inventory printout for combat
  void showInventory() {
    if (inventory.size() == 0 && numStimpaks() == 0) {
      System.out.println("You have nothing.");
    } else if (inventory.size() == 1 && numStimpaks() == 0) {
      System.out.print("You have ");
      String curItem = inventory.get(0).getName();
      if (isVowel(curItem.charAt(0))) { // Grammar stuff
        System.out.println("an " + curItem + ".");
      } else {
        System.out.println("a " + curItem + ".");
      }
    } else if (inventory.size() == 1 && numStimpaks () > 0) { // Grammar stuffs
      System.out.print("You have " + numStimpaks() + " stimpaks.");
    } else {
      System.out.print("You have " + numStimpaks() + " stimpaks, ");
      for (int i = 0; i < inventory.size() - 1; ++i) {
        String curItem = inventory.get(i).getName();
        if (isVowel(curItem.charAt(0))) { // Mooooorreeee grammar!
          System.out.print("an " + curItem + ", ");
        } else {
          System.out.print("a " + curItem + ", ");
        }
      }
      String curItem = inventory.get(inventory.size() - 1).getName();
      if (isVowel(curItem.charAt(0))) { // Need I dare say more?
        System.out.println("and an " + curItem + ".");
      } else {
        System.out.println("and a " + curItem + ".");
      }
    }
  }

  // Add an item to the inventory
  boolean acquire(String item) {
    Item curItem = new Item(item);
    boolean itemFound = false;
    if (maxWeight < curWeight + curItem.getWeight()) { // Over capacity
      return false;
    } else if (inventory.size() > 0) {
      int counter = 0;
      while (!itemFound && (counter < inventory.size())) {
        if (inventory.get(counter).getName().equals(curItem.getName())) {
          inventory.get(counter).stack(); // Don't add a new item, just increase
          itemFound = true;               // the quantity.
          break;
        }
        ++counter;
      }
    }
    if (inventory.size() < 1 || !itemFound) { // Add a new item!
      inventory.add(curItem);
    }
    curWeight += curItem.getWeight();
    return true;
  }

  boolean dropAll(String item) {
    boolean itemFound = false;
    int counter = 0;
    while (!itemFound && counter < inventory.size()) {
      if (inventory.get(counter).getName().toLowerCase().equals(item.toLowerCase())) {
        Item curItem = inventory.get(counter);
        int quantity = curItem.getQuantity();
        int totalWeight = quantity * curItem.getWeight();
        inventory.remove(curItem);
        curWeight -= totalWeight;
        itemFound = true;
      }
      ++counter;
    }
    return itemFound;
  }
  // Remove an item from the inventory
  boolean drop(String item) {
    boolean itemFound = false;
    int counter = 0;
    while (!itemFound && counter < inventory.size()) {
      if (inventory.get(counter).getName().toLowerCase().equals(item.toLowerCase())) {
        Item curItem = inventory.get(counter);
        curWeight -= curItem.getWeight();
        curItem.drop();                         // Decrease quantity of item in question
        int quantity = curItem.getQuantity();
        if (quantity < 1) {
          inventory.remove(curItem);            // Remove AFTER drop so the quantity count
        }                                       // is accurate.
        itemFound = true;
      }
      ++counter;
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
