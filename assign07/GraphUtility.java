package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Erin Parker & Bradley Bartelt & Isaac Hall
 * @version March 3, 2022
 */
public class GraphUtility {

	/**
	 * public driver method for our implementation of the depth first search algorithm.
	 *
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {

		Graph graph = constructGraph(sources,destinations);

		HashSet<Type> visited = new HashSet<>();

		return graph.depthFirstSearch(srcData,dstData,visited);
	}

	/**
	 * Public driver for implementation of breadth first search.
	 *
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {

		Graph graph = constructGraph(sources,destinations);

		HashSet<Type> visited = new HashSet<>();

		return graph.breadthFirstSearch(srcData,dstData,visited);
	}

	/**
	 * public driver method for our implementation of topilogical sort.
	 *
	 * @param sources
	 * @param destinations
	 * @return
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {

		Graph graph = constructGraph(sources,destinations);

		HashSet<Type> visited = new HashSet<>();

		return graph.topologicalSort(sources, destinations,visited);
	}

	/**
	 * constructs a graph with the provided source and destination lists
	 * @param sources
	 * @param destinations
	 * @return
	 * @param <Type>
	 */
	private static <Type> Graph constructGraph(List<Type> sources, List<Type> destinations) throws IllegalArgumentException{

		if(sources.size() != destinations.size()){
			throw new IllegalArgumentException();
		}

		Graph graph = new Graph();
		for(int i = 0; i< sources.size(); i++) {
			graph.addEdge(sources.get(i),destinations.get(i));
		}

		return graph;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}

}
