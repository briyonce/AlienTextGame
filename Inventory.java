//Inventory mechanic

import java.util.ArrayList;

public class Inventory{
  public static void main(String[] args){}

  private ArrayList<Item> inventory = new ArrayList<Item>();
  int maxWeight = 25;
  int curWeight = 0;

  public Inventory() {}

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

  void ShowInventory() {
    if (inventory.size() == 0) {
      System.out.println("You have nothing.");
    } else if (inventory.size() == 1) {
      System.out.print("You have ");
      String curItem = inventory.get(0).getName();
      if (isVowel(curItem.charAt(0))) {
        System.out.println("an " + curItem + ".");
      } else {
        System.out.println("a " + curItem + ".");
      }
    } else {
      System.out.print("You have ");
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

  void acquire(String item) {
    curItem = new Item(item);
    if (maxWeight < curWeight + curItem.getWeight()) {
      System.out.println("INVENTORY FULL: " + (curWeight + curItem.getWeight()));
      System.out.println("You need to drop something.");
    } else {
      boolean itemFound = false;
      int counter = 0;
      while (!itemFound) {
        if (inventory.get(counter).getName().equals(curItem.getName)) {
          inventory.get(counter).stack();
          itemFound = true;
        }
        ++counter;
      }
      if (!itemFound)
        inventory.add(curItem);
      curWeight += curItem.getWeight();
    }
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
