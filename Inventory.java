//Inventory mechanic

import java.util.ArrayList;

public class Inventory{
  public static void main(String[] args){}

  private int STARTING_STIMPAKS = 4;
  private ArrayList<Item> inventory = new ArrayList<Item>();
  private int maxWeight = 25;
  private int curWeight = 0;

  public Inventory() {
    for (int i = 0; i < STARTING_STIMPAKS; ++i)
      acquire("stimpak");
  }

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

  String InventorySimplePrint() {
    if (inventory.size() == 0) {
      return "nothing";
    } else {
      StringBuilder sb = new StringBuilder();
      for (Item item : inventory) {
        sb.append(item.getName() + " ");
      }
      return sb.toString();
    }
  }

  int getUsage() {
    return curWeight;
  }

  void ShowInventory() {
    if (inventory.size() == 0 && numStimpaks() == 0) {
      System.out.println("You have nothing.");
    } else if (inventory.size() == 1 && numStimpaks() == 0) {
      System.out.print("You have ");
      String curItem = inventory.get(0).getName();
      if (isVowel(curItem.charAt(0))) {
        System.out.println("an " + curItem + ".");
      } else {
        System.out.println("a " + curItem + ".");
      }
    } else if (inventory.size() == 1 && numStimpaks () > 0) {
      System.out.print("You have " + numStimpaks() + " stimpaks.");
    } else {
      System.out.print("You have " + numStimpaks() + " stimpaks, ");
      for (int i = 0; i < inventory.size() - 1; ++i) {
        String curItem = inventory.get(i).getName();
        if (isVowel(curItem.charAt(0))) {
          System.out.print("an " + curItem + ", ");
        } else {
          System.out.print("a " + curItem + ", ");
        }
      }
      String curItem = inventory.get(inventory.size() - 1).getName();
      if (isVowel(curItem.charAt(0))) {
        System.out.println("and an " + curItem + ".");
      } else {
        System.out.println("and a " + curItem + ".");
      }
    }
  }

  boolean acquire(String item) {
    Item curItem = new Item(item);
    boolean itemFound = false;
    if (maxWeight < curWeight + curItem.getWeight()) {
      return false;
    } else if (inventory.size() > 0) {
      int counter = 0;
      while (!itemFound && (counter < inventory.size())) {
        if (inventory.get(counter).getName().equals(curItem.getName())) {
          inventory.get(counter).stack();
          itemFound = true;
          break;
        }
        ++counter;
      }
    } else if (inventory.size() < 1 || !itemFound) {
      inventory.add(curItem);
      curWeight += curItem.getWeight();
    }
    return true;
  }

  boolean drop(String item) {
    boolean itemFound = false;
    int counter = 0;
    while (!itemFound && counter < inventory.size()) {
      if (inventory.get(counter).getName().equals(item)) {
        Item curItem = inventory.get(counter);
        int quantity = curItem.getQuantity();
        curItem.drop();
        if (quantity == 1) {
          inventory.remove(curItem);
        }
        itemFound = true;
      }
      ++counter;
    }
    return itemFound;
  }

  void upgrade() {
    this.maxWeight += 25;
  }

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
