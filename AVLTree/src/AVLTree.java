
public class AVLTree {
	private Node root;
	AVLTree() {
		this.root = null;
	}
	
	void insert(String data){
		Node insertnode = new Node(data);
		Node curnode = root;
		
		if( curnode == null) // AVL Ʈ���� ù ���
		{
			this.root = insertnode;
			return;
		}
		
		while(true)
		{
			if(curnode.getData().compareTo(data) > 0) //data�� ���������� ������ �ִ� ���̽�
			{	
				if(curnode.getLeftNode() != null)	//���� ��尡 �ִٸ� �� ���� �̵��Ͽ� ��
					curnode = curnode.getLeftNode();
				
				else	//���� ��尡 null�̶�� �ش� ��ġ�� ���� ��尡 �� ��ġ
				{
					curnode.setLeftNode(insertnode);
					insertnode.setParent(curnode);
					break;
				}
			}
			else if(curnode.getData().compareTo(data) < 0) //data�� ���������� �ڿ��ִ� ���̽�
			{
				if(curnode.getRightNode() != null) //���� ��尡 �ִٸ� �� ���� �̵��Ͽ� ��
					curnode = curnode.getRightNode();
				
				else	//���� ��尡 null�̶�� �ش� ��ġ�� ���� ��尡 �� ��ġ
				{
					curnode.setRightNode(insertnode);
					insertnode.setParent(curnode);
					break;
				}
			}
			else
				return; //�ߺ�Ű�� ���ؼ��� ���� x
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
	
	/*------------------------��� Ŭ����---------------------------*/
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
	/*--------------------��� Ŭ����---------------------------*/
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
