//Item class. Each Item will have its own special attributes

public class Item {
  public static void main(String[] args) {}

  private String name = "object";
  private String color = "grey";
  private int weight = 5;      // Used for inventory mechanic. YOU GOTTA CHOOSE!
  private boolean regenerative = false; // Used for health/healing objects
  private int regenAmount = 0; // The amount of health regenerated from a stimpak
  private int frequency = 0;   // Quantity. The amount of THIS the player has.

  public Item() {}

  public Item(String n) {
    this.name = n;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item(String n, String c) {
    this.name = n;
    this.color = c;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (String n, String c, int w) {
    this.name = n;
    this.color = c;
    this.weight = w;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  String getName() {
    return this.name;
  }

  int getWeight() {
    return this.weight;
  }

  String getColor() {
    return this.color;
  }

  // Simple item description.
  void examine() {
    System.out.print("It's a " + this.name + ". It's " + this.color + " and it "
    + "weighs " + this.weight);
  }

  // You got another one. How cool.
  void stack() {
    ++this.frequency;
  }

  // Another one bites the dust. Damn rip.
  void drop() {
    --this.frequency;
  }

  // How many of me are there???
  int getQuantity() {
    return this.frequency;
  }

  // You may or may not. Depends.
  int heal() {
    return this.regenAmount;
  }

  // Can I heeeaals ya? Heelys? No? I tried.
  boolean isRegenerative() {
    return this.regenerative;
  }
}
