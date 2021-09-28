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
		
		if(node.getData().compareTo(data) > 0) //data가 사전적으로 앞으로 있는 케이스
			node.setLeftNode(this.insert(node.getLeftNode(), data));
		
		else if(node.getData().compareTo(data) < 0)	//data가 사전적으로 뒤에 있는 케이스
			node.setRightNode(this.insert(node.getRightNode(), data));

		else	//data가 같으면 삽입 x
			return null;
		
		//노드의 높이 계산
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1);
		
		//밸런스 팩터 연산
		int BF = getBF(node);
		
		Node temp;
		
		//좌측으로 뻗은 케이스
		if(BF > 1)	
		{
			//LL 왼쪽 왼쪽으로 뻗은 케이스
			if(node.getLeftNode().getData().compareTo(data) > 0)
			{
				//우측으로 한번 회전
				temp = rightRotation(node);
				this.root = temp;
				return temp;
						
			}
			
			//LR 왼쪽 오른쪽으로 뻗은 케이스
			else 
			{
				//기존 노드의 rightchild를 기준으로 우측 회전 후, 다시 자기 기준으로 좌측회전
				node.setLeftNode(leftRotation(node.getLeftNode()));
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}
		}	
		
		//우측으로 뻗은 케이스
		if(BF < -1)
		{
			//RR 오른쪽 오른쪽으로 뻗은 케이스
			if(node.getRightNode().getData().compareTo(data) < 0)
			{
				//좌측으로 한번 회전
				temp =  leftRotation(node);
				this.root = temp;
				return temp;
			}
			
			//RL 오른쪽 왼쪽으로 뻗은 케이스
			else
			{
				//기존 노드 left child를 기준으로 좌측으로 한번 회전후 다시 자기 기준으로 우측으로 회전
				node.setRightNode(rightRotation(node.getRightNode()));
				temp = leftRotation(node);
				this.root = temp;
				return temp;
			}
		}
		
		//재귀의 끝에 루트노드 갱신
		this.root = node;
		
		return node;
	}
	void insert(String data){
		this.insert(this.root, data);
		System.out.print(data + "삽입\n");
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
	
	private Node Delete(Node node, String data) {
		//우선 이진트리의 삭제를 진행
		
		//같은 데이터가 없을 때
		if(node == null)
			return node;
		
		//사전적으로 앞에 있는 케이스면 좌측으로
		if(node.getData().compareTo(data) > 0)
			node.setLeftNode(Delete(node.getLeftNode(), data));
		
		//사전적으로 뒤에 있는 케이스는 우측
		else if(node.getData().compareTo(data) < 0)
			node.setRightNode(Delete(node.getRightNode(), data));
		
		//같은 데이터면 삭제 실행
		else
		{
			//삭제 시 노드의 좌측 우측 노드 두개다 있는 케이스
			//우측 서브트리에서 가장 작은 값을 위로
			if(node.getLeftNode() != null && node.getRightNode() != null)
			{
				//우측 서브트리의 가장 작은 값을 구함
				Node temp = node.getRightNode();
				while(temp.getLeftNode() != null)
					temp = temp.getLeftNode();
				
				//그 값을 현재 노드값으로
				node.setData(temp.getData());
				
				//그 후 서브트리 아래의 가장 작은 값을 삭제 연산 진행
				node.setRightNode(Delete(node.getRightNode(), temp.getData()));
			}
			
			//자식 중 하나 이상이 null인 노드 삭제하는 케이스
			else if(node.getRightNode() == null || node.getLeftNode()== null)
			{
				Node temp = null;
				
				//하나만 널인 케이스 && 좌측이 널인
				if(node.getLeftNode() == null)
				{
					temp = node.getRightNode(); //임시 노드를 우측 자식으로
					node = temp;			//현재 노드를 우측 자식으로 바꿈(현재 노드는 삭제)
				}
				//하나만 널인 케이스 && 우측이 널인
				else 
				{
					temp = node.getLeftNode();	//반대
					node = temp;
				}
				//둘다 null인 케이스
				//노드만 삭제
				if(temp == null)
					node = null;
					
				temp = null;
			}
		}
			
		if(node == null)
			return node;
			
		//삭제가 끝나면 높이 연산 다시
		node.setHeight(Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode()))+1);
		
		//그후 다시 리밸런싱
		int BF = getBF(node);	
		Node temp = null;
		
		//좌측으로 뻗은 케이스
		if(BF > 1)	
		{
			//LL 왼쪽 왼쪽으로 뻗은 케이스
			if(getBF(node.getLeftNode()) >= 0)
			{
				//우측으로 한번 회전
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}
			
			//LR 왼쪽 오른쪽으로 뻗은 케이스
			else 
			{
				//기존 노드의 rightchild를 기준으로 우측 회전 후, 다시 자기 기준으로 좌측회전
				node.setLeftNode(leftRotation(node.getLeftNode()));
				temp = rightRotation(node);
				this.root = temp;
				return temp;
			}	
		}	
			
		//우측으로 뻗은 케이스
		if(BF < -1)
		{
			//RR 오른쪽 오른쪽으로 뻗은 케이스
			if(getBF(node.getRightNode()) <= 0)
			{
				//좌측으로 한번 회전
				temp = leftRotation(node);
				this.root = temp;
				return temp;
			}
			
			//RL 오른쪽 왼쪽으로 뻗은 케이스
			else
			{
				//기존 노드 left child를 기준으로 좌측으로 한번 회전후 다시 자기 기준으로 우측으로 회전
				node.setRightNode(rightRotation(node.getRightNode()));
				temp =  leftRotation(node);
				this.root  = temp;
				return temp;
			}
		}
		//루트 갱신
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
	/*------------------------노드 클래스---------------------------*/
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
	/*--------------------Class Main---------------------------*/
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
