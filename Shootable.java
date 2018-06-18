// Shootable weapons do not break over time. They just run through ammo

public class Shootable extends Weapon {
  private int range = 3; // number of blocks the bullet can travel
  private int ammo = 10; // number of rounds/arrows. etc

  public Shootable () {

  }

  void attack() {
    String [] attacks = {"You pull back the hammer and feel the barrel spin into place."};
    
  }
}
