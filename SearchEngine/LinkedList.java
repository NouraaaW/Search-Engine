package SearchEngine;

public class LinkedList<T> {

	private Node<T> head;
	private Node<T> current;
	int size;

	public LinkedList() {
		head = current = null;
		size = 0;
	}

	public boolean empty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	public boolean current() {
		return current != null;
	}

	public void findFirst() {
		current = head;
	}

	public void insert(T data) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T>(data);
		} else {
			tmp = current.next;
			current.next = new Node<T>(data);
			current = current.next;
			current.next = tmp;
		}
		size++;
	}

	public boolean find(T data) {

		Node<T> tmp = head;
		while (tmp != null) {
			if (tmp.data.equals(data)) {
				current = tmp;
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}

	public void findNext() {
		current = current.next;
	}

	public T retrieve() {

		return current.data;
	}

	public Boolean hasNext() {
		if (current.next != null)
			return true;

		return false;

	}
	
	public Node<T> getHead() {
		return head;
	}

	
	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void setCurrent(Node<T> current) {
		this.current = current;
	}



}
