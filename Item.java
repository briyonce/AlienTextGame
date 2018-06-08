//Item class. Each Item will have its own special attributes

public class Item {
  public static void main(String[] args) {}

  private String name = "object";
  private String color = "grey";
  private int weight = 5;
  private boolean regenerative = false;
  private int regenAmount = 0; // The amount of health regenerated from a stimpak
  private int frequency = 0;

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

  void examine() {
    System.out.print("It's a " + this.name + ". It's " + this.color + " and it "
    + "weighs " + this.weight);
  }

  void stack() {
    ++this.frequency;
  }

  void drop() {
    --this.frequency;
  }

  int getQuantity() {
    return this.frequency;
  }

  int heal() {
    return this.regenAmount;
  }

  boolean isRegenerative() {
    return this.regenerative;
  }
}
