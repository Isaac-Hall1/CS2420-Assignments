package assign08;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a reference to the left child, and a reference to the right child.
 * 
 * @author Erin Parker, Isaac Hall, and Bradley Bartelt
 * @version March 18, 2022
 */
public class BinaryNode<Type> {

	private Type data;

	private BinaryNode<Type> leftChild;

	private BinaryNode<Type> rightChild;

	public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public BinaryNode(Type data) {
		this(data, null, null);
	}

	/**
	 * @return the node data
	 */
	public Type getData() {
		return data;
	}

	/**
	 * @param data - the node data to be set
	 */
	public void setData(Type data) {
		this.data = data;
	}

	/**
	 * @return reference to the left child node
	 */
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild - reference of the left child node to be set
	 */
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return reference to the right child node
	 */
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild - reference of the right child node to be set
	 */
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return reference to the leftmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if(getLeftChild() == null){
			return this;
		}
		else{
			return getLeftChild().getLeftmostNode();
		}

	}

	/**
	 * @return reference to the rightmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getRightmostNode() {
		if(getRightChild() == null){
			return this;
		}
		else{
			return getRightChild().getRightmostNode();
		}
	}

	/**
	 * @return the height of the binary tree rooted at this node
	 *
	 * The height of a tree is the length of the longest path to a leaf node.
	 *
	 */
	public int height() {
		int lDepth = -1;
		int rDepth = -1;
		//recursively call the height method to find the left sides depth
		if(getLeftChild() != null)
			lDepth = getLeftChild().height();
		//recursively call the height method to find the right sides depth
		if(getRightChild() != null)
			rDepth = getRightChild().height();
		// determine which is larger and return that value
		if (lDepth > rDepth)
			return (lDepth + 1);
		else
			return (rDepth + 1);
	}
}
