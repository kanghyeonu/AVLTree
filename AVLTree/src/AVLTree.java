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
		
		if(node.getData().compareTo(data) > 0) //data가 사전적으로 앞으로 있는 케이스
			node.setLeftNode(this.insert(node.getLeftNode(), data));
		
		else if(node.getData().compareTo(data) < 0)	//data가 사전적으로 뒤에 있는 케이스
			node.setRightNode(this.insert(node.getRightNode(), data));
			
		else	//data가 같으면 삽입 x
			return null;
		
		//만들어진 노드의 parent의 높이 계산
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())+ 1));
		
		//밸런스 팩터 연산
		int BF = getBF(node);
		
		//좌측으로 뻗은 케이스
		if(BF > 1)	
		{
			//LL 왼쪽 왼쪽으로 뻗은 케이스
			if(node.getLeftNode().getData().compareTo(data) > 0)
			{
				//우측으로 한번 회전
				return rightRotation(node);
			}
			
			//LR 왼쪽 오른쪽으로 뻗은 케이스
			else 
			{
				//기존 노드의 rightchild를 기준으로 우측 회전 후, 다시 자기 기준으로 좌측회전
				node.setRightNode(leftRotation(node.getRightNode()));
				return rightRotation(node);
			}
		}	
		
		//우측으로 뻗은 케이스
		if(BF < -1)
		{
			//RR 오른쪽 오른쪽으로 뻗은 케이스
			if(node.getRightNode().getData().compareTo(data) < 0)
			{
				//좌측으로 한번 회전
				return leftRotation(node);
			}
			
			//RL 오른쪽 왼쪽으로 뻗은 케이스
			else
			{
				//기존 노드 left child를 기준으로 좌측으로 한번 회전후 다시 자기 기준으로 우측으로 회전
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
	
	private Node rightRotation(Node node) { //파라미터 -> 우측 회전에서 결과로 우측 child가 될 노드  
		Node a = node.getLeftNode();  // 우측 회전에서 parent가 되어 리턴될 노드
		Node b = a.getRightNode();	//우측 회전에서 null이거나 a 노드의 우측 서브트리
		
		a.setRightNode(node);	//a 우측 노드로 a의 parent 부모를 내리고
		node.setLeftNode(b);	//a의 우측 서브트리는 a의 parent보다 작으므로 다시 parent의 좌측 서브트리로 전환
		
		//높이 재계산
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1); //내려온 노드의 높이
		a.setHeight(Math.max(getHeight(a.getLeftNode()),getHeight(a.getRightNode()) + 1)); //새로 부모가 된 노드
		
		return a;	//부모노드를 반환
	}
	
	//rightrotation이랑 같음 방향만 반대
	private Node leftRotation(Node node) {	
		Node a = node.getRightNode();
		Node b = a.getLeftNode();
		
		//회전
		a.setLeftNode(node);
		node.setRightNode(b);
		//그 후,높이 계산
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
	/*------------------------노드 클래스---------------------------*/
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
	/*--------------------노드 클래스---------------------------*/
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
		return; //중복키에 대해서는 삽입 x*/
