package source;

public class Stack<T> implements StackInterface<T> {
	
	@SuppressWarnings("hiding")
	public class Node<T>{
		
		private T data;
		private Node<T> next;
		
		public Node(T newEntry, Node<T> topNode) {
			data = newEntry;
			next = topNode;
		}
		public Node<T> getNextNode() {
			return next;
		}
		public T getNodeData() {
			return data;
		}
		
	}
	
	Node<T> topNode;
	
	public Stack() {
		topNode = null;
	}

	public void push(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry, topNode);
		topNode = newNode;
	}
	public T pop() {
		T top = peek();
		topNode = topNode.getNextNode();
		return top;
	}
	public T peek() {
		return topNode.getNodeData();
	}
	public boolean isEmpty() {
		return topNode == null;
	}
	public void clear() {
		topNode = null;
	}

}
