package miniproject;

import java.util.Scanner;

public class Supplier { // Sugarcane Supplier
	int supplierID;
	String address = "Shyam Agricultures, Aundh";
	double sugarcaneQty, newStock;
	double rate; // Rs 10 per kg
	Scanner sc = new Scanner(System.in);
	Scanner in = new Scanner(System.in);
	Scanner bill = new Scanner(System.in);

	int billNo;
	String name, addr;
	long phoneNo;
	double total;

	// Constructor
	public Supplier() {
		this.sugarcaneQty = 0.0;
		this.rate = 10.0;
		this.supplierID = 1521;
	}

	Queue queueSM = new Queue();

	// Deliver Order - Manufacturer
	int deliverOrder(Order order, boolean pending) {
		total = 0.0;
		int confirm = 0;
		if (sugarcaneQty >= 0) {
			if (order.qty > 0) {
				if (order.qty <= sugarcaneQty) {
					total = order.qty * rate;
					System.out.print("==> Total Amount to be Paid: Rs. " + total
							+ "\n\nPress 1-Confirm Order | 0-Cancel Order: ");
					confirm = in.nextInt();
					if (confirm == 1) {
						sugarcaneQty -= order.qty;
					} else {
						System.out.println("No Problem, Please Visit Us Again!");
					}
				} else if (order.qty > sugarcaneQty || sugarcaneQty == 0) {
					if (pending) {
						System.out.println("! Regular Delivery Status: Sugarcane Restock Required");
					} else {
						System.out.println("\nSorry! We do not have enough stock for your order at this moment!");
						// Node temp = new Node(order);
						queueSM.add(order);
						System.out.println("We have put you in the waiting list!");
					}
				}
			}
		}
		return confirm;
	}

	//Deliver Order - Wholesaler
	int deliverOrder(Order order) {
		total = 0.0;
		int confirm = 0;
		System.out.println("\n====== IMPORTING FROM SUGARCANE SUPPLIER ======");
		if (sugarcaneQty >= 0 && order.qty > 0) {
			if (order.qty <= sugarcaneQty) {
				total = order.qty * rate;
				System.out.println("==> Sugarcane Total: Rs. " + total);
			} else {
				System.out.println("Sorry! we don't have enough Sugarcane Stock for your Order");
				order.qty = total = 0.0;
			}

		}
		System.out.println("Press 1-Confirm Order | 0-Cancel Order");
		confirm = sc.nextInt();
		if (confirm == 1) {
			sugarcaneQty -=order.qty;
		}

		return confirm;
	}

	// Deliver Pending Orders
	void deliverPending() {
		System.out.println("Current Order Required (kg) Sugarcane: "+queueSM.front.currentOrder.qty); // correct
		if (sugarcaneQty == 0 || queueSM.front.currentOrder.qty > sugarcaneQty) {
			System.out.println("! Deliver Pending Status: Sugarcane Restock Required");
		} else { // Deliver
			queueSM.remove(this);
		}
	}

	// Update SugarCane Stock
	void sugarcaneStock() {
		System.out.print("Enter New Stock (kg): ");
		newStock = in.nextInt();
		sugarcaneQty += newStock;
		System.out.println("==> Current Stock: " + sugarcaneQty + " kg");
	}

	void bill(Manufacturer m) {
		billNo++;
		System.out.print("Enter Name : ");
		name = bill.nextLine();
		System.out.print("Enter Shipping Address : ");
		addr = bill.nextLine();
		check(); // for phone number

		System.out.println("\n\n************************************************************");
		System.out.println("ID : " + m.manuID);
		System.out.println("Supplier Address : " + address);
		System.out.println("Phone Number : " + phoneNo);
		System.out.println("Date : 13 / 12 / 2022");
		System.out.println("____________________________________________________________");
		System.out.format("%40s ", ":TAX INVOICE:");
		System.out.println(" ");
		System.out.println(" ");
		System.out.format("%-20s %31s ", "Bill: " + billNo, "Manufacturer Address: " + addr);
		System.out.println(" ");
		System.out.format("%-20s ", "Name: " + name);
		System.out.println(" ");
		System.out.println("____________________________________________________________");
		System.out.format("%-10s %10s %10s %10s %10s ", "Sr.No", "Item Name", "Rate", "Quantity", "Amount");
		System.out.println(" ");
		System.out.format("%-10s %10s %10s %10s %10s ", 1, "Sugarcane", 10, m.quantity, total);
		System.out.println(" ");
		System.out.println("____________________________________________________________");
		System.out.println(" ");
		System.out.println("\t\t\t\t\t   TOTAL : " + total);
		System.out.println(" ");
		System.out.println("************************************************************");
	}

	void bill(Wholesaler w) {
		System.out.print("Enter Name : ");
		name = bill.nextLine();
		System.out.print("Enter Shipping Address : ");
		addr = bill.nextLine();
		check(); // for phone number

		System.out.println("\n\n************************************************************");
		System.out.println("ID : " + w.wsID);
		System.out.println("Supplier Address : " + address);
		System.out.println("Phone Number : " + phoneNo);
		System.out.println("Date : 13 / 12 / 2022");
		System.out.println("____________________________________________________________");
		System.out.format("%40s ", ":TAX INVOICE:");
		System.out.println(" ");
		System.out.println(" ");
		System.out.format("%-20s %31s ", "Bill: " + billNo, "Wholesaler Address: " + addr);
		System.out.println(" ");
		System.out.format("%-20s ", "Name: " + name);
		System.out.println(" ");
		System.out.println("____________________________________________________________");
		System.out.format("%-10s %10s %10s %10s %10s ", "Sr.No", "Item Name", "Rate", "Quantity", "Amount");
		System.out.println(" ");
		System.out.format("%-10s %10s %10s %10s %10s ", 1, "Sugarcane", 10, w.sugarcaneQty, total);
		System.out.println(" ");
		System.out.println("____________________________________________________________");
		System.out.println(" ");
		System.out.println("\t\t\t\t\t   TOTAL : " + total);
		System.out.println(" ");
		System.out.println("************************************************************");
	}

	long check() { // valid phone number checking
		System.out.println("Enter valid 10 digit phone number");
		phoneNo = sc.nextLong();
		String s = Long.toString(phoneNo); // cross check
		if (s.length() == 10) {
			return phoneNo;
		} else {
			return check();
		}
	}
}