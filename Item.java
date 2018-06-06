//Item class. Each Item will have its own special attributes

public class Item {
  public static void main(String[] args) {}

  private String name = "object";
  private String color = "grey";
  private int weight = 5;
  private boolean regenerative = false;
  int frequency = 0;

  public Item() {}

  public Item(String n) {
    this.name = n;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
    }
  }

  public Item(String n, String c) {
    this.name = n;
    this.color = c;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
    }
  }

  public Item (String n, String c, int w) {
    this.name = n;
    this.color = c;
    this.weight = w;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
    }
  }

  String getName() {
    return this.name;
  }

  int getWeight() {
    return this.weight;
  }

  int getColor() {
    return this.color;
  }

  void examine() {
    System.out.print("It's a " + this.name + ". It's " + this.color + " and it "
    + "weighs " + this.weight);
  }

  void stack() {
    ++frequency;
  }
}
