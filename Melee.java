// Melee weapons wear down over time but they can be repaired
import java.util.Random;

public class Melee extends Weapon {
  private int MAX_HEALTH = 10;
  private int health = 10;

  public Melee (String n) {
    super(n);
  }

  public Melee (String n, int w) {
    super(n, w);
    if (n.toLowerCase().equals("fist")) {
      this.health = Integer.MAX_VALUE;
    }
  }

  int getHealth() {
    return this.health;
  }

  // Melee weapons wear down over time
  void wear() {
    health -= 2;
  }


  // Repair said melee weapon to allow
  // for further use.
  void repair() {
    Random r = new Random();
    int amount = r.nextInt(MAX_HEALTH);
    health += amount;
  }

  // Attack w/ melee weapon
  void attack(Entity e) {
    System.out.println("Attacking with " + super.getName() + ".\n");
    this.wear();
  }
}
