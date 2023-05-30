package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Vertex<Type> {

	// used to id the Vertex
	private Type obj;
	private int indegree;

	// adjacency list
	private LinkedList<Edge> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param obj - object used to identify this Vertex
	 */
	public Vertex(Type obj) {
		this.obj = obj;
		this.adj = new LinkedList<Edge>();
		this.indegree = 0;

	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public Type getName() {
		return obj;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	/**
	 * add 1 to indegree
	 */
	public void addDegree(){
		indegree++;
	}

	/**
	 * returns indegree
	 * @return
	 */
	public int getDegree(){
		return indegree;
	}

	/**
	 * subtracts one from indegree
	 */
	public void minusDegree(){
		indegree--;
	}


	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + obj + " adjacent to vertices ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}