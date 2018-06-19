// Melee weapons wear down over time but they can be repaired
import java.util.Random;

public class Melee extends Weapon {
  private int MAX_HEALTH = 10;
  private int health = 10;

  public Melee (String n) {
    super.name = n;
  }

  int getHealth() {
    return this.health;
  }

  // Melee weapons wear down over time
  void wear() {
    health -= 2;
  }

  void repair() {
    Random r = new Random();
    int amount = r.nextInt(MAX_HEALTH);
    health += amount;
  }
}
