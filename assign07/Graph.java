package assign07;

import java.util.*;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is not generic and assumes that a string name is stored at each vertex.
 * 
 * @author Erin Parker and Isaac Hall and Bradley Bartelt
 * @version March 3, 2022
 */
public class Graph<Type> {

	// the graph -- a set of vertices (object mapped to Vertex instance)
	private HashMap<Type, Vertex> vertices;

	private int size;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex>();
		size = 0;
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(Type name1, Type name2) {
		Vertex vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
			size++;
		}

		Vertex vertex2;
		if(vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
			size++;
		}

		vertices.get(name2).addDegree();

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex v : vertices.values()) {
			// for every edge
			Iterator<Edge> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");
			
		}
		
		return dot.toString() + "}";
	}

	/**
	 *
	 * Runs depth for search on a graph made up of our sources and destinations
	 *
	 * @param start
	 * @param destination
	 * @param visited
	 * @return boolean
	 */
	public boolean depthFirstSearch(Type start, Type destination, HashSet<Type> visited){
		// if the destination or the start doesn't exist we throw an exception
		if(!vertices.containsKey(destination) || !vertices.containsKey(start)){
			throw new IllegalArgumentException();
		}
		// marks the start vertex as visited
		visited.add(start);
		if(start.equals(destination)){
			return true;
		}

		Iterator<Edge> iterator = vertices.get(start).edges();

		while(iterator.hasNext()){
			// saves the neighbors of the current vertex
			Type neighbor = (Type) iterator.next().getOtherVertex().getName();
			// if they haven't been visited recurssively calls from the neighbor to the destination
			if (!visited.contains(neighbor)){
				return depthFirstSearch(neighbor, destination, visited);
			}
		}
		return false;
	}

	/**
	 *
	 * finds the shortest path between two different vertexes
	 *
	 * @param start
	 * @param destination
	 * @param visited
	 * @return List
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public <Type> List<Type> breadthFirstSearch(Type start, Type destination, HashSet<Type> visited) throws IllegalArgumentException{
		if(start.equals(destination)){
			ArrayList<Type> returnArray = new ArrayList<Type>();
			returnArray.add(start);
			return returnArray;
		}

		HashMap<Type,Type> cameFrom = new HashMap<>();
		Queue<Type> toVisit = new LinkedList<Type>();
		toVisit.add(start);
		visited.add(start);

		while(!toVisit.isEmpty()){
			Type n = toVisit.remove();

			if(n.equals(destination)){
				return reconstructPath(start, destination, cameFrom);
			}

			Iterator<Edge> iterator = vertices.get(n).edges();
			// looks through the current vertexes neighbors
			while(iterator.hasNext()){
				Type neighbor = (Type) iterator.next().getOtherVertex().getName();
				// if the neighbor hasn't been visited adds it to the queue and saves the node it came from
				if (!visited.contains(neighbor)){
					cameFrom.put(neighbor, n);
					visited.add(neighbor);
					toVisit.add(neighbor);
				}
			}


		}
		throw new IllegalArgumentException();
	}

	/**
	 * topological sort algorithm, returns a list containing all the nodes in a graph sorted where every node appears
	 * before all the nodes it points too.
	 *
	 * @param sources
	 * @param destinations
	 * @param visited
	 * @return
	 * @param <Type>
	 */
	public <Type> List<Type> topologicalSort(List<Type> sources, List<Type> destinations, HashSet<Type> visited){

		Queue<Type> topQueue = new LinkedList<Type>();

		List<Type> output = new ArrayList<Type>();

		//adds every vertex that has an indegree of 0 to the queue (no other nodes point to it)
		for(Type i: sources){
			if(vertices.get(i).getDegree() == 0){
				topQueue.add(i);
				vertices.get(i).minusDegree();
			}
		}

		//while there are still vertexes left to be visited in the graph
		while (!topQueue.isEmpty() ){
			//removes the vertex at the beginning of the queue and adds it to the output list
			Type n = topQueue.remove();
			output.add(n);

			Iterator<Edge> iterator = vertices.get(n).edges();

			//iterates through the neighbors of the current vertex
			while(iterator.hasNext()){
				Type neighbor = (Type) iterator.next().getOtherVertex().getName();
				//decreases the indegree of the neighbor
				vertices.get(neighbor).minusDegree();
				//if the indegree is 0, add it to the queue so it can be added to the output list
				if(vertices.get(neighbor).getDegree() == 0){
					topQueue.add(neighbor);
				}
			}
		}
		//if there was a cycle in the graph
		if(output.size() != size){
			throw new IllegalArgumentException();
		}
		return output;
	}

	/**
	 *
	 * helper method for Breadth First Search  which creates the shortest path.
	 *
	 * @param start
	 * @param destination
	 * @param cameFrom
	 * @return List
	 * @param <Type>
	 */
	private <Type> List<Type> reconstructPath(Type start, Type destination, HashMap<Type,Type> cameFrom){
		List<Type> path = new ArrayList<>();
		// backwardly appends the values from cameFrom to path
		for(Type end = destination; end != start; end = cameFrom.get(end)){
			path.add(end);
		}
		path.add(start);
		//reverses the list after adding the starting value
		Collections.reverse(path);
		return path;
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex v : vertices.values()) 
			result.append(v + "\n");
		
		return result.toString();
	}
}