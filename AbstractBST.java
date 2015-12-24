import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractBST { // non-duplicate keys

	public class BinaryNode {
		Integer key;
		String id;
		BinaryNode left, right, parent;
		BinaryNode(int k) {
			key = k;
		}
		BinaryNode() {
			key = null;
		}
		public void setID(String k){
			this.id = k;
		}
		public void setLeft(BinaryNode left){
			this.left = left;
			this.left.id = this.id+"0";
		}
		public void setRight(BinaryNode right){
			this.right = right;
			this.right.id = this.id+"1";
		}
	}

	BinaryNode root = null;
	protected int counter;

	// returns a node
	//     null if the tree is empty
	//     node if key matches
	//     potential parent if key doesn't match
	//         (convenient for insertion)
	public BinaryNode search(int k){
		if (root == null) return null;
		BinaryNode u = root;
		while (k != u.key) {
		   if (k < u.key) {
		       if (u.left == null) 
		       		return u;
		       else u = u.left;
		   } 
		   else {
		       if (u.right == null) 
		       		return u;
		       else u = u.right;
		   }
		}
		return u;
	}

	// adds a new key to the tree (if possible)
	public boolean add(int k){
		if (root == null) {
		    root = new BinaryNode(k);
		    return true;
		}
		BinaryNode u = search(k);
		if (k == u.key) { // element exists; do not add
		    return false;
		}
		if (k < u.key) {
			BinaryNode v = new BinaryNode(k);
			v.parent = u;
		    u.left = v;
		} else {
			BinaryNode v = new BinaryNode(k);
			v.parent = u;
		    u.right = v;
		}
		return true;
	}

	public boolean remove(int k) {
		
		BinaryNode u = search(k);
		if (u == null || u.key != k) return false;

		BinaryNode nodeToCut = u;
		
		if (u.left != null && u.right != null) { // tricky case
			nodeToCut = min(u.right);
			u.key = nodeToCut.key; // save nodeToCut's key in u
		}
		cut(nodeToCut);
		return true;
	}

	public void cut(BinaryNode v) {
		BinaryNode childOfV = null;
		if (v.left != null) childOfV = v.left;
		if (v.right != null) childOfV = v.right;
		if (v.parent != null) {
			if (v.parent.left == v) { // v is a left child
				v.parent.left = childOfV;
			} else { // v is right child
				v.parent.right = childOfV;
			}
			if (childOfV != null) {
				childOfV.parent = v.parent;
			}
		} 
		else { // deleting root
			if (childOfV == null) {
				root = null;
			} else {
				root = childOfV;
			}
		}
	}

	public BinaryNode min(BinaryNode node) {
		BinaryNode returnNode = node;
		while (returnNode.left != null) {
			returnNode = returnNode.left;
		}
		return returnNode;
	}

	public void print(BinaryNode node) {
		if (node == null) {
			System.out.printf("%s","null");
		} else {
			System.out.printf("%d", node.key);
		}
	}

	public void inorder(BinaryNode u){
		if (u == null) {
			return;
		}
		inorder(u.left);
		System.out.printf(" %d", u.key);
		inorder(u.right);
	}

	public void preorder(BinaryNode u){
		if (u == null) return;
		System.out.printf(" %d", u.key);
		preorder(u.left);
		preorder(u.right);
	}
	public void postorder(BinaryNode u){
		if (u == null) return;
		postorder(u.left);
		postorder(u.right);
		System.out.printf(" %d", u.key);
	}

	public void inorder(){
		inorder(root);
		System.out.printf("\n");
	}

	public void preorder(){
		preorder(root);
		System.out.printf("\n");
	}

	public void postorder(){
		postorder(root);
		System.out.printf("\n");
	}

	public void breadthfirst() {
		Queue<BinaryNode> q = new LinkedList<BinaryNode>();
		if (this.root == null){
			return;
		}
		q.add(this.root);
		
		while (!q.isEmpty()) {
			
			BinaryNode n = (BinaryNode) q.remove();
			
			if (n.left != null){
				q.add(n.left);
			}

			if (n.right != null){
				q.add(n.right);
			}
		
		}
		System.out.printf("\n");
	}
	
	abstract public int height();
	abstract public void displaylevels();
	abstract public void displaytree();

	public static void main(String[] args) throws CloneNotSupportedException {

	}
}
