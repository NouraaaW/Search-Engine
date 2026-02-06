package SearchEngine;

class AVLNode<K extends Comparable<K>, T> {
	public K key;
	public T data;
	AVLNode<K, T> parent;
	AVLNode<K, T> left;
	AVLNode<K, T> right;
	int bf;

	public AVLNode() {
		this.key = null;
		this.data = null;
		this.parent = this.left = this.right = null;
		this.bf = 0;
	}

	public AVLNode(K key, T data) {
		this.key = key;
		this.data = data;
		this.parent = this.left = this.right = null;
		this.bf = 0;
	}

	public AVLNode(K key, T data, AVLNode<K, T> p, AVLNode<K, T> l, AVLNode<K, T> r) {
		this.key = key;
		this.data = data;
		left = l;
		right = r;
		parent = p;
		bf = 0;
	}

	public AVLNode<K, T> getLeft() {
		return left;
	}

	public AVLNode<K, T> getRight() {
		return right;
	}

	public T getData() {
		return data;
	}

}
