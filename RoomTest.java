public class RoomTest {
  public static void main(String[] args) {
    Room curRoom = new Room("med bay");
    curRoom.display();
    Human h = new Human();
    Party p = new Party(h);
    p.printParty();
    curRoom.lootRoom(h);
    p.printParty();
  }
}
