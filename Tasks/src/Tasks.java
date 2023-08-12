import java.util.*;

class TaskNode {
	public String color;
	public TreeSet<String> adj;
	
	public TaskNode() {
		color = "WHITE";
		adj = new TreeSet<String>();
	}
}

public class Tasks {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		String[] arr = str.split(" ");
		
		int numNodes = Integer.parseInt(arr[0]);
		int numEdges = Integer.parseInt(arr[1]);
		
		TreeMap<String, TaskNode> graph = new TreeMap<String, TaskNode>();
		
		for(int i = 0; i < numNodes; i++) {
			str = s.nextLine();
			graph.put(str, new TaskNode());
		}
		
		String key, adjNode;
		for(int i = 0; i < numEdges; i++) {
			str = s.nextLine();
			key = str.substring(0, str.indexOf(' '));
			adjNode = str.substring(str.indexOf(' ') + 1);
			graph.get(key).adj.add(adjNode);
		}
		
		dfs(graph);
		s.close();
	}
	
	public static void dfs(TreeMap<String, TaskNode> graph) {
		ArrayList<String> toposort = new ArrayList<String>();
		boolean cycle = false;
		
		for(String key : graph.descendingKeySet()) {
			if(graph.get(key).color.equals("WHITE")) {
				cycle = dfs_recurse(graph, key, toposort, cycle);
			}
		}
		
		String output = "";
		if(cycle) output = "IMPOSSIBLE ";
		else {
			for(String str : toposort) {
				output = str + " " + output;
			}
		}
		System.out.println(output.substring(0, output.length() - 1));
	}
	
	public static boolean dfs_recurse(TreeMap<String, TaskNode> graph, String key, ArrayList<String> toposort, boolean cycle) {		
		graph.get(key).color = "GRAY";
		
		for(String adj : graph.get(key).adj.descendingSet()) {
			if(graph.get(adj).color.equals("WHITE")) {
				 cycle = dfs_recurse(graph, adj, toposort, cycle);
			}
			if(graph.get(adj).color.equals("GRAY")) {
				cycle = true;
			}
		}
		
		graph.get(key).color = "BLACK";
		toposort.add(key);
		return cycle;
	}
}

