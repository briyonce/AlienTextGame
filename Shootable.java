// Shootable weapons do not break over time. They just run through ammo

public class Shootable extends Weapon {

  private int ammo = 10; // number of rounds/arrows. etc

  public Shootable (String n) {
    super.name = n;
    super.fireRate = 5;
    super.range = 3;
  }

  void attack() {
    String [] attacks = {"You pull back the hammer and feel the barrel spin into place."};
  }

  int getAmmo() {
    return this.ammo;
  }

}
