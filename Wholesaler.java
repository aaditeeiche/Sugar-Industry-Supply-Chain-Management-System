package miniproject;

import java.util.Scanner;

public class Wholesaler {
	Scanner sc = new Scanner(System.in);
	int wsID;
	double sugarQty, jaggeryQty, molassesQty, sugarcaneQty; // stock
	double sugarQtyReq, jaggeryQtyReq, molassesQtyReq, sugarcaneQtyReq;
	double sugarRate, jaggeryRate, molassesRate, juiceRate; // per kg
	int billNo;
	String name, address = "Maruti Wholesalers, Kothrud";
	long phone_no;
	double sugartotal, jaggerytotal, molassestotal;
	Scanner bill = new Scanner(System.in);


	// Constructor
	public Wholesaler() {
		this.sugarQty = this.jaggeryQty = this.molassesQty = this.sugarcaneQty = 0.0;
		this.sugarRate = 35.0;
		this.jaggeryRate = 30.0;
		this.molassesRate = 25.0;
		this.juiceRate = 30.0;
		this.wsID = 2001;
	}

	// Place Order for Goods to Manufacturer
	void placeOrder(Manufacturer objman, Supplier objsup) {
		System.out.println("\n========== BUY GOODS ===========");
		System.out.println("SR\tITEM NAME\tRATE");
		System.out.println("1.\tSugar\t\t30\n2.\tJaggery\t\t25\n3.\tMolasses\t20\n4.\tSugarcane\t10");
		System.out.println("\n================================\nENTER QUANTITIES REQUIED");
		System.out.print("=> Sugar (kg): ");
		sugarQtyReq = sc.nextDouble();
		System.out.print("=> Jaggery (kg): ");
		jaggeryQtyReq = sc.nextDouble();
		System.out.print("=> Molasses (kg): ");
		molassesQtyReq = sc.nextDouble();
		System.out.print("=> Sugarcane (kg): ");
		sugarcaneQtyReq = sc.nextDouble();

		// Manufacture Import
		Order order1 = new Order(wsID, sugarQtyReq, jaggeryQtyReq, molassesQtyReq, sugarcaneQtyReq);
		int confirm = objman.deliverOrder(order1);
		if (confirm == 1) {
			sugarQty += order1.sugarQty;
			jaggeryQty += order1.jaggeryQty;
			molassesQty += order1.molassesQty;
			System.out.println("Your order has been confirmed and delivered!");
		}

		// Sugarcane Supplier Import
		Order order2 = new Order(wsID, sugarcaneQtyReq);
		confirm = objsup.deliverOrder(order2);
		if (confirm == 1) {
			sugarcaneQty += order2.qty;
			System.out.println("Your order has been confirmed and delivered!");
		}
	}

	// Check Wholesaler Inventory
	void display() {
		System.out.println("\n========= WHOLESALER INVENTORY ==========");
		System.out.println("SR\tITEM\tQUANTITY (KG)");
		System.out.println("1.\tSugarcane\t" + sugarcaneQty + "\n2.\tSugar\t\t" + sugarQty + "\n3.\tJaggery\t\t"
				+ jaggeryQty + "\n4.\tMolasses\t" + molassesQty);
		System.out.println("===========================================");
	}
}