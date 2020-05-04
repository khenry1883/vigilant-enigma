package BSTCode;

public class BinarySearchTree<K extends Comparable<K>, V> {

	private TreeNode<K, V> root;

	public BinarySearchTree() {
		root = null;
	}

	public void insertNode(K key, V value) {
		TreeNode<K, V> newNode = new TreeNode<K, V>(key, value);
		if (this.root == null) {
			root = newNode;
			System.out.println("Inserted as root");
		} else
			root.insert(key, value);
	}

	public void deleteNodeWithKey(K Key) {
		TreeNode<K, V> current = this.root;
		TreeNode<K, V> parent = this.root;
		boolean isLeftChild = false;

		if (this.root == null)
			return; // tree is empty

		while (current != null && current.getKey() != Key) {
			parent = current;
			if (Key.compareTo(current.getKey()) > 0) {
				current = current.getLeftChild();
				isLeftChild = true;
			} else {
				current = current.getRightChild();
				isLeftChild = false;
			}
		}
		if (current == null)
			return; // node to be deleted not found

		// this is case 1
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			if (current == root) {
				root = null; // no elements in tree now
			} else {
				if (isLeftChild)
					parent.setLeftChild(null);
				else
					parent.setRightChild(null);
			}
		}
		// This is case 2 broken down further into 2 separate cases
		else if (current.getRightChild() == null) {// current has left child
			if (current == root) {
				root = current.getLeftChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getLeftChild());
			} else {
				parent.setRightChild(current.getLeftChild());
			}
		} else if (current.getLeftChild() == null) {// current has right child
			if (current == root) {
				root = current.getRightChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getRightChild());
			} else {
				parent.setRightChild(current.getRightChild());
			}
		}
		// This is case 3 - Most complicated (node to be deleted has 2 children)
		else {
			TreeNode<K, V> successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.setLeftChild(successor);
			} else {
				parent.setRightChild(successor);
			}
			successor.setLeftChild(current.getLeftChild());
		}
	}

	private TreeNode<K, V> getSuccessor(TreeNode<K, V> node) {
		TreeNode<K, V> parentOfSuccessor = node;
		TreeNode<K, V> successor = node;
		TreeNode<K, V> current = node.getRightChild();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeftChild();
		}
		if (successor != node.getRightChild()) {
			parentOfSuccessor.setLeftChild(successor.getRightChild());
			successor.setRightChild(node.getRightChild());
		}
		return successor;
	}

	public void traverseInOrder() {
		if (this.root != null)
			this.root.traverseInOrder();
		System.out.println();
	}

	public void traversePostOrder() {
		if (this.root != null)
			this.root.traversePostOrder();
		System.out.println();
	}

	public void traversePreOrder() {
		if (this.root != null)
			this.root.traversePreOrder();
		System.out.println();
	}

	public void deleteMax() {
		if (this.root.getRightChild() != null) {
			deleteMax();
		}
		System.out.println("Node Deleted: " + this.root.getRightChild().toString());
		this.root.setRightChild(null);
	}

	public void deleteMin() {
		if (this.root.getLeftChild() != null) {
			deleteMin();
		}
		System.out.println("Node Deleted: " + this.root.getLeftChild().toString());
		this.root.setLeftChild(null);
	}

	public static void main(String[] args) {
		int[] sample = { 212, 580, 6, 7, 28, 84, 112, 434 };

		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();

		for (int x : sample) {
			bst.insertNode(x, x);
			System.out.println("Inserted:" + x);
		}

		bst.deleteNodeWithKey(84);
		bst.traverseInOrder();
		bst.traversePostOrder();
		bst.traversePreOrder();
		bst.deleteMax();
		bst.deleteMin();
	}
}
