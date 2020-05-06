import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.ImmutableValueGraph.Builder;
import com.google.common.graph.ValueGraphBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class DijkNode{
	Integer Node;
	Integer Before;
	boolean isMin;
	int nowValue;
	
	DijkNode(Integer node){
		this.Node = node;
		this.Before = null;
		this.isMin = false;
		this.nowValue = Integer.MAX_VALUE;
	}
}

public class Search {


	/**
	 * Lists all nodes values in a given graph.
	 *
	 * @param graph the graph to query the nodes from
	 * @return lists of all the nodes in the given graph
	 */
	static Collection<Integer> listAllNodes(ImmutableValueGraph<Integer, Integer> graph) {
//		throw new UnsupportedOperationException("Implement me");
		Collection<Integer> ans = graph.nodes();
		return ans;
	}

	/**
	 * Lists all edge values in a given graph.
	 *
	 * @param graph the graph to query the edges from
	 * @return lists of all the edges in the given graph
	 */
	static Collection<Integer> listAllEdges(ImmutableValueGraph<Integer, Integer> graph) {
//		throw new UnsupportedOperationException("Implement me");
		Collection<Integer> ans = new ArrayList<Integer>();
		Collection<Integer> Nodes = graph.nodes();
		for(Integer i : Nodes) {
			for(Integer j : Nodes) {
				if(i < j && graph.hasEdgeConnecting(i, j)) {    // 有连接判定 i < j防止重复计算
					ans.add(graph.edgeValueOrDefault(i, j, null));
				}
			}
		}
		return ans;
	}

	/**
	 * Lists all nodes with 4 or more edges
	 *
	 * @param graph the graph to query the edges from
	 * @return lists of all nodes that satisfy the condition
	 */
	static Collection<Integer> findAllNodeWith4OrMoreEdges(
			ImmutableValueGraph<Integer, Integer> graph) {
//		throw new UnsupportedOperationException("Implement me");
		Collection<Integer> ans = new ArrayList<Integer>();
		Collection<Integer> Nodes = graph.nodes();
		Map<Integer, Integer> EdgesNum = new HashMap<Integer, Integer>();
		for(Integer i : Nodes) {   // 存储边的数量 初始为0
			EdgesNum.put(i, 0);
		}

		for(Integer i : Nodes) {
			for(Integer j : Nodes) {
				if(i < j && graph.hasEdgeConnecting(i, j)) {   // i<j防止重复计算 有连接则两个结点均+1
					EdgesNum.put(i, EdgesNum.get(i) + 1);
					EdgesNum.put(j, EdgesNum.get(j) + 1);
				}
			}
		}
		
		for(Integer i: Nodes) {
			if(EdgesNum.get(i) >= 4) {  // 判定
				ans.add(i);
			}
		}
		
		return ans;
	}

	/**
	 * Lists all nodes with edges values that when summed, is > 20
	 * For example, a node with three connected edges: 1, 2, 3 has an edge sum of 6.
	 *
	 * @param graph the graph to query the edges from
	 * @return lists of all nodes that satisfy the condition
	 */
	static Collection<Integer> findAllNodesWithEdgeSumGreaterThan20(
			ImmutableValueGraph<Integer, Integer> graph) {
//		throw new UnsupportedOperationException("Implement me");
		Collection<Integer> ans = new ArrayList<Integer>();
		Collection<Integer> Nodes = graph.nodes();
		Map<Integer, Integer> EdgesNum = new HashMap<Integer, Integer>();
		for(Integer i : Nodes) {  // 存储边的权值 初始为0
			EdgesNum.put(i, 0);
		}
		
		for(Integer i : Nodes) {
			for(Integer j : Nodes) {   // 有连接 总权值+当前权值
				if(i < j && graph.hasEdgeConnecting(i, j)) {
					EdgesNum.put(i,  EdgesNum.get(i) + graph.edgeValueOrDefault(i, j, null));
					EdgesNum.put(j,  EdgesNum.get(j) + graph.edgeValueOrDefault(i, j, null));
				}
			}
		}
		
		for(Integer i: Nodes) {   // 判定
			if(EdgesNum.get(i) >= 20) {
				ans.add(i);
			}
		}
		
		return ans;
	}


	/**
	 * Finds the shortest possible path that travels from the source to destination, factoring the
	 * edge distances.
	 * A path that allows you to travel from the source to the destination with the minimum total
	 * edge distances is the shortest path.
	 *
	 * @param graph the graph to compute the shortest path with
	 * @param source the starting position of the search, the resulting list should end with this
	 * value
	 * @param destination the end position of the search, the resulting list should end with this
	 * value
	 * @return a list of nodes that represent the shortest path from source to destination
	 */
	static List<Integer> shortestPathFromSourceToDestination(
			ImmutableValueGraph<Integer, Integer> graph,
			Integer source,
			Integer destination) {

	}

	// reads in a graph stored in plan text, not part of any question but feel free to study at how
	// a graph is constructed
	static ImmutableValueGraph<Integer, Integer> readGraph(String content) {
		List<String> lines = content.lines().collect(Collectors.toList());
		if (lines.isEmpty()) throw new IllegalArgumentException("No lines");
		int currentLine = 0;

		String[] topLine = lines.get(currentLine++).split(" ");
		int numberOfNodes = Integer.parseInt(topLine[0]);
		int numberOfEdges = Integer.parseInt(topLine[1]);

		Builder<Integer, Integer> builder = ValueGraphBuilder
				.undirected()
				.expectedNodeCount(numberOfNodes)
				.immutable();


		for (int i = 0; i < numberOfNodes; i++) {
			String line = lines.get(currentLine++);
			if (line.isEmpty()) continue;
			builder.addNode(Integer.parseInt(line));
		}

		for (int i = 0; i < numberOfEdges; i++) {
			String line = lines.get(currentLine++);
			if (line.isEmpty()) continue;

			String[] s = line.split(" ");
			if (s.length != 3) throw new IllegalArgumentException("Bad edge line:" + line);
			builder.putEdgeValue(Integer.parseInt(s[0]),
					Integer.parseInt(s[1]),
					Integer.parseInt(s[2]));
		}
		return builder.build();
	}


}
