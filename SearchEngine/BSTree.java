package SearchEngine;

public class BSTree<K extends Comparable<K>, T> {

	BSTNode<K, T> root;
	BSTNode<K, T> current;
	int count;

	public BSTree() {
		root = current = null;
		count = 0;
	}

	public int size() {
		return count;
	}

	public boolean empty() {
		return root == null;
	}

	public T retrieve() {
		T data = null;
		if (current != null)
			data = current.data;
		return data;
	}

	public T retrieveDataWithWord(K key) {
		T data = search(this.root, key);

		return data;
	}

	public void update(T e) {
		if (current != null)
			current.data = e;
	}

	public boolean find(K key) {
		BSTNode<K, T> p = root;

		if (empty())
			return false;

		while (p != null) {
			if (p.key.compareTo(key) == 0) {
				current = p;
				return true;
			} else if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		return false;
	}

	private T search(BSTNode<K, T> node, K key) {
		if (node == null)
			return null;
		else if (node.key.compareTo(key) == 0) {
			current = node;
			return node.data;
		} else if (node.key.compareTo(key) > 0)
			return search(node.left, key);
		else
			return search(node.right, key);
	}

	public boolean insert(K key, T data) {

		if (empty()) {
			current = root = new BSTNode<K, T>(key, data);
			count++;
			return true;
		}
		BSTNode<K, T> par = null;
		BSTNode<K, T> child = root;

		while (child != null) {
			if (child.key.compareTo(key) == 0) {
				return false;
			} else if (key.compareTo(child.key) < 0) {
				par = child;
				child = child.left;
			} else {
				par = child;
				child = child.right;
			}
		}

		if (key.compareTo(par.key) < 0) {
			par.left = new BSTNode<K, T>(key, data);
			current = par.left;
		}

		else {
			par.right = new BSTNode<K, T>(key, data);
			current = par.right;
		}
		count++;
		return true;
	}

}
