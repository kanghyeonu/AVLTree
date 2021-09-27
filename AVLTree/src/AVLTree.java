import java.util.*;
//import java.io.*;

public class AVLTree {
	private Node root;
	AVLTree() {
		this.root = null;
	}
	
	Node insert(Node node, String data){
		if(node == null)
		{
			Node newnode = new Node(data);
			this.setRoot(newnode);
			return newnode;
		}
		
		if(node.getData().compareTo(data) > 0) //data�� ���������� ������ �ִ� ���̽�
			node.setLeftNode(this.insert(node.getLeftNode(), data));
		
		else if(node.getData().compareTo(data) < 0)	//data�� ���������� �ڿ� �ִ� ���̽�
			node.setRightNode(this.insert(node.getRightNode(), data));
			
		else	//data�� ������ ���� x
			return null;
		
		//������� ����� parent�� ���� ���
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())+ 1));
		
		//�뷱�� ���� ����
		int BF = getBF(node);
		
		//�������� ���� ���̽�
		if(BF > 1)	
		{
			//LL ���� �������� ���� ���̽�
			if(node.getLeftNode().getData().compareTo(data) > 0)
			{
				//�������� �ѹ� ȸ��
				return rightRotation(node);
			}
			
			//LR ���� ���������� ���� ���̽�
			else 
			{
				//���� ����� rightchild�� �������� ���� ȸ�� ��, �ٽ� �ڱ� �������� ����ȸ��
				node.setRightNode(leftRotation(node.getRightNode()));
				return rightRotation(node);
			}
		}	
		
		//�������� ���� ���̽�
		if(BF < -1)
		{
			//RR ������ ���������� ���� ���̽�
			if(node.getRightNode().getData().compareTo(data) < 0)
			{
				//�������� �ѹ� ȸ��
				return leftRotation(node);
			}
			
			//RL ������ �������� ���� ���̽�
			else
			{
				//���� ��� left child�� �������� �������� �ѹ� ȸ���� �ٽ� �ڱ� �������� �������� ȸ��
				node.setLeftNode(rightRotation(node.getLeftNode()));
				return leftRotation(node);
			}
		}
		
		this.root = renewRoot(root);
		
		return node;
	}
	
	private int getBF(Node node) {
		if(node == null)
			return 0;
		
		return getHeight(node.getLeftNode()) - getHeight(node.getRightNode());
	}
	
	private Node rightRotation(Node node) { //�Ķ���� -> ���� ȸ������ ����� ���� child�� �� ���  
		Node a = node.getLeftNode();  // ���� ȸ������ parent�� �Ǿ� ���ϵ� ���
		Node b = a.getRightNode();	//���� ȸ������ null�̰ų� a ����� ���� ����Ʈ��
		
		a.setRightNode(node);	//a ���� ���� a�� parent �θ� ������
		node.setLeftNode(b);	//a�� ���� ����Ʈ���� a�� parent���� �����Ƿ� �ٽ� parent�� ���� ����Ʈ���� ��ȯ
		
		//���� ����
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1); //������ ����� ����
		a.setHeight(Math.max(getHeight(a.getLeftNode()),getHeight(a.getRightNode()) + 1)); //���� �θ� �� ���
		
		return a;	//�θ��带 ��ȯ
	}
	
	//rightrotation�̶� ���� ���⸸ �ݴ�
	private Node leftRotation(Node node) {	
		Node a = node.getRightNode();
		Node b = a.getLeftNode();
		
		//ȸ��
		a.setLeftNode(node);
		node.setRightNode(b);
		//�� ��,���� ���
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1);
		a.setHeight(Math.max(getHeight(a.getLeftNode()), getHeight(a.getRightNode()) + 1)); 
		
		return a;
	}
	
	void PreOrder(Node curnode){
		if(curnode == null)
			return;
		
		System.out.print(curnode.getData());
		PreOrder(curnode.getLeftNode());
		PreOrder(curnode.getRightNode());
	}
	void setRoot(Node node) {
		this.root = node;
	}
	
	Node getRoot() {
		return this.root;
	}
	
	private Node renewRoot(Node node) {
		Node new_root = node;
		while(new_root.getParent() != null)
		{
			new_root = new_root.getParent();
			System.out.print(new_root.getData());
		}
		
		return new_root;
	}
	
	private int getHeight(Node node) {
		if(node == null)
			return 0;
		
		return node.getHeight();
	}
	/*------------------------��� Ŭ����---------------------------*/
	public class Node{
		private Node parent;
		private Node left;
		private Node right;
		String data;
		int height;
		
		Node(String data){
			this.left = this.right = this.parent = null;
			this.data = data;
			this.height = 1;
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
		void setHeight(int h) {
			this.height = h;
		}
		int getHeight() {
			return this.height;
		}
	}
	/*--------------------��� Ŭ����---------------------------*/
	public static void main(String[] args) {
		AVLTree AVL = new AVLTree();

		AVL.insert(AVL.getRoot(), "c");
		AVL.insert(AVL.getRoot(), "b");
		AVL.insert(AVL.getRoot(), "f");
		AVL.insert(AVL.getRoot(), "k");
		AVL.insert(AVL.getRoot(), "a");
		
		AVL.PreOrder(AVL.getRoot());
		
	}

}

/*while(true)
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
		return; //�ߺ�Ű�� ���ؼ��� ���� x*/
