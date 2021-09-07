import java.util.*;
//import java.io.*;

public class AVLTree {
	private Node root;
	AVLTree() {
		this.root = null;
	}
	
	Node insert(Node node, String data){
		if(node == null)
			return (new Node(data));
		
		if(node.getData().compareTo(data) > 0) //data가 사전적으로 앞으로 있는 케이스
			node.setLeftNode(insert(node.getLeftNode(), data));
		
		else if(node.getData().compareTo(data) < 0)	//data가 사전적으로 뒤에 있는 케이스
			node.setRightNode(insert(node.getRightNode(), data));
			
		else	//data가 같으면 삽입 x
			return null;
		
		//노드의 높이 계산
		node.setHeight(Math.max(node.getLeftNode().getHeight(), node.getRightNode().getHeight()) + 1);
		
		//밸런스 팩터 연산
		int BF = getBF(node);
		
		//LL 좌측으로 뻗은 케이스
		if(BF > 1 && node.getData().compareTo(data) > 0)	
		{
			//LL이므로 회전은 우측 회전 한번
			node.getLeftNode().setRightNode(node);
			node.setParent(node.getLeftNode());
		}
		//LR 좌측우측으로 뻗은 케이스
		if(BF > 1 && node.getData().compareTo(data) < 0)
		{
			//LR에서는 왼쪽 오른쪽 회전 한번씩
			node.setLeftNode(node.getLeftNode().getRightNode()); //왼쪽으로 회전
		}
		//RL 우측좌측으로 뻗은 케이스
		if(BF < -1 && node.getData().compareTo(data) > 0)
		{
			//LR에서는 오른쪽 왼쪽 회전 한번씩
		}
		//RR 우측우측으로 뻗은 케이스
		if(BF < -1 && node.getData().compareTo(data) < 0)
		{
			//RR이므로 죄측으로 회전 한번
			node.getRightNode().setLeftNode(node);
			node.setParent(node.getRightNode());
		}
		
		return node;
	}
	
	int getBF(Node node) {
		if(node == null)
			return 0;
		
		return node.getLeftNode().getHeight() - node.getRightNode().getHeight();
	}
	
	int getHeight(Node node) {
		if(node == null)
			return 0;
		
		return node.getHeight();
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
	
	/*------------------------노드 클래스---------------------------*/
	public class Node{
		private Node parent;
		private Node left;
		private Node right;
		String data;
		int height;
		
		Node(String data){
			this.left = this.right = null;
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
