package SearchEngine;

public class AVLTree<K extends Comparable<K>, T> {

	private AVLNode<K, T> root;
	private AVLNode<K, T> current;
	private int count;

	public AVLTree() {
		root = current = null;
		count = 0;
	}

	public boolean empty() {
		return root == null;
	}

	public int size() {
		return count;
	}

	public T retrieve() {
		T data = null;
		if (current != null)
			data = current.data;
		return data;
	}

	public void update(T e) {
		if (current != null)
			current.data = e;
	}

	private T searchTree(AVLNode<K, T> node, K key) {
		if (node == null)
			return null;
		else if (node.key.compareTo(key) == 0) {
			current = node;
			return node.data;
		} else if (node.key.compareTo(key) > 0)
			return searchTree(node.left, key);
		else
			return searchTree(node.right, key);
	}

	private void updateBalance(AVLNode<K, T> node) {
		if (node.bf < -1 || node.bf > 1) {
			rebalance(node);
			return;
		}

		if (node.parent != null) {
			if (node == node.parent.left) {
				node.parent.bf -= 1;
			}

			if (node == node.parent.right) {
				node.parent.bf += 1;
			}

			if (node.parent.bf != 0) {
				updateBalance(node.parent);
			}
		}
	}

	void rebalance(AVLNode<K, T> node) {
		if (node.bf > 0) {
			if (node.right.bf < 0) {
				rightRotate(node.right);
				leftRotate(node);
			} else {
				leftRotate(node);
			}
		} else if (node.bf < 0) {
			if (node.left.bf > 0) {
				leftRotate(node.left);
				rightRotate(node);
			} else {
				rightRotate(node);
			}
		}
	}

	public boolean find(K key) {
		T data = searchTree(this.root, key);
		if (data != null)
			return true;
		return false;
	}

	void leftRotate(AVLNode<K, T> x) {
		AVLNode<K, T> y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}

		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;

		x.bf = x.bf - 1 - Math.max(0, y.bf);
		y.bf = y.bf - 1 + Math.min(0, x.bf);
	}

	void rightRotate(AVLNode<K, T> x) {
		AVLNode<K, T> y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;

		x.bf = x.bf + 1 - Math.min(0, y.bf);
		y.bf = y.bf + 1 + Math.max(0, x.bf);
	}

	public boolean insert(K key, T data) {
		AVLNode<K, T> node = new AVLNode<K, T>(key, data);

		AVLNode<K, T> p = null;
		AVLNode<K, T> current = this.root;

		while (current != null) {
			p = current;
			if (node.key.compareTo(current.key) == 0) {
				return false;
			} else if (node.key.compareTo(current.key) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		node.parent = p;
		if (p == null) {
			root = node;
			current = node;
		} else if (node.key.compareTo(p.key) < 0) {
			p.left = node;
		} else {
			p.right = node;
		}
		count++;

		updateBalance(node);
		return true;
	}

	public T retrieveDataWithWord(K key) {
		T data = searchTree(this.root, key);

		return data;
	}

}
