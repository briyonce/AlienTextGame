// Things such as spells, throwing Knives, etc. One time use. You may have multiple, though.

public class Ranged extends Weapon {

  public Ranged (String n) {
    super(n);
  }

  public Ranged(String n, int w) {
    super(n, w);
  }

  public void attack () {
    System.out.println("Attacking with ranged weapon.\n");
  }
}
