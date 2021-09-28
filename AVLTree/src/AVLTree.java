import java.util.*;
//import java.io.*;

public class AVLTree {
	private Node root;
	AVLTree() {
		this.root = null;
	}
	
	private Node insert(Node node, String data){
		if(node == null)
		{
			Node newnode = new Node(data);
			
			if(this.root == null)
				this.root = newnode;
			
			return newnode;
		}
		
		if(node.getData().compareTo(data) > 0) //data�� ���������� ������ �ִ� ���̽�
			node.setLeftNode(this.insert(node.getLeftNode(), data));
		
		else if(node.getData().compareTo(data) < 0)	//data�� ���������� �ڿ� �ִ� ���̽�
			node.setRightNode(this.insert(node.getRightNode(), data));

		else	//data�� ������ ���� x
			return null;
		
		//����� ���� ���
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1);
		
		//�뷱�� ���� ����
		int BF = getBF(node);
		
		Node temp;
		
		//�������� ���� ���̽�
		if(BF > 1)	
		{
			//LL ���� �������� ���� ���̽�
			if(node.getLeftNode().getData().compareTo(data) > 0)
			{
				//�������� �ѹ� ȸ��
				temp = rightRotation(node);
				this.root = temp;
				return temp;
						
			}
			
			//LR ���� ���������� ���� ���̽�
			else 
			{
				//���� ����� rightchild�� �������� ���� ȸ�� ��, �ٽ� �ڱ� �������� ����ȸ��
				node.setLeftNode(leftRotation(node.getLeftNode()));
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}
		}	
		
		//�������� ���� ���̽�
		if(BF < -1)
		{
			//RR ������ ���������� ���� ���̽�
			if(node.getRightNode().getData().compareTo(data) < 0)
			{
				//�������� �ѹ� ȸ��
				temp =  leftRotation(node);
				this.root = temp;
				return temp;
			}
			
			//RL ������ �������� ���� ���̽�
			else
			{
				//���� ��� left child�� �������� �������� �ѹ� ȸ���� �ٽ� �ڱ� �������� �������� ȸ��
				node.setRightNode(rightRotation(node.getRightNode()));
				temp = leftRotation(node);
				this.root = temp;
				return temp;
			}
		}
		
		//����� ���� ��Ʈ��� ����
		this.root = node;
		
		return node;
	}
	void insert(String data){
		this.insert(this.root, data);
		System.out.print(data + "����\n");
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
	
	private Node Delete(Node node, String data) {
		//�켱 ����Ʈ���� ������ ����
		
		//���� �����Ͱ� ���� ��
		if(node == null)
			return node;
		
		//���������� �տ� �ִ� ���̽��� ��������
		if(node.getData().compareTo(data) > 0)
			node.setLeftNode(Delete(node.getLeftNode(), data));
		
		//���������� �ڿ� �ִ� ���̽��� ����
		else if(node.getData().compareTo(data) < 0)
			node.setRightNode(Delete(node.getRightNode(), data));
		
		//���� �����͸� ���� ����
		else
		{
			//���� �� ����� ���� ���� ��� �ΰ��� �ִ� ���̽�
			//���� ����Ʈ������ ���� ���� ���� ����
			if(node.getLeftNode() != null && node.getRightNode() != null)
			{
				//���� ����Ʈ���� ���� ���� ���� ����
				Node temp = node.getRightNode();
				while(temp.getLeftNode() != null)
					temp = temp.getLeftNode();
				
				//�� ���� ���� ��尪����
				node.setData(temp.getData());
				
				//�� �� ����Ʈ�� �Ʒ��� ���� ���� ���� ���� ���� ����
				node.setRightNode(Delete(node.getRightNode(), temp.getData()));
			}
			
			//�ڽ� �� �ϳ� �̻��� null�� ��� �����ϴ� ���̽�
			else if(node.getRightNode() == null || node.getLeftNode()== null)
			{
				Node temp = null;
				
				//�ϳ��� ���� ���̽� && ������ ����
				if(node.getLeftNode() == null)
				{
					temp = node.getRightNode(); //�ӽ� ��带 ���� �ڽ�����
					node = temp;			//���� ��带 ���� �ڽ����� �ٲ�(���� ���� ����)
				}
				//�ϳ��� ���� ���̽� && ������ ����
				else 
				{
					temp = node.getLeftNode();	//�ݴ�
					node = temp;
				}
				//�Ѵ� null�� ���̽�
				//��常 ����
				if(temp == null)
					node = null;
					
				temp = null;
			}
		}
			
		if(node == null)
			return node;
			
		//������ ������ ���� ���� �ٽ�
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode()))+1);
		
		//���� �ٽ� ���뷱��
		int BF = getBF(node);	
		Node temp = null;
		
		//�������� ���� ���̽�
		if(BF > 1)	
		{
			//LL ���� �������� ���� ���̽�
			if(getBF(node.getLeftNode()) >= 0)
			{
				//�������� �ѹ� ȸ��
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}
			
			//LR ���� ���������� ���� ���̽�
			else 
			{
				//���� ����� rightchild�� �������� ���� ȸ�� ��, �ٽ� �ڱ� �������� ����ȸ��
				node.setLeftNode(leftRotation(node.getLeftNode()));
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}	
		}	
			
		//�������� ���� ���̽�
		if(BF < -1)
		{
			//RR ������ ���������� ���� ���̽�
			if(getBF(node.getRightNode()) <= 0)
			{
				//�������� �ѹ� ȸ��
				temp = leftRotation(node);
				this.root = temp;
				return temp;
			}
			
			//RL ������ �������� ���� ���̽�
			else
			{
				//���� ��� left child�� �������� �������� �ѹ� ȸ���� �ٽ� �ڱ� �������� �������� ȸ��
				node.setRightNode(rightRotation(node.getRightNode()));
				temp =  leftRotation(node);
				this.root  = temp;
				return temp;
			}
		}
		//��Ʈ ����
		this.root = node;
		
		return node;
	}
	
	void Delete(String data) {
		this.Delete(this.root ,data);
	}
	
	void PreOrder() {
		PreOrder(this.root);
	}
	
	private void PreOrder(Node curnode){
		if(curnode == null)
			return;
		
		System.out.print(curnode.getData() + " ");
		PreOrder(curnode.getLeftNode());
		PreOrder(curnode.getRightNode());
	}
	
	private int getHeight(Node node) {
		if(node == null)
			return 0;
		
		return node.getHeight();
	}
	/*------------------------��� Ŭ����---------------------------*/
	public class Node{
		private Node left;
		private Node right;
		String data;
		int height;
		
		Node(String data){
			this.left = this.right = null;
			this.data = data;
			this.height = 1;
		}
		/*void setParent(Node node) {
			this.parent = node;
		}
		Node getParent() {
			return this.parent;
		}*/
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

		AVL.insert("01");
		AVL.insert("02");
		AVL.insert("03");
		AVL.insert("07");
		AVL.insert("08");
		AVL.insert("04");
		AVL.insert("05");
		AVL.insert("06");
		
		AVL.Delete("04");
		AVL.Delete("07");
		AVL.Delete("06");
		AVL.Delete("08");
		AVL.Delete("05");
		AVL.PreOrder();
		
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
