// Keeps track of all of the active members of a "party"! Wow, how imaginative.

import java.util.ArrayList;

// It's time to get THROWED!
public class Party{
  public static void main(String[] args) {};

  private ArrayList<Human> members = new ArrayList<Human>();
  private int partySize;

  public Party () {
    partySize = 0;
  }

  public Party (Human h) {
    members.add(h);
  }

  public Party (ArrayList<Human> p) {
    members = p;
  }

  // Strengthen in numbers
  void addMember(Human h) {
    members.add(h);
  }

  // They weren't that cool anyways...
  void removeMember(Human h) {
    members.remove(h);
  }

  // Return the number of humans in the party.
  int numMembers() {
    return members.size();
  }

  // Overview of all active players, their stats, and their inventories.
  void printParty() {
    for (Human h : members) {
      if (h.isAlive())
        System.out.println(h.getName() + ": " + h.getHealth() + " - " + h.inventorySimplePrint());
    }
  }

  // Just in case somebody uses this and doesn't actually look at the files.
  int size() {
    return this.partySize;
  }
}
