import java.util.*;

public class Scheduling {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		String str = s.nextLine();
		String[] arr = str.split(" ");
		
		int r = Integer.parseInt(arr[0]);
		int c = Integer.parseInt(arr[1]);
		int n = Integer.parseInt(arr[2]);
		
		while(r != 0 || c != 0 || n != 0) {
			
			HashMap<String, ArrayList<String>> students = new HashMap<String, ArrayList<String>>();
			for(int i = 0; i < r; i++) {
				str = s.nextLine();
				arr = str.split(" ");
				if(students.containsKey(arr[0])) students.get(arr[0]).add(arr[1]);
				else {
					students.put(arr[0], new ArrayList<String>());
					students.get(arr[0]).add(arr[1]);
				}
			}
			
			HashMap<String, int[]> classes = new HashMap<String, int[]>();
			for(int i = 0; i < c; i++) {
				str = s.nextLine();
				arr = str.split(" ");
				int[] classInfo = {Integer.parseInt(arr[1]), i};
				classes.put(arr[0], classInfo);
			}
			
			int size = students.size() + c + 2;
			int[][] graph = new int[size][size];
			for(int i = 1; i < students.size() + 1; i++) graph[0][i] = n;
			
			int row = 1;
			for(String student : students.keySet()) {
				for(String request : students.get(student)) {
					graph[row][classes.get(request)[1] + students.size() + 1] = 1;
				}
				row++;
			}
			
			for(String course : classes.keySet()) {
				graph[classes.get(course)[1] + students.size() + 1][size - 1] = classes.get(course)[0];
			}
			
			if(maxflow(graph) == students.size()*n) System.out.println("Yes");
			else System.out.println("No");
			
			str = s.nextLine();
			if(str.equals("")) str = s.nextLine();
			arr = str.split(" ");
			r = Integer.parseInt(arr[0]);
			c = Integer.parseInt(arr[1]);
			n = Integer.parseInt(arr[2]);
		}
		
		s.close();
	}
	
	public static void printArr(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printArrList(ArrayList<Integer> arrlist) {
		System.out.print("\t");
		for(int x : arrlist) System.out.print(x + " ");
		System.out.println();
	}
	
	public static int maxflow(int[][] graph) {
		
		int[][] gf = new int[graph.length][graph.length];
		for(int i = 0; i < graph.length; i++) 
			for(int j = 0; j < graph.length; j++)
				gf[i][j] = graph[i][j];
		
		int maxflow = 0;
		int pathflow;
		
		ArrayList<Integer> path = dfs(gf);
		while(path != null) {
			pathflow = Integer.MAX_VALUE;
			for(int i = 0; i < path.size() - 1; i++) {
				pathflow = Math.min(pathflow, gf[path.get(i)][path.get(i + 1)]);
			}
			
			for(int i = 0; i < path.size() - 1; i++) {
				gf[path.get(i)][path.get(i + 1)] -= pathflow;
				gf[path.get(i + 1)][path.get(i)] += pathflow;
			}
			
			maxflow += pathflow;
			path = dfs(gf);
		}
		return maxflow;
	}
	
	public static ArrayList<Integer> dfs(int[][] graph) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		boolean found = false;
		dfs_recurse(graph, visited, found, 0);
		if(visited.contains(graph.length - 1)) {
			return visited;
		}
		else return null;
	}
	
	public static boolean dfs_recurse(int[][] graph, ArrayList<Integer> visited, boolean found, int curnode) {
		if(found) return found;
		visited.add(curnode);
		if(curnode == graph.length - 1) {
			found = true;
			return found;
		}
		for(int i = 0; i < graph.length; i++) {
			if(!visited.contains(i) && graph[curnode][i] > 0) found = dfs_recurse(graph, visited, found, i);
		}
		if(found) return found;
		visited.remove(visited.size() - 1);
		return found;
	}

}
