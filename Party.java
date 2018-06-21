// Keeps track of all of the active members of a "party"! Wow, how imaginative.

import java.util.ArrayList;

// It's time to get THROWED!
public class Party{
  public static void main(String[] args) {};

  private ArrayList<Entity> members = new ArrayList<Entity>();
  private int partySize = 0; // used for speed

  public Party () {}

  public Party (Human h) {
    members.add(h);
    ++this.partySize;
  }

  public Party (ArrayList<Entity> p) {
    this.members = p;
    this.partySize = p.size();
  }

  // Strengthen in numbers
  void addMember(Human h) {
    members.add(h);
    ++this.partySize;
  }

  // Add a member to the party
  void addMember(Enemy e) {
    members.add(e);
    ++this.partySize;
  }

  // Return member in question.
  // Used for turn-by-turn combat to get the
  // next member to fight.
  Entity getMember(int index) {
    return members.get(index);
  }

  // They weren't that cool anyways...
  void removeMember(Entity e) {
    members.remove(e);
    --this.partySize;
  }

  // Return the number of humans in the party.
  int numMembers() {
    return this.partySize;
  }

  // Used to discard enemies after protagonist flees
  void empty() {
    members.clear();
  }

  // Overview of all active players, their stats, and their inventories.
  void printParty() {
    for (Entity e : this.members) {
      System.out.print(e.getName() + ": " + e.getHealth());
      if (e instanceof Human) {
        Human h = (Human) e;
        if (h.isAlive()) // Just making sure
           System.out.print( " - " + h.inventorySimplePrint());
      }
      System.out.println();
    }
  }

  // Used in combat so the player is aware of the party's status
  void printPartyHealth() {
    for (Entity e : this.members) {
      System.out.print(e.getName() + "'s Health: " + e.getHealth() + "  ");
    }
    System.out.println();
  }

  // Just in case somebody uses this and doesn't actually look at the files.
  int size() {
    return this.partySize;
  }
}
