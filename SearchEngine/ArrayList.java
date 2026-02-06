package SearchEngine;

public class ArrayList<T> {
	private T[] elements;
	private int size;
	private int maxsize;
	private int current;

	public ArrayList(int maxsize) {
		this.maxsize = maxsize;
		elements = (T[]) new Object[maxsize];
		size = 0;
		current = -1;
	}

	public void add(T element) {
		if (!isFull())
			elements[size++] = element;
	}

	public T get(int index) {
		if (!isEmpty())
			return elements[index];

		return null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == maxsize;
	}

	public void findFirst() {
		if (!isEmpty())
			current = 0;

	}

	public void findNext() {
		if (current + 1 < size)
			current++;
		else
			current = -1;

	}

	public int getDocID() {
		return current;
	}

	public boolean isCurrentNull() {
		return current == -1;
	}

}
