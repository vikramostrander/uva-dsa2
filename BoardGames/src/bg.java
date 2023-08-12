import java.util.*;

public class bg {
	
	public static void main(String[] args) {
		
		Scanner s = new java.util.Scanner(System.in);
		int numNodes = s.nextInt();
		int numEdges = s.nextInt();
		
		HashMap<Integer, TreeSet<Integer>> graph = new HashMap<Integer, TreeSet<Integer>>();
		
		for(int i = 0; i < numNodes; i++) {
			graph.put(i, new TreeSet<Integer>());
		}
		
		int node1, node2;
		for(int i = 0; i < numEdges; i++) {
			node1 = s.nextInt();
			node2 = s.nextInt();
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		int numDangerous = s.nextInt();
		HashSet<Integer> dangerous = new HashSet<Integer>();
		for(int i = 0; i < numDangerous; i++) {
			dangerous.add(s.nextInt());
		}
		
		dfs(graph, dangerous);
		s.close();
	}
	
	public static void dfs(HashMap<Integer, TreeSet<Integer>> graph, HashSet<Integer> dangerous) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		dfs_recurse(graph, dangerous, visited, 0);
	}
	
	public static void dfs_recurse(HashMap<Integer, TreeSet<Integer>> graph, HashSet<Integer> dangerous, ArrayList<Integer> visited, int curnode) {
		visited.add(curnode);
		if(curnode == graph.size() - 1) {
			String output = "";
			for(int i = 0; i < visited.size(); i++) {
				output += visited.get(i) + "-";
			}
			System.out.println(output.substring(0, output.length() - 1));
		}
		else {
			for(int i : graph.get(curnode)) {
				if(!visited.contains(i) && !dangerous.contains(i)) dfs_recurse(graph, dangerous, visited, i);
			}
		}
		visited.remove(visited.size() - 1);
	}
}