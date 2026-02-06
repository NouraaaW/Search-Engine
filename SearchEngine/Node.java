package SearchEngine;

class Node<T> {
	public T data;
	public Node<T> next;

	public Node() {
		data = null;
		next = null;
	}

	public Node(T data) {
		this.data = data;
		next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}
