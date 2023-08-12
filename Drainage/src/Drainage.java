import java.util.*;

class Point implements Comparable<Point> {
	int r, c, v, index;
	ArrayList<Point> neighbors;
	
	public Point(int r, int c, int v) {
		this.r = r;
		this.c = c;
		this.v = v;
		neighbors = new ArrayList<Point>();
	}
	
	public int compareTo(Point p) {
		return this.v - p.v;
	}
}

public class Drainage {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		int numTrials = Integer.parseInt(str);
		
		String[] arr;
		String town;
		int rows, columns;
		ArrayList<Point> points;
				
		for(int trial = 0; trial < numTrials; trial++) {
			str = s.nextLine();
			arr = str.split(" ");
			town = arr[0];
			rows = Integer.parseInt(arr[1]);
			columns = Integer.parseInt(arr[2]);
			points = new ArrayList<Point>();
			
			for(int r = 0; r < rows; r++) {
				str = s.nextLine();
				arr = str.split(" ");
				for(int c = 0; c < columns; c++) {
					points.add(new Point(r, c, Integer.parseInt(arr[c])));
				}
			}
			
			addNeighbors(points, rows, columns);
			Collections.sort(points);
			
			System.out.println(town + ": " + solve(points));
		}
		
		s.close();
	}
	
	public static int solve(ArrayList<Point> points) {
		ArrayList<Integer> memory = new ArrayList<Integer>();
		int max;
		
		for(int i = 0; i < points.size(); i++) {
			points.get(i).index = i;
			max = 1;
			for(Point p : points.get(i).neighbors) {
				max = Math.max(max, memory.get(p.index) + 1);
			}
			memory.add(max);
		}
		
		return Collections.max(memory);
	}
	
	public static void addNeighbors(ArrayList<Point> points, int rows, int columns) {
		int index;
		
		for(int i = 0; i < points.size(); i++) {
			
			if(points.get(i).r != 0) {
				index = i - columns;
				if(points.get(i).v > points.get(index).v) 
					points.get(i).neighbors.add(points.get(index));
			}
			
			if(points.get(i).r != rows - 1) {
				index = i + columns;
				if(points.get(i).v > points.get(index).v) 
					points.get(i).neighbors.add(points.get(index));
			}
			
			if(points.get(i).c != 0) {
				index = i - 1;
				if(points.get(i).v > points.get(index).v) 
					points.get(i).neighbors.add(points.get(index));
			}
			
			if(points.get(i).c != columns - 1) {
				index = i + 1;
				if(points.get(i).v > points.get(index).v) 
					points.get(i).neighbors.add(points.get(index));
			}
		}
	}
}
