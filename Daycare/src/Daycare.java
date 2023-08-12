import java.util.*;

class Room implements Comparable<Room> {
	int cap1;
	int cap2;
	int delta;
	
	public Room(int cap1, int cap2) {
		this.cap1 = cap1;
		this.cap2 = cap2;
		delta = this.cap2 - this.cap1;
	}
	
	public int compareTo(Room room) {
		return this.cap1 - room.cap1;
	}
}

public class Daycare {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String str;
		String[] arr;
		int numRooms;
		Room room;
		ArrayList<Room> rooms_positive;
		ArrayList<Room> rooms_negative;
		
		while(s.hasNextLine()) {
			str = s.nextLine();
			numRooms = Integer.parseInt(str);
			rooms_positive = new ArrayList<Room>();
			rooms_negative = new ArrayList<Room>();
			for(int n = 0; n < numRooms; n++) {
				str = s.nextLine();
				arr = str.split(" ");
				room = new Room(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
				
				if(room.delta < 0) {
					rooms_negative.add(room);
					
				}
				else {
					rooms_positive.add(0, room);
				}
			}
			Collections.sort(rooms_positive);
			Collections.sort(rooms_negative, Collections.reverseOrder());
			ArrayList<Room> rooms = new ArrayList<Room>();
			rooms.addAll(rooms_positive);
			rooms.addAll(rooms_negative);
			
			System.out.println(remodel(rooms));
		}
		
		s.close();
	}

	public static int remodel(ArrayList<Room> rooms) {
		int trailer = 0, empty = 0, needed = 0;
		for(Room room : rooms) {
			//System.out.println("room " + room.cap1);
			//System.out.println("--> t=" + trailer + ", e=" + empty);
			
			if(room.cap1 > empty) {
				needed = room.cap1 - empty;
				trailer += needed;
				empty = room.cap2;
			}
			else empty += room.delta;
			
			if(empty < 0) {
				trailer += empty*-1;
				empty = 0;
			}
			//System.out.println("--> t=" + trailer + ", e=" + empty);
		}
		//System.out.println("final t = " + trailer + ", e = " + empty);
		return trailer;
	}
}
