public class MyLinkedList {
	
	private ListNode head;
	private int size;
	
	//inner class for ListNode
	private class ListNode {
		private Object data;
		private ListNode next;
		private ListNode(Object d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public MyLinkedList() {
		this.head = new ListNode(null); //with a dummy head node
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add Object e to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method in your source code.
	public void addFirst(Object e)
	{
		ListNode node = new ListNode(e);
		node.next = head.next;
		head.next = node;
		size++;
	}
	
	// Remove(cut out) the first data node(the node succeeding the dummy node) 
	//       in this list, then returns the data in the node removed.
	// If the size of this list is zero, throws an Exception.
	public Object removeFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("LinkedList is empty!");
		}
		Object temp = this.head.next.data; //stores data from the first node
		if (this.size == 1) {
			this.head.next = null;
		}else {
			this.head.next = this.head.next.next;
		}
		this.size--;

		return temp; //change this as you need.
	}
	
	// Returns true if this list contains the specified element o. 
	// More formally, returns true if and only if this list contains at least one element e 
	// such that (o==null ? e==null : o.equals(e)).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean contains(Object o) {
		ListNode prev = this.head, cur = this.head.next;
		int i = 0;
		while(i < size) {
			if(cur.data == null && o == null) { //Safely checks data without causing a NullPointerException
				return true;
			}
			if(cur.data.equals(o)) {
				return true;
			}
			prev = cur;
			cur = cur.next;
			i++;
		}
		return false; //Linked list does not contain data equal to Object o
	}
	
	// Removes the first occurrence of the specified element o from this list and returns true, if it is present. 
	// If this list does not contain the element o, it is unchanged and the method returns false.
	// More formally, removes the element o with the lowest index i such that 
	//     (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean remove(Object o) {
		ListNode prev = this.head, cur = this.head.next;
		int i = 0;
		while(i < size) {
			if(cur.data == null && o == null) { //Safely removes null data without causing a NullPointerException
				prev.next = cur.next;
				this.size--;
				return true;
			}
			if(cur.data.equals(o)) {
				prev.next = cur.next;
				this.size--;
				return true;
			}

			prev = cur;
			cur = cur.next;
			i++;
		}

		return false; //no data was removed
	}

	// Removes all copies of o from this linked list.
	// You have to handle the cases where Object o may 
	//        have zero, one or multiple copies in this list.
	// If any element has been removed from this list, returns true. 
	//        Otherwise returns false.
	// Note: be careful when multiple copies of Object o are stored
	//        in consecutive(adjacent) list nodes.
	//        E.g. []->["A"]->["A"]->["B"]. 
	//        Be careful when removing all "A"s in this example.
	// Note: This list may contains zero, one or multiple copies of null data elements.
	public boolean removeAllCopies(Object o) { //passed test
		boolean dataWasRemoved = this.remove(o);
		while(this.remove(o)){
			//empty body
		}
		return dataWasRemoved;
	}
	
	// Insert data elements from linkedlist A and B alternately into 
	//    a new linkedlist C, then returns C.
        // Follow the pattern to pick items in list A and B, 
        //        linkedlist A->linkedlist B->linkedlist A->linkedlist B …
	// If A is longer than B, append remaining items in A to C
	//     when the end of B is first reached.
	// If B is longer than A, append remaining items in B to C
	//     when the end of A is first reached.
	// E.g1 A = {1, 3, 5, 7, 9} and B = {2, 4, 6}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 7, 9}.
        //
	// E.g2 A = {1, 3, 5} and B = {2, 4, 6, 8, 10}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 8, 10}.
	// Note: after this method is called, both list A and B are UNCHANGED.
	public static MyLinkedList interleave(MyLinkedList A, MyLinkedList B) {
		MyLinkedList C = new MyLinkedList();
		ListNode curA = A.head.next, curB = B.head.next;

		while(curA != null || curB != null) { //While loops continues until both A and B have been fully walked through
			//Checks if list A still has a new element to add. If it does, it adds that element to C,
			//then walks list A forwards.
			if(curA != null) {
				C.add(curA.data);
				curA = curA.next;
			}
			if(curB != null) {
				C.add(curB.data);
				curB = curB.next;
			}
		}
		return C; //change this as you need.
	}
	
	// Inserts the specified element at the specified position in this list. 
	// Shifts the element currently at that position (if any) and any subsequent
	//     elements to the right (adds one to their indices).
	// if(index < 0 or index > this.size), throws IndexOutOfBoundsException.
	
	// E.g, if this list is [dummy]->["A"]->["B"]->["C"] with size = 3.
	//   add(0,D) will result in [dummy]->["D"]->["A"]->["B"]->["C"].
	//   Continuing on the previous add() call, add(1,"E") will
	//   change the existing list to [dummy]->["D"]->["E"]->["A"]->["B"]->["C"].
	public void add(int index, Object o) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index passed in is not valid!");
		}
		ListNode cur = this.head;
		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			cur = cur.next;
			i++;
		}
		ListNode nn = new ListNode(o);
		nn.next = cur.next; //points nn at the node which is currently at the index
		cur.next = nn; //points the node left of the index at nn
		this.size++;

	}
	public Object get(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds!");
		}
		ListNode cur = this.head.next;

		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			//Why does < index + 1 work, but not < index?
			cur = cur.next;
			i++;
		}
		return cur.data; //change this as you need.
	}
	
	// Removes (cuts out) the list node at the specified index in this list. 
	// Returns the data element in the node that is removed.
	// Be noted that the list node at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds!");
		}
		ListNode prev = this.head, cur = this.head.next;

		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			prev = cur;
			cur = cur.next;
			i ++;
		}

		Object dataRemoved = cur.data; //Stores the value of the current node that will be removed
		prev.next = cur.next;
		//cur = cur.next; //Is this line necessary for some reason?
		this.size--;

		return dataRemoved; //change this as you need.
	}

	
	//Add the object e to the end of this list.
	// it returns true, after e is successfully added.
	public boolean add(Object e) {
		ListNode prev = this.head, cur = this.head.next;
		ListNode nn = new ListNode(e);

		if(size == 0) { //If the list is empty, adds this way to avoid NullPointerException
			prev.next = nn;
			this.size++;
			return true;
		}
		int i = 0;
		while(i < size - 1) {
//			prev = cur;
			cur = cur.next;
			i++;
		}

//		for (int i = 0; i < size - 1; i++) { //Walks to the very end of the linked list
//			//Is this better or worse than doing it with a while loop?
//			//Why do I need size - 1 rather than size?
//			//prev = cur;
//			cur = cur.next;
//		}
		cur.next = nn;
		this.size++;

		return true; //change this as you need.
	}
	
        //Please DO NOT Change the following toString() method!!!
        //You must include the following toString() method in your source code.
	@Override
	public String toString() {
		String result = "{";
	    for (ListNode node = this.head.next; node != null; node = node.next) {
	    		if(node.next != null)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
