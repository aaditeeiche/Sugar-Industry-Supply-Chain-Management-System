package miniproject;

class Node {

	Order currentOrder;
	Node next;

	Node(Order currentOrder) {
		this.currentOrder = currentOrder;
		this.next = null;
	}
}

public class Queue {
	Node front, rear;

	Queue() {
		this.front = null;
		this.rear = null;
	}

	boolean isEmpty() {
		if (front == null && rear == null)
			return true;
		else
			return false;
	}

	void add(Order currentOrder) { // ENQUEUE
		Node temp = new Node(currentOrder);
		if (this.rear == null) {
			this.front = this.rear = temp; // temp is both front and rear because queue is empty
			return;
		}
		this.rear.next = temp;
		this.rear = temp;
	}

	void remove(Supplier objsup) { // DEQUEUE
		if (this.front == null) // if queue is empty then return null
			return;

		Node temp = this.front;
		int confirm = objsup.deliverOrder(temp.currentOrder, true);
		if (confirm == 1) {
			System.out.println("\n==> Order Has Been Delivered and Dequeued");
			this.front = this.front.next;
		}

		if (this.front == null) { // if front becomes null then rear will also become null
			this.rear = null;
		}
	}
}

class Order {
	int ID;
	double qty; //sugarcane qty
	double sugarQty, jaggeryQty, molassesQty;

	Order(int ID, double qty) {
		this.ID = ID;
		this.qty = qty;
	}
	
	Order(int ID, double sugarQty, double jaggeryQty, double molassesQty, double qty) {
		this.ID = ID;
		this.sugarQty = sugarQty;
		this.jaggeryQty = jaggeryQty;
		this.molassesQty = molassesQty;
		this.qty = qty;
	}
}