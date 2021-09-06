
public class AVLTree {
	Node root;
	AVLTree(String data) {
		this.root = null;
	}
	
	void insert(String data){
		Node insertnode = new Node(data);
		Node rootnode = root;
		
		if( rootnode == null) // AVL 트리의 첫 노드
		{
			this.root = insertnode;
			return;
		}
		
		if(  )
		while()
	}
	
	
	
	
	/*--------------------노드 클래스---------------------------*/
	public class Node{
		private Node parent;
		private Node left;
		private Node right;
		String data;
		//int height;
		
		Node(String data){
			this.left = this.right = null;
			this.data = data;
			//this.height = -1;
		}
		void setParent(Node node) {
			this.parent = node;
		}
		Node getParent() {
			return this.parent;
		}
		void setLeftNode(Node node) { 
			this.left = node;
		}
		
		Node getLeftNode() {
			return this.left;
		}
		
		void setRightNode(Node node) {
			this.right = node;
		}
		
		Node getRightNode() {
			return this.right;
		}
		
		void setData(String data) {
			this.data = data;
		}
		
		String getData() {
			return this.data;
		}
		
		/*void setHeight(int height) {
			this.height = height;
		}
		
		int getHeight() {
			return this.height;
		}*/
	}
	/*--------------------노드 클래스---------------------------*/
	public static void main(String[] args) {
		

	}

}
