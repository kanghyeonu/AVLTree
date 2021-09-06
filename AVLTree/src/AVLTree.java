
public class AVLTree {
	private Node root;
	AVLTree() {
		this.root = null;
	}
	
	void insert(String data){
		Node insertnode = new Node(data);
		Node curnode = root;
		
		if( curnode == null) // AVL 트리의 첫 노드
		{
			this.root = insertnode;
			return;
		}
		
		while(true)
		{
			if(curnode.getData().compareTo(data) > 0) //data가 사전적으로 앞으로 있는 케이스
			{	
				if(curnode.getLeftNode() != null)	//다음 노드가 있다면 그 노드로 이동하여 비교
					curnode = curnode.getLeftNode();
				
				else	//다음 노드가 null이라면 해당 위치가 현재 노드가 들어갈 위치
				{
					curnode.setLeftNode(insertnode);
					insertnode.setParent(curnode);
					break;
				}
			}
			else if(curnode.getData().compareTo(data) < 0) //data가 사전적으로 뒤에있는 케이스
			{
				if(curnode.getRightNode() != null) //다음 노드가 있다면 그 노드로 이동하여 비교
					curnode = curnode.getRightNode();
				
				else	//다음 노드가 null이라면 해당 위치가 현재 노드가 들어갈 위치
				{
					curnode.setRightNode(insertnode);
					insertnode.setParent(curnode);
					break;
				}
			}
			else
				return; //중복키에 대해서는 삽입 x
		}
	}
	
	void PreOrder(Node curnode){
		if(curnode == null)
			return;
		
		System.out.print(curnode.getData());
		PreOrder(curnode.getLeftNode());
		PreOrder(curnode.getRightNode());
	}
	
	Node getRoot() {
		return this.root;
	}
	
	/*------------------------노드 클래스---------------------------*/
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
		AVLTree AVL = new AVLTree();
		
		AVL.insert("c");
		AVL.insert("r");
		AVL.insert("j");
		AVL.insert("h");
		AVL.insert("a");
		AVL.insert("b");
		
		AVL.PreOrder(AVL.getRoot());
		
	}

}
