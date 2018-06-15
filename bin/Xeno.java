// Specified class for Xenomorph-type creatures

import java.util.Random;

public class Xeno extends Enemy {
  public static void main(String[] args) {}

  private String[] xeno_castes = {"Queen", "Praetorian", "Predalien", "Spitter", "Lurker", "Runner", "Warrior"};
  private String[] xeno_types = {"Facehugger", "Chest-Burster", "Xenomorph", "Bloodburster", "Neomorph"};

  public Xeno () {
    Random r = new Random();
    String name = xeno_types[r.nextInt(xeno_types.length)];
    super.alterName(name);
  }

  public Xeno (String name, int health, int maxDamage) {
    super(name, health, maxDamage);
  }
}
