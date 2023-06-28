package miniproject;

import java.util.Scanner;
import java.util.Formatter;

public class Manufacturer {
	Scanner sc = new Scanner(System.in), in = new Scanner (System.in);
	int manuID;
	double electricity;
	double sugarcaneQty, quantity; // source product
	double sugarQty, jaggeryQty, molassesQty; // products and by products
	double sugarRate, jaggeryRate, molassesRate; // per kg
	Formatter formatter = new Formatter();
	double total, sugarTotal, jaggeryTotal, molassesTotal;
	int billNo;
	String name, addr, address = "Salient Sugar Manufacturers Inc, Hinjewadi";
	long phoneNo;
	Scanner bill = new Scanner(System.in);

	Queue queueMW = new Queue();

	// Constructor
	public Manufacturer() {
		this.sugarQty = this.jaggeryQty = this.molassesQty = this.sugarcaneQty = 0.0;
		this.sugarRate = 30.0;
		this.jaggeryRate = 25.0;
		this.molassesRate = 20.0;
		this.electricity = 0.0;
		this.manuID = 1001;
	}

	// Place Order for Sugarcane
	void placeOrder(Supplier objsup) {
		System.out.print("Enter Quantity of Sugarcane Required (kg): ");
		quantity = sc.nextDouble(); // sugarcane required ka quantity
		System.out.println("Computing, Please Wait ");
		Order order = new Order(manuID, quantity);
		int confirmOrder = objsup.deliverOrder(order, false);
		if (confirmOrder == 1) {
			sugarcaneQty += order.qty;
			System.out.println("\nYour order has been confirmed.\nDelivered " + order.qty + " kg sugarcane");
		}
	}

	// Create Products in Factory
	void sugarcaneProducts() {
		double temp = sugarcaneQty;
		sugarQty = 0.55 * temp;
		sugarcaneQty -= sugarQty;
		jaggeryQty = 0.25 * temp;
		sugarcaneQty -= jaggeryQty;
		molassesQty = 0.15 * temp;
		sugarcaneQty -= molassesQty;
		System.out.println(
				"\n55% of Sugarcane Converted to Sugar\n25% of Sugarcane Converted to Jaggery\n15% of Sugarcane Converted to Molasses");
		formatter.format("%.2f", sugarcaneQty);

		this.generateElectricity(temp);
	}

	void generateElectricity(double temp) {
		// 1kg sugarcane generates 0.06kWh electricity
		electricity = temp * 0.05;
		formatter = new Formatter();
		formatter.format("%.2f", sugarcaneQty);
		System.out.println("\nSugarcane Remains Utilised to Generate Electricity: " + formatter + " kg");
		formatter = new Formatter();
		formatter.format("%.2f", electricity);
		System.out.println("Electricity Generated: " + formatter + " kWh");
		sugarcaneQty = 0.0; // ignoring losses in the factory
	}

	void display() {
		System.out.println("\n========== MANUFACTURE PRODUCT STOCKS ==========");
		System.out.println("SR\tPRODUCT\t\tQUANTITY (KG)");
		formatter = new Formatter();
		formatter.format("%.2f", sugarcaneQty);
		System.out.println("0\tSugarcane\t" + formatter);
		formatter = new Formatter();
		formatter.format("%.2f", sugarQty);
		System.out.println("1\tSugar\t\t" + formatter);
		formatter = new Formatter();
		formatter.format("%.2f", jaggeryQty);
		System.out.println("2\tJaggery\t\t" + formatter);
		formatter = new Formatter();
		formatter.format("%.2f", molassesQty);
		System.out.println("3\tMolasses\t" + formatter);
		System.out.println("================================================");
	}

	int deliverOrder(Order order) {
		total = sugarTotal = jaggeryTotal = molassesTotal = 0.0;
		int confirm = 0;
		System.out.println("\n===== IMPORTING FROM MANUFACTURER =====");
		if (sugarQty >= 0 && order.sugarQty > 0) {

			if (order.sugarQty <= sugarQty) {
				sugarTotal = order.sugarQty * sugarRate;
				System.out.println("==> Sugar Total: Rs. " + sugarTotal);
			} else {
				System.out.println("Sorry! We don't have enough Sugar Stock for your Order");
				order.sugarQty = sugarTotal = 0.0;
			}

		}
		if (jaggeryQty >= 0 && order.jaggeryQty > 0) {

			if (order.jaggeryQty <= jaggeryQty) {
				jaggeryTotal = order.jaggeryQty * jaggeryRate;
				System.out.println("==> Jaggery Total: Rs. " + jaggeryTotal);
			} else {
				System.out.println("Sorry! We don't have enough Jaggery Stock for your Order");
				order.jaggeryQty = jaggeryTotal = 0.0;
			}

		}
		if (molassesQty >= 0 && order.molassesQty > 0) {
			if (order.molassesQty <= molassesQty) {
				molassesTotal = order.molassesQty * molassesRate;
				System.out.println("==> Molasses Total: Rs. " + molassesTotal);
			} else {
				System.out.println("Sorry! We don't have enough Molasses Stock for your Order");
				order.molassesQty = molassesTotal = 0.0;
			}

		}
		System.out.println("Press 1-Confirm Order | 0-Cancel Order");
		confirm = sc.nextInt();
		if (confirm == 1) {
			total = sugarTotal + jaggeryTotal + molassesQty;
			sugarQty -= order.sugarQty;
			jaggeryQty -= order.jaggeryQty;
			molassesQty -= order.molassesQty;
		}

		return confirm;
	}
	
	void bill(Wholesaler w) {
		System.out.print("Enter Recipient Name : ");
		name = bill.nextLine();
		System.out.print("Enter Shipping Address : ");
		addr = bill.nextLine();
		check(); // for phone number

		System.out.println("\n\n************************************************************");
		System.out.println("ID : " + w.wsID);
		System.out.println("Manufacturer Address : " + address);
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
		System.out.format("%-10s %10s %10s %10s %10s ", 1, "Sugar", 30, w.sugarQtyReq, sugarTotal);
		System.out.println(" ");
		System.out.format("%-10s %10s %10s %10s %10s ", 1, "Jaggery", 25, w.jaggeryQtyReq, jaggeryTotal);
		System.out.println(" ");
		System.out.format("%-10s %10s %10s %10s %10s ", 1, "Molasses", 20, w.molassesQtyReq, molassesTotal);
		System.out.println(" ");
		System.out.println("____________________________________________________________");
		System.out.println(" ");
		System.out.println("\t\t\t\t\t   TOTAL : " + total);
		System.out.println(" ");
		System.out.println("************************************************************");
	}

	long check() { // valid phone number checking
		System.out.print("Enter Valid 10 Digit Phone Number: ");
		phoneNo = sc.nextLong();
		String s = Long.toString(phoneNo); // cross check
		if (s.length() == 10) {
			return phoneNo;
		} else {
			return check();
		}
	}
}